
package dcsiira.wordreplace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /WR command.
 * @author DCSiira
 */
public class WRCommand implements CommandExecutor
{
    private final WordReplace plugin;

    public WRCommand(WordReplace plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split)
    {
    	if(!sender.isOp())
    	{
    		sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] You are not able to use this command, OP's only");
    		return true;
    	}
        if (split.length == 1)
        {
        	if(split[0].equalsIgnoreCase("version"))
        	{
                String version = plugin.getDescription().getVersion();
                sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Version: " + version);
                return true;
        	}
        	else if(split[0].equalsIgnoreCase("reload"))
        	{
        		plugin.config.readNodes();
        		sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Reload Complete");
        		return true;
        	}
        	else if(split[0].equalsIgnoreCase("list"))
        	{
        		wordList(sender);
            	return true;
        	}
        }
        if (split.length == 2)
        {
        	if(split[0].equalsIgnoreCase("remove"))
        	{
                remove(Integer.parseInt(split[1]) + 1);
                sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Removing List Complete");
                return true;
        	}
        }
        else
        {
        	if(split[0].equalsIgnoreCase("add"))
        	{
        		add(split);
        		sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Adding Complete");
        		return true;
        	}
        }
        return false;
    }
    public boolean wordList(CommandSender sender)
    {
    	sender.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE + "] Replaces:");
        for (int listLoop = 0; listLoop < this.plugin.wrList.size(); listLoop++)
        {
          String wordReplacing = this.plugin.parseWordReplacing(listLoop);
          ChatColor wordColor = this.plugin.parseWordColor(listLoop);
          String wordsBeingReplaced = "[";

          for (String tempWordBeingReplaced : this.plugin.parseWordsBeingReplaced(listLoop)) {
            wordsBeingReplaced = wordsBeingReplaced + tempWordBeingReplaced + ", ";
          }
          wordsBeingReplaced = wordsBeingReplaced.substring(0, wordsBeingReplaced.length() - 2);
          sender.sendMessage(wordsBeingReplaced + "] with " + wordColor + wordReplacing);
        }
        return true;
      }

    public boolean add(String[] split)
    {
    	
    	String newList = split[1] + "," + split[2] + ",";
    	for(int loop = 3; loop < split.length; loop++)
    	{
    		newList += split[loop] + ":";
    	}
    	newList = newList.substring(0, newList.length()-1);
    	this.plugin.wrList.add(newList);
    	this.plugin.config.writeNode("word-replace-list",this.plugin.wrList);
    	this.plugin.config.readNodes();
    	return true;
    }
    public boolean remove(int removeList)
    {
    	this.plugin.wrList.remove(removeList);
    	this.plugin.config.writeNode("word-replace-list",this.plugin.wrList);
    	this.plugin.config.readNodes();
    	return true;
    }
}
