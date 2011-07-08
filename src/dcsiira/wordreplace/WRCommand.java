
package dcsiira.wordreplace;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /WR command.
 * @author DCSiiras
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
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (split.length == 1){
        	if(split[0].equalsIgnoreCase("version"))
        	{
                String version = plugin.getDescription().getVersion();
                player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Version: " + version);
        	}
        	else if(split[0].equalsIgnoreCase("reload"))
        	{
        		//if(plugin.reload())
        		//	player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Reloaded Successfully");
        		//else
        		//	player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Error on reload");  
        		player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] This does not work YET");
        	}
        	else if(split[0].equalsIgnoreCase("list"))        			
        		wordList(player);
        	return true;
        }
        else {
            return false;
        }
    }
    public void wordList(Player player)
    {
        player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Replaces:");
        for(int listLoop = 0; listLoop < plugin.config.numberOfLists; listLoop++)
    	{
        	String wordReplacing = plugin.config.parseWordReplacing(listLoop);
        	String wordColor = plugin.config.parseWordColor(listLoop);
        	String wordsBeingReplaced = "";
        	
        	for (String tempWordBeingReplaced : plugin.config.parseWordsBeingReplaced(listLoop))
        		wordsBeingReplaced += tempWordBeingReplaced + ", ";
        	
        	wordsBeingReplaced = wordsBeingReplaced.substring(0, wordsBeingReplaced.length()-2);
        	player.sendMessage(wordsBeingReplaced + " with " + plugin.config.getChatColor(wordColor) + wordReplacing );
    	}
    }
}
