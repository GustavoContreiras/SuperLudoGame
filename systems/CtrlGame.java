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
		
	public void doMouseClick (int x, int y) {
		Position.doMouseClick(x, y);
	}
	
	public void makeMoveAfterClick(Position posClicked, int rolledDice, Team currentTeam) {
		Game.makeMoveAfterClick(posClicked, rolledDice, currentTeam);
	}
	
	public int rollDice() {
		return Game.rollDice();
	}
	
	public void makeMoveAfterRollDice() {
		Game.makeMoveAfterRollDice();
	}
	
	//TEAM
	public Team getTeam(String team) {
		
		switch (team) {
		case "redTeam": return Game.redTeam;
		case "greenTeam": return Game.greenTeam;
		case "yellowTeam": return Game.yellowTeam;
		case "blueTeam": return Game.blueTeam;
		case "currentTeam": return Game.currentTeam;
		case "oldTeam": return Game.oldTeam;
		}
		
		return null;
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
	
	public Position getRedTeamEndPosition() {
		return Game.redTeam.walkthrough[56];
	}
	
	public Position getGreenTeamEndPosition() {
		return Game.greenTeam.walkthrough[56];
	}
	
	public Position getYellowTeamEndPosition() {
		return Game.yellowTeam.walkthrough[56];
	}
	
	public Position getBlueTeamEndPosition() {
		return Game.blueTeam.walkthrough[56];
	}
	
	//DICE	
	public int getCurrentDice () {
		return Game.currentDice;
	}
	
	public int getOldDice () {
		return Game.oldDice;
	}
	
	//PAWN
	public Position getRedPawnPosition(int pawnInx) {
		return Game.redTeam.pawn[pawnInx].position;
	}
	
	public Position getGreenPawnPosition(int pawnInx) {
		return Game.greenTeam.pawn[pawnInx].position;
	}
	
	public Position getYellowPawnPosition(int pawnInx) {
		return Game.yellowTeam.pawn[pawnInx].position;
	}
	
	public Position getBluePawnPosition(int pawnInx) {
		return Game.blueTeam.pawn[pawnInx].position;
	}
	
	public Pawn getLastPawnMoved () {
		return Game.lastPawnMoved;
	}
	
	public int getLastPawnMovedId () {
		return Game.lastPawnMoved.id;
	}
	
	public Team getLastPawnMovedTeam () {
		return Game.lastPawnMoved.team;
	}
	
	public int getPawnPositionX (String team, int pawnInx) { //Pega a coordenada X do peão
		return this.getTeam(team).pawn[pawnInx].position.x;
	}
	
	public int getPawnPositionY (String team, int pawnInx) { //Pega a coordenada Y do peão
		return this.getTeam(team).pawn[pawnInx].position.y;
	}
	
	//CHECKS
	public boolean isFirstMove() {
		return Game.flag_firstMove;
	}
	
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
	
	public boolean checkRedTeamPawnExistence (int pawnInx) {
		if (this.getRedTeam().pawn[pawnInx] != null)
			return true;
		else
			return false;
	}
	
	public boolean checkGreenTeamPawnExistence (int pawnInx) {
		if (this.getGreenTeam().pawn[pawnInx] != null)
			return true;
		else
			return false;
	}
	
	public boolean checkYellowTeamPawnExistence (int pawnInx) {
		if (this.getYellowTeam().pawn[pawnInx] != null)
			return true;
		else
			return false;
	}
	
	public boolean checkBlueTeamPawnExistence (int pawnInx) {
		if (this.getBlueTeam().pawn[pawnInx] != null)
			return true;
		else
			return false;
	}
		
	public boolean checkPawnPositionVacancy (String team, int pawnInx) { //Checa se há outro peão nessa posição
		if (this.getTeam(team).pawn[pawnInx].position.pawn[1] == null)
			return true;
		else
			return false;
	}
	
	public boolean checkPawnPositionBarrier (String team, int pawnInx) { //Checa se há outro peão nessa posição (barreira)
		
		if (this.getTeam(team).pawn[pawnInx].position.pawn[0] != null & 
			this.getTeam(team).pawn[pawnInx].position.pawn[1] != null) {
			
			if (this.getTeam(team).pawn[pawnInx].position.pawn[0].team == this.getTeam(team) &
				this.getTeam(team).pawn[pawnInx].position.pawn[1].team == this.getTeam(team)) {
				return true;
			}
		}

		return false;
	}
	
	public boolean checkPawnPositionEnemy (String team, int pawnInx) { //Checa se há outro peão nessa posição (e for um inimigo)
		if (this.getTeam(team).pawn[pawnInx].position.pawn[1].team != this.getTeam(team))
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
	
	public String comparePawnTeam (String team, int pawnInx) {
		if (this.getTeam(team).pawn[pawnInx].position.pawn[1].team == this.getTeam("redTeam"))
			return "RED";
		else if (this.getTeam(team).pawn[pawnInx].position.pawn[1].team == this.getTeam("greenTeam"))
			return "GREEN";
		else if (this.getTeam(team).pawn[pawnInx].position.pawn[1].team == this.getTeam("yellowTeam"))
			return "YELLOW";
		else if (this.getTeam(team).pawn[pawnInx].position.pawn[1].team == this.getTeam("blueTeam"))
			return "BLUE";
		else
			return "NULL";
	}

	//public void registra() {}
}
