package wardzhao.org;

import java.util.HashMap;
import java.util.HashSet;

public class HeroQualification {
	private HashMap<String, HashSet<String>> heroMap; // 组别，英雄集合
	
	public HeroQualification() {
		super();
		this.heroMap = new HashMap<String, HashSet<String>>();
		this.heroMap.put("一组", new HashSet<String>());
		this.heroMap.put("二组", new HashSet<String>());
		this.heroMap.put("三组", new HashSet<String>());
	}
	
	public void choose(String group, String hero) {
		(this.heroMap.get(group)).add(hero);
	}
	
	public boolean canBeChosen(String group, String hero) {
		return !(this.heroMap.get(group)).contains(hero);
	}
}
