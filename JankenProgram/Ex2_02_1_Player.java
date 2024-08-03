class Ex2_02_1_Player {
	
	private String name       ; //player name
	private String handStatus ;
	private final String rockHand    = "グー"   ;
	private final String scissorHand = "チョキ" ;
	private final String paperHand   = "パー"   ;
	
	Ex2_02_1_Player(String nm){
		this.name = nm;
	}
	
	public void makeHandStatus() {
		String [] handType = {rockHand, scissorHand, paperHand};
		int handTypeNum = new java.util.Random().nextInt(2);
		handStatus = handType[handTypeNum];
	}

	public String getName() {
		return name;
	}

	public String getHandStatus() {
		return handStatus;
	}

	
}
