package wardzhao.org;

import java.util.HashMap;

public class SummonerQualification {

	private HashMap<String, Integer> summonerMap;
	
	private Integer MAX_HERO_COUNT = 5;
	
	public SummonerQualification() {
		super();
		
		this.summonerMap = new HashMap<String, Integer>();
	}

	public boolean canBeChosen(String summoner) {
		
		Integer heroCount = this.summonerMap.get(summoner);
		
		if (heroCount == null || heroCount < MAX_HERO_COUNT) {
			return true;
		} else {
			return false;
		}
	}
	
	public void choose(String summoner) {
		
		Integer heroCount = this.summonerMap.get(summoner);
		
		if (heroCount == null) {
			heroCount = 0;
		}
		
		this.summonerMap.put(summoner, ++heroCount);
	}
	
	
}
