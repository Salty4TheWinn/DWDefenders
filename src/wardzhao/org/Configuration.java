package wardzhao.org;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;

public class Configuration {
	
	// 指定boss tab分开

	private static HashSet<String> bossSet = new HashSet<String>();
	
	public static Properties properties = null;
	
	public static void loadConfFile() {
		try {

			FileInputStream fis = new FileInputStream(new File("conf.properties"));
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			Configuration.properties = new Properties();  
			Configuration.properties.load(isr);
			String bosses = properties.getProperty("bosses"); 
			String[] bossArray = bosses.split("\t");
			for (String e : bossArray) {
				Configuration.bossSet.add(e);
			}
			
			isr.close();
			fis.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 

	}
	
	// 判断一个英雄是不是boss boss优先选
	public static boolean isBoss(String heroName) {
		return Configuration.bossSet.contains(heroName);
	}
}
