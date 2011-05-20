
package dcsiira.wordreplace;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /WR command.
 * @author DCSiira
 */
public class WRCommand implements CommandExecutor {
    private final WordReplace plugin;

    public WRCommand(WordReplace plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
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
                plugin.load();
                player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Reloaded Successfully");
        	}
        	else if(split[0].equalsIgnoreCase("list"))
        	{
                player.sendMessage("[" + ChatColor.AQUA + "WordReplace" + ChatColor.WHITE +"] Replaces:");
                String msg = " | ";
            	for (String replace : WRConfiguration.replaceFromWords) {
            		msg += ChatColor.RED + replace + ChatColor.WHITE + " | ";
            	}
                player.sendMessage(msg);
                player.sendMessage(ChatColor.WHITE + "With " + WRPlayerListener.getColor(WRConfiguration.replaceWordColor) + WRConfiguration.replaceToWord);
        	}
        	return true;
        }
        else {
            return false;
        }
    }
}
