
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

    public WRPlayerListener(WordReplace instance) {
        plugin = instance;
    }


    public void onPlayerChat(PlayerChatEvent event) {
    	  Player p = event.getPlayer();
    	  String playerMessage = event.getMessage();
    	  playerMessage = playerMessage.replaceAll("%","%%") + " ";
    	  event.setMessage(replace(p, playerMessage));
   }
    
    public String replace(Player p, String playerMessage)
    {
        if ((WRConfiguration.replaceFromWords == null) || (WRConfiguration.replaceFromWords.size() == 0))
        	  return playerMessage;

         String[] split = playerMessage.split(" ");
        StringBuilder out = new StringBuilder();


        for (String word : split) {
      	  for (String replace : WRConfiguration.replaceFromWords) {
              if (word.equalsIgnoreCase(replace))
                word = getColor(WRConfiguration.replaceWordColor) + WRConfiguration.replaceToWord + getColor(WRConfiguration.normalChatColor); 
              else if(word.equalsIgnoreCase(replace + "!"))
                  word = getColor(WRConfiguration.replaceWordColor) + WRConfiguration.replaceToWord + "!" + getColor(WRConfiguration.normalChatColor);
              else if(word.equalsIgnoreCase(replace + "?"))
                  word = getColor(WRConfiguration.replaceWordColor) + WRConfiguration.replaceToWord + "?" + getColor(WRConfiguration.normalChatColor);
              else if(word.equalsIgnoreCase(replace + ","))
                  word = getColor(WRConfiguration.replaceWordColor) + WRConfiguration.replaceToWord + "," + getColor(WRConfiguration.normalChatColor); 
      	  }
              out.append(word).append(" ");
        }
        return out.toString();
      }
    
    public static ChatColor getColor(String color)


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