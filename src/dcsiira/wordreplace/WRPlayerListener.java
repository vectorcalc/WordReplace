package dcsiira.wordreplace;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class WRPlayerListener extends PlayerListener
{
  private final WordReplace plugin;

  public WRPlayerListener(WordReplace plugin)
  {
    this.plugin = plugin;
  }

  public void onPlayerChat(PlayerChatEvent event)
  {
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
      for (int listLoop = 0; listLoop < this.plugin.wrList.size(); listLoop++)
      {
        String wordReplacing = this.plugin.parseWordReplacing(listLoop);
        ChatColor wordColor = this.plugin.parseWordColor(listLoop);
        for (String wordBeingReplaced : this.plugin.parseWordsBeingReplaced(listLoop))
        {
          wordBeingChecked = checkWord(wordReplacing, wordBeingReplaced, wordBeingChecked, wordColor);
          wordBeingChecked = checkWord(wordReplacing, wordReplacing, wordBeingChecked, wordColor);
        }
      }
      out.append(wordBeingChecked).append(" ");
    }
    return out.toString();
  }

  public String checkWord(String wordReplacing, String wordBeingReplaced, String wordBeingChecked, ChatColor wordColor)
  {
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced))
      return wordColor + wordReplacing + this.plugin.normalChatColor;
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "!"))
      return wordColor + wordReplacing + "!" + this.plugin.normalChatColor;
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "!!!"))
        return wordColor + wordReplacing + "!!!" + this.plugin.normalChatColor;
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "?"))
      return wordColor + wordReplacing + "?" + this.plugin.normalChatColor;
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + ","))
      return wordColor + wordReplacing + "," + this.plugin.normalChatColor;
    if (wordBeingChecked.equalsIgnoreCase(wordBeingReplaced + "'s"))
      return wordColor + wordReplacing + "'s" + this.plugin.normalChatColor;
    
    return wordBeingChecked;
  }
}