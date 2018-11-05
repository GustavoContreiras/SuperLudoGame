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
		Game.setCurrentDice(0);	
	}
	
	public static void makeMove(Position posClicked, int rolledDice, Team currentTeam) {
		
		Pawn pawn1 = null;
		Pawn pawn2 = null;
		Pawn pawn3 = null;
		Pawn pawn4 = null;
		Pawn pawnClicked = null;
		
		//pega os peoes dessa posicao
		if (posClicked.pawn[0] != null) {
			pawn1 = posClicked.pawn[0];		
			
			if (posClicked.pawn[1] != null) {
				pawn2 = posClicked.pawn[1];
				
				if (posClicked.pawn[2] != null) {
					pawn3 = posClicked.pawn[2];
					
					if (posClicked.pawn[3] != null) {
						pawn4 = posClicked.pawn[3];
					}
				}
			}
		}
		
		
		//pega o peao dessa posicao que pertence ao time corrente
		if (pawn1 != null & pawn1.team == currentTeam) {
			pawnClicked = pawn1;
		}
		else if (pawn2 != null & pawn2.team == currentTeam) {
			pawnClicked = pawn2;
		}
		else if (pawn3 != null & pawn3.team == currentTeam) {
			pawnClicked = pawn3;
		}
		else if (pawn4 != null & pawn4.team == currentTeam) {
			pawnClicked = pawn4;
		}
		else {
			pawnClicked = null;
		}
		
		//PAWN CLICKED NA CASA INICIAL
		if (pawnClicked.currentPositionInx == -1) {
			System.out.println("Pawn is on home.");
			
			//ROLLED DICE EH 5
			if (Game.currentDice == 5) {
				pawnClicked.walk(1);
				Game.nextTurn();
			}
			
			//ROLLED DICE EH 6
			else if (Game.currentDice == 6) {
				Game.currentTeam.dicesRolled += 1;
				Game.setCurrentDice(0);
				Main.but_rollDice.setEnabled(true);
				
				pawnClicked.walk(1);
			}
			
			//ROLLED DICE EH MENOR QUE 5
			else {
				System.out.println("Choose another pawn.");
			}
		}
		
		//PAWN CLICKED FORA DA CASA INICIAL
		else {
			System.out.println("Pawn is out from home.");
			pawnClicked.walk(rolledDice);
			
			if (rolledDice != 6) {
				Game.nextTurn();
			}
			
			else {
				Game.setCurrentDice(0);
				Main.but_rollDice.setEnabled(true);
			}
		}
		
		Main.frame.repaint();
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
				Game.currentTeam.pawn[0].walk(1);
				Game.setCurrentDice(0);
				rollDice.setEnabled(true);
				Game.nextTurn();
			}
			
			return 5;
			
		case 6:
			Game.setCurrentDice(6);
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.currentTeam.pawn[0].walk(1);
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