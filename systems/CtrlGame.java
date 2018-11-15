package systems;

public class CtrlGame {
	private static CtrlGame ctrl = null;
	
	private CtrlGame() {
		
	}
	
	
	//GAME
	public static CtrlGame getController() {
		if (ctrl == null)
			ctrl = new CtrlGame();
		return ctrl;
	}
	
	public static void destroy() {
		if (ctrl != null)
			ctrl = null;
	}
	
	public void reset() {
		new Game();
	}
	
	public boolean isFirstMove() {
		return Game.flag_firstMove;
	}
	
	public void mouseClicked (int x, int y) {
		Position.mouseClicked(x, y);
	}
	
	public int rollDice() {
		return Game.rollDice();
	}
	
	//TEAM
	public Team getTeam (String var) {
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
	
	public Team getRedTeam () {
		return Game.redTeam;
	}
	
	public Team getGreenTeam () {
		return Game.greenTeam;
	}
	
	public Team getYellowTeam () {
		return Game.yellowTeam;
	}
	
	public Team getBlueTeam () {
		return Game.blueTeam;
	}
	
	public Team getCurrentTeam () {
		return Game.currentTeam;
	}
	
	public Team getOldTeam () {
		return Game.oldTeam;
	}
	
	public String getTeamName (Team team) {
		return team.name;
	}
	
	//DICE
	public int getDice (String var) {
		switch (var) {
			case "oldDice": return Game.oldDice;
			case "currentDice": return Game.currentDice;
			default: return -1;
		}
	}
	
	public int getCurrentDice () {
		return Game.currentDice;
	}
	
	public int getOldDice () {
		return Game.oldDice;
	}
	
	//PAWN	
	public Pawn getLastPawnMoved () {
		return Game.lastPawnMoved;
	}
	
	public int getLastPawnMovedId () {
		return Game.lastPawnMoved.id;
	}
	
	public Team getLastPawnMovedTeam () {
		return Game.lastPawnMoved.team;
	}
	
	//CHECKS
	public boolean checkAllPawnsInHome () {
		
		if (CtrlGame.getController().getCurrentTeam() != null) {
		
			int pawnsInHome = 0;
			
			for (int i = 0; i < 4; i++) {
				
				if (Game.redTeam.pawn[i].positionInx == -1) {
					pawnsInHome++;
				}
				
				if (Game.greenTeam.pawn[i].positionInx == -1) {
					pawnsInHome++;
				}
				
				if (Game.yellowTeam.pawn[i].positionInx == -1) {
					pawnsInHome++;
				}
				
				if (Game.blueTeam.pawn[i].positionInx == -1) {
					pawnsInHome++;
				}
			}
			
			if (pawnsInHome == 16) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}
	
	public boolean checkTeamPawn (String team, int i) { //Checa se o peão existe
		if (this.getTeam(team).pawn[i] != null)
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionVacancy (String team, int i) { //Checa se há outro peão nessa posição
		if (this.getTeam(team).pawn[i].position.pawn[1] == null)
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionBarrier (String team, int i) { //Checa se há outro peão nessa posição (barreira)
		if (this.getTeam(team).pawn[i].position.pawn[1].team == this.getTeam(team) &
				this.getTeam(team).pawn[i].position.pawn[0].team == this.getTeam(team))
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionEnemy (String team, int i) { //Checa se há outro peão nessa posição (e for um inimigo)
		if (this.getTeam(team).pawn[i].position.pawn[1].team != this.getTeam(team))
			return true;
		else
			return false;
	}
	
	public boolean checkLastPawnMoved () { //Checa se existe um peao que foi movido
		if (Game.lastPawnMoved != null)
			return true;
		else
			return false;
	}
	
	public String comparePawnTeam (String team, int i) {
		if (this.getTeam(team).pawn[i].position.pawn[1].team == this.getTeam("redTeam"))
			return "RED";
		else if (this.getTeam(team).pawn[i].position.pawn[1].team == this.getTeam("greenTeam"))
			return "GREEN";
		else if (this.getTeam(team).pawn[i].position.pawn[1].team == this.getTeam("yellowTeam"))
			return "YELLOW";
		else if (this.getTeam(team).pawn[i].position.pawn[1].team == this.getTeam("blueTeam"))
			return "BLUE";
		else
			return "NULL";
	}
	
	public int getPawnPositionX (String team, int i) { //Pega a coordenada X do peão
		return this.getTeam(team).pawn[i].position.x;
	}
	
	public int getPawnPositionY (String team, int i) { //Pega a coordenada Y do peão
		return this.getTeam(team).pawn[i].position.y;
	}
	
	public void makeMove(Position posClicked, int rolledDice, Team currentTeam) {
		Game.makeMove(posClicked, rolledDice, currentTeam);
	}
}
