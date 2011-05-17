
package dcsiira.wordreplace;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /WR sample command.
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

        if (split.length == 0) {
            String version = plugin.getDescription().getVersion();
            player.sendMessage("WordReplace Version: " + version);
            return true;
        } else if (split.length == 1){
        	if(split[0].equalsIgnoreCase("version"))
        	{
                String version = plugin.getDescription().getVersion();
                player.sendMessage("WordReplace Version: " + version);
        	}
        	if(split[0].equalsIgnoreCase("reload"))
        	{
                plugin.loadConfig();
                player.sendMessage("WordReplace Reloaded Successfully");
        	}
        	return true;
        }
        else {
            return false;
        }
    }
}
