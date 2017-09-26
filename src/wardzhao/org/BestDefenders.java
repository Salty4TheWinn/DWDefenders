package wardzhao.org;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

public class BestDefenders {

	private final String INPUT_FILE_NAME = "������.txt";
	private final String OUTPUT_FILE_NAME = "����.txt";
	private ArrayList<Hero> optionalHero = new ArrayList<Hero>();
	private ArrayList<Hero> chosenHero = new ArrayList<Hero>();
	
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
		String summoner;
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
			    
			    if (segments.length < 5) {
			    	continue;
			    }
			    
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
			    
			    // hero name
			    summoner = segments[2];
			    if (StringUtils.isBlank(summoner)) {
			    	continue;
			    }
			    
			    // hero name
			    group = segments[4];
			    if (StringUtils.isBlank(group)) {
			    	continue;
			    }
			    
			    // ��Ҫ��ϵ��Ӣ�ۣ���Ա��ս�������
			   // ����Ҫ�� String name, Integer pi, String summoner, String group
			    optionalHero.add(new Hero(	heroName,  pi,  summoner, group, boss.isBoss(heroName)));
			    
			} // while
			
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
		this.optionalHero.sort(new HeroComparator() );
	}
	
	// ��ѡӢ��
	public void choose() {
		
		HeroQualification heroCheck = new HeroQualification();
		SummonerQualification summonerCheck = new SummonerQualification();
		
		for (Hero hero : this.optionalHero) {
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
 