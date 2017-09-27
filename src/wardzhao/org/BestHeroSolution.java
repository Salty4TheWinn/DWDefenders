package wardzhao.org;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BestHeroSolution implements SolutionInterface {
	
	protected String OUTPUT_FILE_NAME = null;
	protected ArrayList<Hero> candidateHeros = null;
	protected ArrayList<Hero> chosenHeros = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void setCandidateHeros(ArrayList<Hero> heros) {
		this.candidateHeros = (ArrayList<Hero>) heros.clone();
	}
	
	@Override
	public void sort() {
		this.candidateHeros.sort(new HeroComparator() );
	}

	@Override
	public void chooseHeros() {
		HeroChooser heroChooser = new HeroChooser();
		
		for (Hero hero : this.candidateHeros) {
			if (heroChooser.canBeChosen(hero)) {
				heroChooser.add(hero);
			}
		}
		
		this.chosenHeros = heroChooser.getChosenHero();
	}

	protected void setOutputFilename() {
		OUTPUT_FILE_NAME = "布阵（最强英雄优先）.txt";
	}
	
	@Override
	public void output() {
		String line = null;
		boolean isFirst = true;
		String firstLine = "英雄\t成员\t战力\t组别\r\n";
		int rowNumber = 0;
		
		try {
			this.setOutputFilename();
			FileOutputStream fos = new FileOutputStream(OUTPUT_FILE_NAME);
			OutputStreamWriter osr = new OutputStreamWriter(fos, "GBK");
			BufferedWriter bw = new BufferedWriter(osr);
			
			for (Hero hero : this.chosenHeros) {
				
				rowNumber++;
				
				// skip first line
				if (isFirst) {
					isFirst = false;
					bw.write(firstLine);
				}
				
			 line = hero.getName() + "\t"+
			 hero.getSummoner() + "\t" +
			 hero.getPi() + "\t" + 
			 hero.getGroup() + "\r\n";
			 
			 bw.write(line);
			    
			} // while
			
			bw.close();
			osr.close();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("row number = " + rowNumber);
			e.printStackTrace();
		} 

		
	}

}
