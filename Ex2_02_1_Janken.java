class Ex2_02_1_Janken {
	public static void main (String[] args) {
		
		String name1     = args[0] ;
		String name2     = args[1] ;
		String name3     = args[2] ;
	
		
		Ex2_02_1_Player playerName1 = new Ex2_02_1_Player(name1);
		Ex2_02_1_Player playerName2 = new Ex2_02_1_Player(name2);
		Ex2_02_1_Refree refreeName = new Ex2_02_1_Refree(name3);
	
		refreeName.startGame();
		
		playerName1.makeHandStatus();
		playerName2.makeHandStatus();
		
		refreeName.handStatusJudge(playerName1, playerName2);
		
		refreeName.sayJudgeResult();
		
		refreeName.judge(playerName1, playerName2);
		
		
		
	}
}
