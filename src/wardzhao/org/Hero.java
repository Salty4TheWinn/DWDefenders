package wardzhao.org;

public class Hero {
	
	private String name; // Ӣ����
	private Integer pi; // ս��
	private String summoner; // ���
	private String group; // ���
	private boolean isBoss; // ����boss ����ȷ��
	
	public Hero(String name, Integer pi, String summoner, String group, boolean isBoss) {
		super();
		
		this.name = name;
		this.pi = pi;
		this.summoner = summoner;
		this.group = group;
		this.setBoss(isBoss);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSummoner() {
		return summoner;
	}

	public void setSummoner(String summoner) {
		this.summoner = summoner;
	}

	public Integer getPi() {
		return pi;
	}

	public void setPi(Integer pi) {
		this.pi = pi;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}


}
