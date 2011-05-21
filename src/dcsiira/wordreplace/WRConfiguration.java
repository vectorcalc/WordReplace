package dcsiira.wordreplace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class WRConfiguration
{
    private final WordReplace plugin;
    public int numVars = 0;
    public List<String> wrList;
    public String normalChatColor = "WHITE";
  
  public WRConfiguration(WordReplace plugin)
  {
	  this.plugin = plugin;
  }

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
        numVars = (Integer) plugin.getConfiguration().getProperty("number-of-lists");
        wrList = (List<String>)plugin.getConfiguration().getProperty("replaceList");
        normalChatColor = (String)plugin.getConfiguration().getProperty("normal-chat-color");
    }
    catch (Exception e)
    {
      System.out.println("WordReplace: error reading configuration file");
    }
  }
  public String parseColor(int listNumber)
  {
      String[] split = wrList.get(listNumber).split(",");
      return split[0];	  
  }
  public String[] parseReplaceWords(int listNumber)
  {
      String[] split = wrList.get(listNumber).split(",");
      String[] replaceWords = split[2].split(":");
      return replaceWords;	  
  }
  public String parseReplaceToWord(int listNumber)
  {
      String[] split = wrList.get(listNumber).split(",");
      return split[1];	
  }

  

}