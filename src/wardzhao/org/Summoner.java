/**
 * 
 */
package wardzhao.org;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Ward Zhao
 * 这是玩家类
 */
public class Summoner implements Comparable<Summoner>{

	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	private String group = null;
	
	private String summonerName = null;
	
	private Integer pi = 0; // average of top 10 heros
	
	private BestHeroComparator comparator = new BestHeroComparator();
	
	public Summoner(String group, String summonerName) {
		this.setGroup(group);
		this.setSummonerName(summonerName);
	}
	
	public void addHero(Hero hero) {
		this.heros.add(hero);
	}
	
	public void CalculatePI() {
		// sort hero by pi
		heros.sort(this.comparator);

		// get average pi of top 10 heros
		int totalPi = 0;
		int i = 0;
		while (i < heros.size() && i < 10) {
			totalPi = totalPi + heros.get(i).getPi();
			i ++;
		}
		
		this.pi = totalPi / (i + 1);
	}

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getPi() {
		return pi;
	}

	public void setPi(Integer pi) {
		this.pi = pi;
	}

	@Override
	public int compareTo(Summoner o) {
		return this.pi - o.pi;
	}
	
	private class BestHeroComparator implements Comparator<Hero> {

		@Override
		public int compare(Hero o1, Hero o2) {
			
			return o2.getPi() - o1.getPi();
		}

	}
}
