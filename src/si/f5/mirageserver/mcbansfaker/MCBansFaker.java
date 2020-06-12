package si.f5.mirageserver.mcbansfaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MCBansFaker extends JavaPlugin {

    private static String apiKey;

    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MCBansFaker" + ChatColor.GREEN + " Enabled.");
        getCommand("fakemcbans").setExecutor(new Commander());

        saveDefaultConfig();
        FileConfiguration config = getConfig();
        apiKey = config.getString("apikey");

    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MCBansFaker" + ChatColor.DARK_RED + " Disabled.");

    }

    static String getApiKey() {

        return apiKey;

    }

}