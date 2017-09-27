package wardzhao.org;

import java.util.ArrayList;
import java.util.HashMap;

public class BalanceHeroSolution extends BestHeroSolution {

	private ArrayList<Summoner> summoners = null;
	
	@Override
	protected void setOutputFilename() {
		OUTPUT_FILE_NAME = "布阵（1最强英雄2最弱玩家3最强英雄）.txt";
	}
	
	@Override
	public void sort() {
		// 英雄按照战力降序排序
		this.candidateHeros.sort(null);
		
		//玩家按照战力升序排序
		this.createSummonerList();
		this.summoners.sort(null);
	}

	@Override
	public void chooseHeros() {
		
		HeroChooser heroChooser = new HeroChooser();
		
		Integer firstLowerBound = Integer.valueOf(Configuration.properties.getProperty("first_lower_bound"));
		
		// 第一轮按照英雄战力降序开始选满足 配置下限的全部英雄
		for (Hero hero : this.candidateHeros) {
			if (hero.getPi() > firstLowerBound /* 战力超过下限 */ && heroChooser.canBeChosen(hero)) {
				heroChooser.add(hero);
			}
		}
		
		// 第二轮从战力升序的玩家list：this.summoners各选2个英雄
		for (Summoner summoner : this.summoners) {
			for (Hero hero : summoner.getHeros()) {
				if (heroChooser.canBeChosen(hero)) {
					heroChooser.add(hero);
				}
			}
		}
		
		
		// 第三轮按照英雄战力降序开始选全部英雄
		for (Hero hero : this.candidateHeros) {
			if (heroChooser.canBeChosen(hero)) {
				heroChooser.add(hero);
			}
		}
		
		this.chosenHeros = heroChooser.getChosenHero();
	}
	
	private void createSummonerList() {
		
		this.summoners = new ArrayList<Summoner>();
		// create summoner map
		HashMap<String, Summoner> mapSummoner = new HashMap<String, Summoner>();

		// fill summoner's heros
		Summoner summoner = null;
		for (Hero hero : this.candidateHeros) {

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
		for (Summoner player : mapSummoner.values()) {
			player.CalculatePI();
			this.summoners.add(player);
		}

	}

}
