
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

    public WRPlayerListener(WordReplace plugin) {
        this.plugin = plugin;
    }


    public void onPlayerChat(PlayerChatEvent event) {
    	  Player player = event.getPlayer();
    	  String playerMessage = event.getMessage();
    	  event.setMessage(replace(player, playerMessage));
   }
    
    public String replace(Player player, String playerMessage)
    {
        String[] split = playerMessage.split(" ");
        StringBuilder out = new StringBuilder();

            for (String wordBeingChecked : split) 
            {
            	for(int listLoop = 0; listLoop < plugin.config.numberOfLists; listLoop++)
            	{
            	String wordReplacing = plugin.config.parseWordReplacing(listLoop);
            	String wordColor = plugin.config.parseWordColor(listLoop);
              	  for (String wordBeingReplaced : plugin.config.parseWordsBeingReplaced(listLoop))
            	  {
              		wordBeingChecked = checkWord(wordReplacing, wordBeingReplaced, wordBeingChecked, plugin.config.getChatColor(wordColor));
              	  }
            	}
                out.append(wordBeingChecked).append(" ");
            }
        return out.toString();
    }
    
    public String checkWord(String wordReplacing, String wordBeingReplaced, String wordBeingChecked, ChatColor wordColor)
    {
    	 if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced))
             return wordColor + wordReplacing + plugin.config.getChatColor(plugin.config.normalChatColor);
         else if(wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "!"))
             return wordColor + wordReplacing+ "!" + plugin.config.getChatColor(plugin.config.normalChatColor);
         else if(wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "?"))
             return wordColor + wordReplacing + "?" + plugin.config.getChatColor(plugin.config.normalChatColor);
         else if(wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + ","))
             return wordColor + wordReplacing + "," + plugin.config.getChatColor(plugin.config.normalChatColor);
         else
        	 return wordBeingChecked;
    }
 
}