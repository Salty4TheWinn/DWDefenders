package wardzhao.org;

import java.util.HashMap;
import java.util.HashSet;

public class HeroQualification {
	private HashMap<String, HashSet<String>> heroMap; // ���Ӣ�ۼ���
	
	public HeroQualification() {
		super();
		this.heroMap = new HashMap<String, HashSet<String>>();
		this.heroMap.put("һ��", new HashSet<String>());
		this.heroMap.put("����", new HashSet<String>());
		this.heroMap.put("����", new HashSet<String>());
	}
	
	public void choose(String group, String hero) {
		(this.heroMap.get(group)).add(hero);
	}
	
	public boolean canBeChosen(String group, String hero) {
		return !(this.heroMap.get(group)).contains(hero);
	}
}
