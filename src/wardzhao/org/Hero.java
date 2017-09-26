package wardzhao.org;

public class Hero {
	
	private String name; // 英雄名
	private Integer pi; // 战力
	private String summoner; // 玩家
	private String group; // 组别
	private boolean isBoss; // 防守boss 优先确定
	
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
