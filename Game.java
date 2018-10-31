import java.util.Random;

import javax.swing.JButton;

public class Game {
	
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
		
		Game.setCurrentDiceImage(0);	
	}
	
	public static int rollDice (Team currentTeam, JButton rollDice) {	
		Random random = new Random();
		int max = 6;
		int min = 1;
		int randomNumber = random.nextInt(max + 1 - min) + min;
		
		//System.out.printf("randomNumber: %d",randomNumber);
		
		rollDice.setEnabled(false);
		
		if (Game.currentTeam.pawn[0].currentPosition == Game.currentTeam.pawn[0].homePosition &
			Game.currentTeam.pawn[1].currentPosition == Game.currentTeam.pawn[1].homePosition &
			Game.currentTeam.pawn[2].currentPosition == Game.currentTeam.pawn[2].homePosition &
			Game.currentTeam.pawn[3].currentPosition == Game.currentTeam.pawn[3].homePosition) {
			
			if (Game.currentTeam == Game.redTeam) {
				Game.currentTeam = Game.greenTeam;
			}
			else if (Game.currentTeam == Game.greenTeam) {
				Game.currentTeam = Game.yellowTeam;
			}
			else if (Game.currentTeam == Game.yellowTeam) {
				Game.currentTeam = Game.blueTeam;
			}
			else if (Game.currentTeam == Game.blueTeam) {
				Game.currentTeam = Game.redTeam;
			}
			Game.currentDice = 0;
			Main.frame.repaint();
		}
		
		switch (randomNumber) {
		case 1:
			Game.setCurrentDiceImage(1);
			return 1;
		case 2:
			Game.setCurrentDiceImage(2);
			return 2;
		case 3:
			Game.setCurrentDiceImage(3);
			return 3;
		case 4:
			Game.setCurrentDiceImage(4);
			return 4;
		case 5:
			Game.setCurrentDiceImage(5);
			return 5;
		case 6:
			Game.setCurrentDiceImage(6);
			return 6;
		}
		
		rollDice.setEnabled(false);
		
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

	public static Team getTeamOnTurn() {
		return currentTeam;
	}
	
	public static int getCurrentDice() {
		return currentDice;
	}
	
    public static void setCurrentDiceImage(int dice) {
    	currentDice = dice;
    	Main.frame.repaint();
    }

}
