package wardzhao.org;

import java.util.Comparator;

public class HeroComparator implements Comparator<Hero> {

	@Override
	public int compare(Hero o1, Hero o2) {

		int bossWeight1 = o1.isBoss() ? 100000 : 0;
		int bossWeight2 = o2.isBoss() ? 100000 : 0;
		
		return (bossWeight2 + o2.getPi()) - (bossWeight1 + o1.getPi());
	}

}
