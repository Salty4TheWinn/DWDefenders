package wardzhao.org;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

public class BestDefenders {

	private final String INPUT_FILE_NAME = "������.txt";
	private final String OUTPUT_FILE_NAME = "����.txt";
	private ArrayList<Hero> candidateHero = new ArrayList<Hero>();
	private ArrayList<Hero> chosenHero = new ArrayList<Hero>();
	private Summoner[] summoners = null;
	
	// ��ȡһ��tab�ָ����ı��ļ�����Ҫ��ϵ��Ӣ�ۣ���Ա��ս���������;��AQ/AW����/AW����������Ĭ�ϲ�����
	// ʵ��ϵ��û���õ�
	// �������ÿ�����Ӣ�۲��ظ��ж���
	public void loadFile() {
		
		File file = new File(INPUT_FILE_NAME);
		BufferedReader br = null;
		String line = null;
		boolean isFirst = true;
		int rowNumber = 0;
		String heroName;
		Integer pi;
		String summonerName;
		String group;
		Boss boss = new Boss();
		
		try {
			br = new BufferedReader(new FileReader(file));
			while((line=br.readLine())!=null){
				
				rowNumber++;
				
				// skip first line
				if (isFirst) {
					isFirst = false;
					continue;
				}
				
			    String[] segments = line.split("\t"); //��tab�ָ�
			    
			    // skip lines which columns are less than 5
			    if (segments.length < 5) {
			    	continue;
			    }
			    
			    // skip lines which hero is for AQ or AW attack
			    if (segments.length == 6 && (segments[5].startsWith("AQ") || segments[5].startsWith("AW����"))) {
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
			
			// create summoner map
			HashMap<String, Summoner> mapSummoner = new HashMap<String, Summoner>();
			
			// fill summoner's heros
			Summoner summoner = null;
			for (Hero hero : candidateHero) {
				
				// get summoner
				if (mapSummoner.containsKey(hero.getSummoner())) {
					summoner = mapSummoner.get(hero.getSummoner());
				} else {
					summoner = new Summoner(hero.getGroup(), hero.getSummoner());
					mapSummoner.put(hero.getSummoner(), summoner);
				}
				
				summoner.addHero(hero);
			}
			
			// calculate summoner's pi
			this.summoners = new Summoner[mapSummoner.values().size()];
			int i = 0;
			for (Summoner player : mapSummoner.values()) {
				player.CalculatePI();
				this.summoners[i++] = player;
			}
			
			br.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("row number = " + rowNumber);
			e.printStackTrace();
		} 

	}
	
	// ����boss��ս������ ����ȷ��boss Ȼ��ս���ߵ�Ӣ��
	public void sort() {
		this.candidateHero.sort(new HeroComparator() );
		
		// sort summoner by pi
		Arrays.sort(this.summoners);
	}
	
	// ��ѡӢ��
	public void choose() {
		
		HeroQualification heroCheck = new HeroQualification();
		SummonerQualification summonerCheck = new SummonerQualification();
		
		for (Hero hero : this.candidateHero) {
			if (heroCheck.canBeChosen(hero.getGroup(), hero.getName()) && summonerCheck.canBeChosen(hero.getSummoner())) {
				heroCheck.choose(hero.getGroup(), hero.getName());
				summonerCheck.choose(hero.getSummoner());
				chosenHero.add(hero);
			}
		}
	}
	
	// дһ��tab�ָ����ı��ļ�����Ҫ��ϵ��Ӣ�ۣ���Ա��ս�������
	public void output() {
		File file = new File(OUTPUT_FILE_NAME);
		BufferedWriter wr = null;
		String line = null;
		boolean isFirst = true;
		String firstLine = "Ӣ��\t��Ա\tս��\t���\r\n";
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

		
	}

}
 