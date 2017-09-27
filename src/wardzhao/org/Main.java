package wardzhao.org;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		Configuration.loadConfFile();
		
		DefenderLoader defenders = new DefenderLoader();
		ArrayList<Hero> heros = defenders.loadFile();
		
		SolutionInterface bestHeroSolution = new BestHeroSolution();
		bestHeroSolution.setCandidateHeros(heros);
		bestHeroSolution.sort();
		bestHeroSolution.chooseHeros();
		bestHeroSolution.output();
		
		SolutionInterface balanceHeroSolution = new BalanceHeroSolution();
		balanceHeroSolution.setCandidateHeros(heros);
		balanceHeroSolution.sort();
		balanceHeroSolution.chooseHeros();
		balanceHeroSolution.output();

	}
}
