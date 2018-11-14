package systems;

public class CtrlGame {
	private static CtrlGame ctrl = null;
	
	private CtrlGame() {
		
	}
	
	public static CtrlGame getCtrlGame() {
		if (ctrl == null)
			ctrl = new CtrlGame();
		return ctrl;
	}
	
	public static void destroyGame() {
		if (ctrl != null)
			ctrl = null;
	}
	
	public void resetGame() {
		new Game();
	}
	
	public Team getGameTeam (String var) {
		switch (var) {
			case "redTeam": return Game.redTeam;
			case "greenTeam": return Game.greenTeam;
			case "yellowTeam": return Game.yellowTeam;
			case "blueTeam": return Game.blueTeam;
			case "currentTeam": return Game.currentTeam;
			case "oldTeam": return Game.oldTeam;
			default: return null;
		}
	}
	
	public int getGameDice (String var) {
		switch (var) {
			case "oldDice": return Game.oldDice;
			case "currentDice": return Game.currentDice;
			default: return -1;
		}
	}
	
	public String getTeamName (String var) {
		switch (var) {
			case "redTeam": return Game.redTeam.name;
			case "greenTeam": return Game.greenTeam.name;
			case "yellowTeam": return Game.yellowTeam.name;
			case "blueTeam": return Game.blueTeam.name;
			case "currentTeam": return Game.currentTeam.name;
			case "oldTeam": return Game.oldTeam.name;
			default: return null;
		}
	}
	
	public void mouseClicked (int x, int y) {
		Position.mouseClicked(x, y);
	}
	
	public int rollDice() {
		return Game.rollDice();
	}
	
	public boolean checkTeamPawn (String team, int i) { //Checa se o peão existe
		if (this.getGameTeam(team).pawn[i] != null)
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionVacancy (String team, int i) { //Checa se há outro peão nessa posição
		if (this.getGameTeam(team).pawn[i].position.pawn[1] == null)
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionBarrier (String team, int i) { //Checa se há outro peão nessa posição (barreira)
		if (this.getGameTeam(team).pawn[i].position.pawn[1].team == this.getGameTeam(team) &
				this.getGameTeam(team).pawn[i].position.pawn[0].team == this.getGameTeam(team))
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionEnemy (String team, int i) { //Checa se há outro peão nessa posição (e for um inimigo)
		if (this.getGameTeam(team).pawn[i].position.pawn[1].team != this.getGameTeam(team))
			return true;
		else
			return false;
	}
	
	public String comparePawnTeam (String team, int i) {
		if (this.getGameTeam(team).pawn[i].position.pawn[1].team == this.getGameTeam("redTeam"))
			return "RED";
		else if (this.getGameTeam(team).pawn[i].position.pawn[1].team == this.getGameTeam("greenTeam"))
			return "GREEN";
		else if (this.getGameTeam(team).pawn[i].position.pawn[1].team == this.getGameTeam("yellowTeam"))
			return "YELLOW";
		else if (this.getGameTeam(team).pawn[i].position.pawn[1].team == this.getGameTeam("blueTeam"))
			return "BLUE";
		else
			return "NULL";
	}
	
	public int getPawnPositionX (String team, int i) { //Pega a coordenada X do peão
		return this.getGameTeam(team).pawn[i].position.x;
	}
	
	public int getPawnPositionY (String team, int i) { //Pega a coordenada Y do peão
		return this.getGameTeam(team).pawn[i].position.y;
	}
	
	public void makeMove(Position posClicked, int rolledDice, Team currentTeam) {
		Game.makeMove(posClicked, rolledDice, currentTeam);
	}
}
