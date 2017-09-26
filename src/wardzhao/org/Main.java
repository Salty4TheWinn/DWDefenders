package wardzhao.org;

public class Main {
	
	public static void main(String[] args) {
		
		BestDefenders defenders = new BestDefenders();
		defenders.loadFile();
		defenders.sort();
		defenders.choose();
		defenders.output();

	}
}
