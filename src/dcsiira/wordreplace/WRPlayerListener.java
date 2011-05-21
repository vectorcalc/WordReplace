
package dcsiira.wordreplace;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.ChatColor;
/**
 * Handle events for all Player related events
 * @author DCSiira
 */
public class WRPlayerListener extends PlayerListener {
    private final WordReplace plugin;
    private final WRConfiguration config;

    public WRPlayerListener(WordReplace plugin, WRConfiguration config) {
        this.plugin = plugin;
        this.config = config;
    }


    public void onPlayerChat(PlayerChatEvent event) {
    	  Player p = event.getPlayer();
    	  String playerMessage = event.getMessage();
    	  playerMessage = playerMessage.replaceAll("%","%%") + " ";
    	  event.setMessage(replace(p, playerMessage));
   }
    
    public String replace(Player p, String playerMessage)
    {
        String[] split = playerMessage.split(" ");
        StringBuilder out = new StringBuilder();

            for (String word : split) 
            {
            	for(int i = 0; i < config.numVars; i++)
            	{
              	  for (String replace : config.parseReplaceWords(i))
            	  {
                     word = checkWord(config.parseReplaceToWord(i), replace, word, getColor(config.parseColor(i)));
              	  }
            	}
                out.append(word).append(" ");
            }
        return out.toString();
    }
    
    public String checkWord(String replaceTo, String replaceFrom, String word, ChatColor color)
    {
    	 if (word.equalsIgnoreCase(replaceFrom))
             return color + replaceTo + getColor(config.normalChatColor);
         else if(word.equalsIgnoreCase(replaceFrom + "!"))
             return color + replaceTo+ "!" + getColor(config.normalChatColor);
         else if(word.equalsIgnoreCase(replaceFrom + "?"))
             return color + replaceTo + "?" + getColor(config.normalChatColor);
         else if(word.equalsIgnoreCase(replaceFrom + ","))
             return color + replaceTo + "," + getColor(config.normalChatColor);
         else
        	 return word;
    }
    public ChatColor getColor(String color)
    {
    	if(color.equalsIgnoreCase("BLACK") || color.equalsIgnoreCase("&0"))
    			return ChatColor.BLACK;
    	else if(color.equalsIgnoreCase("DARK_BLUE") || color.equalsIgnoreCase("&1"))
    			return ChatColor.DARK_BLUE;
    	else if(color.equalsIgnoreCase("DARK_GREEN") || color.equalsIgnoreCase("&2"))
    			return ChatColor.DARK_GREEN;
    	else if(color.equalsIgnoreCase("DARK_AQUA") || color.equalsIgnoreCase("&3"))
    			return ChatColor.DARK_AQUA;
    	else if(color.equalsIgnoreCase("DARK_RED") || color.equalsIgnoreCase("&4"))
    			return ChatColor.DARK_RED;
    	else if(color.equalsIgnoreCase("DARK_PURPLE") || color.equalsIgnoreCase("&5"))
    			return ChatColor.DARK_PURPLE;
    	else if(color.equalsIgnoreCase("GOLD") || color.equalsIgnoreCase("&6"))
    			return ChatColor.GOLD;
    	else if(color.equalsIgnoreCase("GRAY") || color.equalsIgnoreCase("&7"))
    			return ChatColor.GRAY;
    	else if(color.equalsIgnoreCase("DARK_GRAY") || color.equalsIgnoreCase("&8"))
    			return ChatColor.DARK_GRAY;
    	else if(color.equalsIgnoreCase("BLUE") || color.equalsIgnoreCase("&9"))
    			return ChatColor.BLUE;
    	else if(color.equalsIgnoreCase("GREEN") || color.equalsIgnoreCase("&a"))
    			return ChatColor.GREEN;
    	else if(color.equalsIgnoreCase("AQUA") || color.equalsIgnoreCase("&b"))
    			return ChatColor.AQUA;
    	else if(color.equalsIgnoreCase("RED") || color.equalsIgnoreCase("&c"))
    			return ChatColor.RED;
    	else if(color.equalsIgnoreCase("LIGHT_PURPLE") || color.equalsIgnoreCase("&d"))
    			return ChatColor.LIGHT_PURPLE;
    	else if(color.equalsIgnoreCase("YELLOW") || color.equalsIgnoreCase("&e"))
    			return ChatColor.YELLOW;
    	else if(color.equalsIgnoreCase("WHITE") || color.equalsIgnoreCase("&f"))
    			return ChatColor.WHITE;
    	else
    		return ChatColor.WHITE;
    }

}