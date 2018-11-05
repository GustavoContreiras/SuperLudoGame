import java.util.Random;

import javax.swing.JButton;

public class Game {
	
	static Pawn lastPawnMoved = null;
	static Team redTeam = null;
	static Team greenTeam = null;
	static Team yellowTeam = null;
	static Team blueTeam = null;
	static Team currentTeam;
	static int currentDice = -1;
	
	public Game() {
				
		Main.but_rollDice.setEnabled(true);
		Main.but_saveGame.setEnabled(true);
		Main.lab_rolledA.setText("");
		
		Game.currentTeam = null;
		Game.redTeam = new Team("Red");
		Game.greenTeam = new Team("Green");
		Game.yellowTeam = new Team("Yellow");
		Game.blueTeam = new Team("Blue");
		Game.currentTeam = Game.setTeamOnTurn();
		
		Game.resetPositions();
		Game.insertPawnsInHomePositions();
		Game.setCurrentDice(0);	
	}
	
	public static void insertPawnsInHomePositions() {
		Position.B5.pawn[0] = Game.redTeam.pawn[0];
		Position.E5.pawn[0] = Game.redTeam.pawn[1];
		Position.B2.pawn[0] = Game.redTeam.pawn[2];
		Position.E2.pawn[0] = Game.redTeam.pawn[3];
		
		Position.K2.pawn[0] = Game.greenTeam.pawn[0];
		Position.K5.pawn[0] = Game.greenTeam.pawn[1];
		Position.N2.pawn[0] = Game.greenTeam.pawn[2];
		Position.N5.pawn[0] = Game.greenTeam.pawn[3];
		
		Position.N11.pawn[0] = Game.yellowTeam.pawn[0];
		Position.K11.pawn[0] = Game.yellowTeam.pawn[1];
		Position.N14.pawn[0] = Game.yellowTeam.pawn[2];
		Position.K14.pawn[0] = Game.yellowTeam.pawn[3];
		
		Position.E14.pawn[0] = Game.blueTeam.pawn[0];
		Position.E11.pawn[0] = Game.blueTeam.pawn[1];
		Position.B14.pawn[0] = Game.blueTeam.pawn[2];
		Position.B11.pawn[0] = Game.blueTeam.pawn[3];
	}
	
	public static void nextTurn () {
		
		Game.setCurrentDice(0);
		Main.lab_rolledA.setText(""); //nao funciona
		Game.currentTeam.dicesRolled = 0;
		Game.currentTeam = Game.setTeamOnTurn();
		Main.but_rollDice.setEnabled(true);
		Main.frame.repaint();
	}
	
	public static int rollDice (Team currentTeam, JButton rollDice) {	
		Random random = new Random();
		int max = 6;
		int min = 1;
		int randomNumber = random.nextInt(max + 1 - min) + min;
		
		rollDice.setEnabled(false);
		
		switch (randomNumber) {
		
		case 1:
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam = Game.setTeamOnTurn();
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
				return 0;
			}
			else {
				Game.setCurrentDice(1);
				return 1;
			}
			
		case 2:
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam = Game.setTeamOnTurn();
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
				return 0;
			}
			else {
				Game.setCurrentDice(2);
				return 2;
			}
			
		case 3:
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam = Game.setTeamOnTurn();
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
				return 0;
			}
			else {
				Game.setCurrentDice(3);
				return 3;
			}
			
		case 4:
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam = Game.setTeamOnTurn();
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
				return 0;
			}
			else {
				Game.setCurrentDice(4);
				return 4;
			}
			
		case 5:
			Game.setCurrentDice(5);
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam.pawn[0].detachFromPos(Game.currentTeam.pawn[0].currentPosition);
				Game.currentTeam.pawn[0].addPosition(1);
				Game.currentTeam.pawn[0].attachToPos(Game.currentTeam.pawn[0].currentPosition, Game.currentTeam.pawn[0]);
				Game.nextTurn();
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
			}
			
			return 5;
			
		case 6:
			Game.setCurrentDice(6);
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam.pawn[0].detachFromPos(Game.currentTeam.pawn[0].currentPosition);
				Game.currentTeam.pawn[0].addPosition(1);
				Game.currentTeam.pawn[0].attachToPos(Game.currentTeam.pawn[0].currentPosition, Game.currentTeam.pawn[0]);
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
			}
			
			Game.currentTeam.dicesRolled += 1;
			Game.lastPawnMoved = Game.currentTeam.pawn[0];
			
			if (Game.currentTeam.dicesRolled == 3) {
				Game.lastPawnMoved.currentPositionInx = -1;
				Game.lastPawnMoved.currentPosition = Game.lastPawnMoved.homePosition;
				Game.nextTurn();
			}
			
			return 6;
		}
		
		return 0;
	}

	public static Team setTeamOnTurn() {
	    	if (Game.currentTeam == null) {
	    		Random random = new Random();
	    		int max = 4;
	    		int min = 1;
	    		int randomNumber = random.nextInt(max + 1 - min) + min;
	    		switch (randomNumber) {
	    		case 1:
	    			return Game.redTeam;
	    		case 2:
	    			return Game.greenTeam;
	    		case 3:
	    			return Game.yellowTeam;
	    		case 4:
	    			return Game.blueTeam;
				default:
					return null;
	    		}
	    	}
	    	else if (Game.currentTeam == Game.redTeam) {
	    		return Game.greenTeam;
	    	}
	    	else if (Game.currentTeam == Game.greenTeam) {
	    		return Game.yellowTeam;
	    	}
	    	else if (Game.currentTeam == Game.yellowTeam) {
	    		return Game.blueTeam;
	    	}
	    	else if (Game.currentTeam == Game.blueTeam) {
	    		return Game.redTeam;
	    	}
	    	else {
	    		return null;
	    	}
	    }

    public static void setCurrentDice(int dice) {
    	currentDice = dice;
    	Main.frame.repaint();
    }

    private static void resetPositions() {
    	if (Game.redTeam != null & Game.greenTeam != null & Game.yellowTeam != null & Game.blueTeam != null) {
			for (int i = 0; i < 57; i++) {
				Game.redTeam.walkthrough[i].pawn[0] = null;
				Game.redTeam.walkthrough[i].pawn[1] = null;
			}
			for (int i = 0; i < 57; i++) {
				Game.greenTeam.walkthrough[i].pawn[0] = null;
				Game.greenTeam.walkthrough[i].pawn[1] = null;
			}
			for (int i = 0; i < 57; i++) {
				Game.yellowTeam.walkthrough[i].pawn[0] = null;
				Game.yellowTeam.walkthrough[i].pawn[1] = null;
			}
			for (int i = 0; i < 57; i++) {
				Game.blueTeam.walkthrough[i].pawn[0] = null;
				Game.blueTeam.walkthrough[i].pawn[1] = null;
			}
		}
    }
}
