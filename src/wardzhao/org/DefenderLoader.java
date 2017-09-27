package wardzhao.org;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class DefenderLoader {

	private final String INPUT_FILE_NAME = "多样性.txt";
//	private final String OUTPUT_FILE_NAME = "布阵.txt";
//	private ArrayList<Hero> chosenHero = new ArrayList<Hero>();
	private Summoner[] summoners = null;
	
	// 读取一个tab分隔的文本文件，列要求：系别，英雄，成员，战力，组别，用途（AQ/AW进攻/AW布防，空着默认布防）
	// 实际系别没有用到
	// 组别用在每个组的英雄不重复判断上
	public ArrayList<Hero> loadFile() {
		
		String line = null;
		boolean isFirst = true;
		int rowNumber = 0;
		String heroName;
		Integer pi;
		String summonerName;
		String group;
		Configuration boss = new Configuration();
		ArrayList<Hero> candidateHero = new ArrayList<Hero>();
		
		try {
			FileInputStream fis = new FileInputStream(INPUT_FILE_NAME);
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			BufferedReader br = new BufferedReader(isr);
			
			while((line=br.readLine())!=null){
				
				rowNumber++;
				
				// skip first line
				if (isFirst) {
					isFirst = false;
					continue;
				}
				
			    String[] segments = line.split("\t"); //按tab分割
			    
			    // skip lines which columns are less than 5
			    if (segments.length < 5) {
			    	continue;
			    }
			    
			    // skip lines which hero is for AQ or AW attack
			    if (segments.length == 6 && (segments[5].startsWith("AQ") || segments[5].startsWith("AW进攻"))) {
			    	continue;
			    }
			    
			    // hero name
			    heroName = segments[1];
			    if (StringUtils.isBlank(heroName)) {
			    	continue;
			    }
			    
			    // pi
			    if (StringUtils.isBlank(segments[3])) {
			    	continue;
			    }
			    try {
			    	pi = Integer.valueOf(segments[3]); // pi
			    } catch (NumberFormatException e) {
					System.err.println("row number = " + rowNumber);
					e.printStackTrace();
					continue;
				}
			    
			    // summoner
			    summonerName = segments[2];
			    if (StringUtils.isBlank(summonerName)) {
			    	continue;
			    }
			    
			    // group
			    group = segments[4];
			    if (StringUtils.isBlank(group)) {
			    	continue;
			    }
			    
			   // load heros to list
			    candidateHero.add(new Hero(	heroName,  pi,  summonerName, group, boss.isBoss(heroName)));
			    
			} // while
			
			br.close();
			isr.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("row number = " + rowNumber);
			e.printStackTrace();
		}
		return candidateHero; 

	}
	
	// 按照boss和战力排序 优先确定boss 然后定战力高的英雄
/*	public void sort() {
		this.candidateHero.sort(new HeroComparator() );
		
		// sort summoner by pi
		Arrays.sort(this.summoners);
	}*/
	
	// 挑选英雄
	/*public void choose() {
		
		HeroQualification heroCheck = new HeroQualification();
		SummonerQualification summonerCheck = new SummonerQualification();
		
		for (Hero hero : this.candidateHero) {
			if (heroCheck.canBeChosen(hero.getGroup(), hero.getName()) && summonerCheck.canBeChosen(hero.getSummoner())) {
				heroCheck.choose(hero.getGroup(), hero.getName());
				summonerCheck.choose(hero.getSummoner());
				chosenHero.add(hero);
			}
		}
	}*/
	
	// 写一个tab分隔的文本文件，列要求：系别，英雄，成员，战力，组别
	/*public void output() {
		File file = new File(OUTPUT_FILE_NAME);
		BufferedWriter wr = null;
		String line = null;
		boolean isFirst = true;
		String firstLine = "英雄\t成员\t战力\t组别\r\n";
		int rowNumber = 0;
		
		try {
			
			wr = new BufferedWriter(new FileWriter(file));
			
			for (Hero hero : this.chosenHero) {
				
				rowNumber++;
				
				// skip first line
				if (isFirst) {
					isFirst = false;
					wr.write(firstLine);
				}
				
			 line = hero.getName() + "\t"+
			 hero.getSummoner() + "\t" +
			 hero.getPi() + "\t" + 
			 hero.getGroup() + "\r\n";
			 
			 wr.write(line);
			    
			} // while
			
			wr.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("row number = " + rowNumber);
			e.printStackTrace();
		} 

		
	}*/

}
 