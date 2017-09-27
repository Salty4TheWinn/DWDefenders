package wardzhao.org;

import java.util.HashMap;
import java.util.HashSet;

public class HeroQualification {
	private HashMap<String, HashSet<String>> heroMap; // 组别，英雄集合
	
	public HeroQualification() {
		super();
		this.heroMap = new HashMap<String, HashSet<String>>();
		this.heroMap.put(Configuration.properties.getProperty("group1"), new HashSet<String>());
		this.heroMap.put(Configuration.properties.getProperty("group2"), new HashSet<String>());
		this.heroMap.put(Configuration.properties.getProperty("group3"), new HashSet<String>());
	}
	
	public void choose(String group, String hero) {
		(this.heroMap.get(group)).add(hero);
	}
	
	public boolean canBeChosen(String group, String hero) {
		HashSet<String> heroSet = this.heroMap.get(group);
		return !heroSet.contains(hero);
	}
}
