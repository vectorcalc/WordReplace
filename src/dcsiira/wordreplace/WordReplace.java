
package dcsiira.wordreplace;

import java.util.HashMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
/**
 * Word Replacing Plugin for Bukkit
 *
 * @author DCSiira
 */
public class WordReplace extends JavaPlugin {
    private final WRPlayerListener playerListener = new WRPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    private PluginManager pm;
    private Logger log;
    Configuration config;
   
    public static WordReplace instance;
    public static File wordreplace;
    



    public void onDisable() {
        System.out.println("[WordReplace] Plugin Disabled"); //Show Plugin Disabled in Log File
        pm.disablePlugin(this);
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        this.pm = getServer().getPluginManager();
        this.log = getServer().getLogger();
        this.config = getConfiguration();
        instance = this;
        wordreplace = getFile();
        
        WRConfiguration.checkConfigFile();
        
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Priority.Lowest, this); //Register Event type/priority with Bukkit

        getCommand("WR").setExecutor(new WRCommand(this)); //Register command "/WR" with Bukkit
        System.out.println(this.getDescription().getName() + " version " + this.getDescription().getVersion() + " is enabled!" );
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}