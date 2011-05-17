
package dcsiira.wordreplace;

import java.util.HashMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
   

    public List<String> replaceFromWords = new ArrayList<String>();
    public String replaceWordColor = "AQUA";
    public String replaceToWord = "TestWordReplace";
    public String normalChatColor = "WHITE";
    public boolean debug = true;


    public void onDisable() {
        System.out.println("[WordReplace] Plugin Disabled"); //Show Plugin Disabled in Log File
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        this.pm = getServer().getPluginManager();
        this.log = getServer().getLogger();
        this.config = getConfiguration();
             
        if (!new File(getDataFolder(), "config.yml").exists()) {
            defaultConfig();
          }
          loadConfig();
        
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Priority.Lowest, this); //Register Event type/priority with Bukkit

        //getCommand("WR").setExecutor(new WRCommand(this)); //Register command "/WR" with Bukkit

        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void loadConfig() {
        this.config.load();
        if(checkVersion())
        {
            this.replaceFromWords = this.config.getStringList("replace-these-words", this.replaceFromWords);
            this.replaceWordColor = this.config.getString("replace-word-color", this.replaceWordColor);
            this.replaceToWord = this.config.getString("replace-words-to", this.replaceToWord);
            this.normalChatColor = this.config.getString("normal-chat-color", this.normalChatColor);
            this.debug = this.config.getBoolean("is-debugging", this.debug);
        }
        else
        {
        	defaultConfig();
        }

      }
    private void defaultConfig() {
        this.config.setProperty("Word-Replace-Version", this.getDescription().getVersion());
        this.config.setProperty("replace-these-words", this.replaceFromWords);
        this.config.setProperty("replace-word-color", this.replaceWordColor);
        this.config.setProperty("replace-words-to", this.replaceToWord);
        this.config.setProperty("normal-chat-color", this.normalChatColor);
        this.config.setProperty("is-debugging", this.debug);
        this.config.save();
      }
    
    public boolean checkVersion()
    {
    	String configVersion = null;
    	double curVersion = Double.parseDouble(this.getDescription().getVersion());
    	configVersion = this.config.getString("Word-Replace-Version", configVersion);
    	if(curVersion > Double.parseDouble(configVersion))
    			return false;
    	return true;
    }
    
    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
