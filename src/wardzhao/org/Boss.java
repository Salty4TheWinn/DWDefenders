package wardzhao.org;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;

public class Boss {
	
	// 指定boss tab分开

	private HashSet<String> bossSet;
	
	public Boss() {
		super();
		this.bossSet = new HashSet<String>();
		this.setBossList();
	}
	
	private void setBossList() {
		try {

			FileInputStream fis = new FileInputStream(new File("conf.properties"));
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			Properties prop = new Properties();  
			prop.load(isr);
			String bosses = prop.getProperty("bosses"); 
			String[] bossArray = bosses.split("\t");
			for (String e : bossArray) {
				this.bossSet.add(e);
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
	public boolean isBoss(String heroName) {
		return bossSet.contains(heroName);
	}
}
