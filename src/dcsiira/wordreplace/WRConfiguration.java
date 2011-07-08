package dcsiira.wordreplace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.ChatColor;

public class WRConfiguration
{
    private final WordReplace plugin;
    public int numberOfLists = 0;
    public List<String> WordReplacingList;
    public String normalChatColor = "WHITE";
  
  public WRConfiguration(WordReplace plugin)
  {
	  this.plugin = plugin;
  }

  @SuppressWarnings("unchecked")
public void checkConfigFile()
  {
    File configFile = new File(plugin.getDataFolder(), "config.yml");
    if (!configFile.canRead()) try {
        configFile.getParentFile().mkdirs();
        JarFile jar = new JarFile(plugin.wordreplace);
        JarEntry entry = jar.getJarEntry("config.yml");
        InputStream is = jar.getInputStream(entry);
        FileOutputStream os = new FileOutputStream(configFile);
        byte[] buf = new byte[(int)entry.getSize()];
        is.read(buf, 0, (int)entry.getSize());
        os.write(buf);
        os.close();
        plugin.getConfiguration().load();
        System.out.println("WordReplace: configuration file generated successfully");
      } catch (Exception e) {
        System.out.println("WordReplace: could not generate configuration file");
      }
      
    try
    {
        WordReplacingList = (List<String>)plugin.getConfiguration().getProperty("replaceList");
        numberOfLists = WordReplacingList.size();
        normalChatColor = (String)plugin.getConfiguration().getProperty("normal-chat-color");
    }
    catch (Exception e)
    {
      System.out.println("WordReplace: error reading configuration file");
    }
  }
  public String parseWordColor(int listNumber)
  {
      String[] split = WordReplacingList.get(listNumber).split(",");
      return split[0];	  
  }
  public String parseWordReplacing(int listNumber)
  {
      String[] split = WordReplacingList.get(listNumber).split(",");
      return split[1];	
  }
  public String[] parseWordsBeingReplaced(int listNumber)
  {
      String[] split = WordReplacingList.get(listNumber).split(",");
      String[] replaceWords = split[2].split(":");
      return replaceWords;	  
  }
  public ChatColor getChatColor(String color)
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
  	else if(color.equalsIgnoreCase("GOLD") || color.equalsIgnoreCase("&6") || color.equalsIgnoreCase("DARK_YELLOW") )
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
  	else if(color.equalsIgnoreCase("LIGHT_PURPLE") || color.equalsIgnoreCase("&d") || color.equalsIgnoreCase("PINK") )
  			return ChatColor.LIGHT_PURPLE;
  	else if(color.equalsIgnoreCase("YELLOW") || color.equalsIgnoreCase("&e"))
  			return ChatColor.YELLOW;
  	else if(color.equalsIgnoreCase("WHITE") || color.equalsIgnoreCase("&f"))
  			return ChatColor.WHITE;
  	else
  		return ChatColor.WHITE;
  }



  

}