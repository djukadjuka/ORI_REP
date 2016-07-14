package mainPackage.mainClasses.supportPackage;

public class Result {
	private int playerCompResult;
	private int compCompResult;
	
	public Result(){
		playerCompResult = 0;
		compCompResult = 0;
	}
	public void addToPlayerCompResult(int points){
		playerCompResult += points;
	}
	public void addToCompCompResult(int points){
		compCompResult += points;
	}
	public int getPlayerCompResult() {
		return playerCompResult;
	}
	public void setPlayerCompResult(int playerCompResult) {
		this.playerCompResult = playerCompResult;
	}
	public int getCompCompResult() {
		return compCompResult;
	}
	public void setCompCompResult(int compCompResult) {
		this.compCompResult = compCompResult;
	}
	@Override
	public String toString(){
		return "Player,Comp : " + playerCompResult + "| Comp,Comp : " + compCompResult;
	}
}
