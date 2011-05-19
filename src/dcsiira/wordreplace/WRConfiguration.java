package dcsiira.wordreplace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;




public abstract class WRConfiguration
{ 
  
  public static List<String> replaceFromWords = Arrays.asList("DCSiira", "DC", "Siira");
  public static String replaceWordColor = "AQUA";
  public static String replaceToWord = "Admin";
  public static String normalChatColor = "WHITE";

  public static void checkConfigFile()
  {
    File configFile = new File(WordReplace.instance.getDataFolder(), "config.yml");
    if (!configFile.canRead()) try {
        configFile.getParentFile().mkdirs();
        JarFile jar = new JarFile(WordReplace.wordreplace);
        JarEntry entry = jar.getJarEntry("config.yml");
        InputStream is = jar.getInputStream(entry);
        FileOutputStream os = new FileOutputStream(configFile);
        byte[] buf = new byte[(int)entry.getSize()];
        is.read(buf, 0, (int)entry.getSize());
        os.write(buf);
        os.close();
        WordReplace.instance.getConfiguration().load();
      } catch (Exception e) {
        System.out.println("Growbie: could not create configuration file");
      }

    
    replaceFromWords = Arrays.asList("DCSiira", "DC", "Siira");
    replaceWordColor = "AQUA";
    replaceToWord = "Admin";
    normalChatColor = "WHITE";
    
    try
    {      
      replaceFromWords = (List<String>)WordReplace.instance.getConfiguration().getProperty("replace-these-words");
      replaceWordColor = (String)WordReplace.instance.getConfiguration().getProperty("replaceWordColor");
      replaceToWord = (String)WordReplace.instance.getConfiguration().getProperty("replace-words-to");
      normalChatColor = (String)WordReplace.instance.getConfiguration().getProperty("normal-chat-color");
    }
    catch (Exception e)
    {
      System.out.println("Growbie: error loading configuration");
    }
  }

}