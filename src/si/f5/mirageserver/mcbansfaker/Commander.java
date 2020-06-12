package si.f5.mirageserver.mcbansfaker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Commander implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {

        if(args.length < 2) {

            sender.sendMessage(ChatColor.RED + "/fakemcbans <player name> <player ip address>");
            return true;

        }

        String apiKey = MCBansFaker.getApiKey();
        String playerName = args[0].toString();
        String playerAddress = args[1].toString();

        if(apiKey == null) {

            sender.sendMessage(ChatColor.RED + "Invalid APIKey! Write APIKey at config!");
            return true;

        }

        try {
            String uriStr = "http://api.mcbans.com/v3/" + apiKey + "/login/" + URLEncoder.encode(playerName, "UTF-8") + "/" + URLEncoder.encode(playerAddress, "UTF-8") + "/4.4.3";
            URLConnection conn = (new URL(uriStr)).openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setUseCaches(false);
            BufferedReader br = null;
            String response = null;

            try {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = br.readLine();
                sender.sendMessage(ChatColor.GREEN + "Successfully Faked");
            } finally {
                if (br != null) {
                    br.close();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            sender.sendMessage(ChatColor.RED + "Exception Error");
        }

        return true;

    }

}