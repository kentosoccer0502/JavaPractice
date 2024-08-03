class Ex2_02_1_Refree {
	
	private String name       ; //player 

	private final String rockHand    = "グー"   ;
	private final String scissorHand = "チョキ" ;
	private final String paperHand   = "パー"   ;	
	
	Ex2_02_1_Refree(String nm){
		this.name      = nm ;
	}
	
	private void refreeMessage(String msg) {
		System.out.println(name + "審判「" + msg + "」");
	}
	
	public void startGame() {
		refreeMessage("じゃんけん・・・ぽん！！！！");
	}
	
	public void handStatusJudge(Ex2_02_1_Player p1, Ex2_02_1_Player p2) {
		refreeMessage(p1.getName() + "さんの手：" + p1.getHandStatus() + "でした！");
		refreeMessage(p2.getName() + "さんの手：" + p2.getHandStatus() + "でした！");
	}
	
	public void sayJudgeResult() {
		System.out.println("結果は・・・");
	}
	
	public void judge(Ex2_02_1_Player p1, Ex2_02_1_Player p2) {
		if(p1.getHandStatus().equals(p2.getHandStatus())) {
			refreeMessage("あいこ！勝負つかず！");
		}else if(p1.getHandStatus().equals(rockHand) && p2.getHandStatus().equals(scissorHand) ||
				p1.getHandStatus().equals(scissorHand) && p2.getHandStatus().equals(paperHand) ||
				p1.getHandStatus().equals(paperHand) && p2.getHandStatus().equals(rockHand)) {
			refreeMessage(p1.getName() + "さんの勝利！");
		}else if(p2.getHandStatus().equals(rockHand) && p1.getHandStatus().equals(scissorHand) ||
				p2.getHandStatus().equals(scissorHand) && p1.getHandStatus().equals(paperHand) ||
				p2.getHandStatus().equals(paperHand) && p1.getHandStatus().equals(rockHand)) {
			refreeMessage(p2.getName() + "さんの勝利！");
		}
	}
}
