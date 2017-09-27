package wardzhao.org;

import java.util.ArrayList;

public class HeroChooser {
	private HeroQualification heroCheck = null;
	private SummonerQualification summonerCheck = null;
	private ArrayList<Hero> chosenHeros = null;
	
	public HeroChooser() {
		this.heroCheck = new HeroQualification();
		this.summonerCheck = new SummonerQualification();
		this.chosenHeros = new ArrayList<Hero>();
	}
	
	public boolean canBeChosen(Hero hero) {
		return heroCheck.canBeChosen(hero.getGroup(), hero.getName()) 
				&& summonerCheck.canBeChosen(hero.getSummoner());
	}
	
	public void add(Hero hero) {
		this.heroCheck.choose(hero.getGroup(), hero.getName());
		this.summonerCheck.choose(hero.getSummoner());
		this.chosenHeros.add(hero);
	}
	
	public ArrayList<Hero> getChosenHero(){
		return this.chosenHeros;
	}
}
