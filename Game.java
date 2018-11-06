
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
		
		Game.resetPositions();
		Game.setCurrentDice(0);
		Game.setTeamOnTurn();
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
			System.out.println("Position do not have pawn of the current team.");
		}
		
		//PAWN CLICKED NA CASA INICIAL
		if (pawnClicked.currentPositionInx == -1) {
			System.out.println("Pawn is on home.");
			
			//ROLLED DICE EH 5
			if (Game.currentDice == 5) {
				pawnClicked.walk(1);
				Game.prepareNextTurn();
			}
			
			//ROLLED DICE EH 6
			else if (Game.currentDice == 6) {
				Game.currentTeam.dicesSixRolled += 1;
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
				Game.prepareNextTurn();
			}
			
			else {
				Game.setCurrentDice(0);
				Main.but_rollDice.setEnabled(true);
			}
		}
		
		Main.frame.repaint();
	}
	
	public static void prepareNextTurn () {
		
		Game.setCurrentDice(0);
		Game.setTeamOnTurn();
		Game.currentTeam.dicesSixRolled = 0;
		
		Main.lab_rolledA.setText(""); //nao funciona
		Main.but_rollDice.setEnabled(true);
		Main.frame.repaint();
	}
	
	public static int rollDice () {
		
		int randomNumber = (int) (Math.random() * 6 + 1);
		
		Main.but_rollDice.setEnabled(false);
		
		switch (randomNumber) {
			case 1:	Game.setCurrentDice(1);	break;
			case 2: Game.setCurrentDice(2); break;	
			case 3: Game.setCurrentDice(3); break;
			case 4: Game.setCurrentDice(4); break;
			case 5: Game.setCurrentDice(5);	break;
			case 6: Game.setCurrentDice(6);	break;
		}
		
		if (randomNumber < 5) {
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				Game.prepareNextTurn();
			}
			
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				Pawn pawnOutOfHome = Game.currentTeam.getPawnOutOfHome();
				pawnOutOfHome.walk(Game.currentDice);
				
				if (Game.currentTeam.walkthrough[0].pawn[0] != null) {
					Game.currentTeam.hasPawnOnExitHouse = true;
				}
				else {
					Game.currentTeam.hasPawnOnExitHouse = false;
				}
				
				Game.prepareNextTurn();
			}
		}
		
		else if (randomNumber == 5) {
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				
				Game.currentTeam.pawn[0].walk(1);
				Game.currentTeam.hasPawnOnExitHouse = true;
				Game.prepareNextTurn();
			}
			
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				//se o quarto peao tiver na casa de saida
				if (Game.currentTeam.hasPawnOnExitHouse) {
					
					Pawn pawnOnExitHouse = Game.currentTeam.walkthrough[0].pawn[0];
					pawnOnExitHouse.walk(Game.currentDice);
					Game.currentTeam.hasPawnOnExitHouse = false;
					
					Game.prepareNextTurn();
				}
				
				//se o quarto peao nao tiver na casa inicial nem na de saida
				else {
					Main.lab_instructions.setText("Choose a pawn!");
					Main.frame.repaint();
				}
			}
		}
		
		else if (randomNumber == 6) {
			
			Main.but_rollDice.setEnabled(true);
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				
				Game.currentTeam.pawn[0].walk(1);
				Game.setCurrentDice(0);
				Game.currentTeam.hasPawnOnExitHouse = true;
			}
			
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				//se o quarto peao tiver na casa de saida
				if (Game.currentTeam.hasPawnOnExitHouse) {
					
					Pawn pawnOnExitHouse = Game.currentTeam.walkthrough[0].pawn[0];
					pawnOnExitHouse.walk(Game.currentDice);
					Game.currentTeam.hasPawnOnExitHouse = false;
				}
				
				//se o quarto peao nao tiver na casa inicial nem na de saida
				else {
					Main.lab_instructions.setText("Choose a pawn!");
				}
			}
		}
		
		return randomNumber;
	}

	public static void setTeamOnTurn() {
		
		//se ninguem tiver jogado ainda
    	if (Game.currentTeam == null) {
    		
    		int randomNumber = (int) (Math.random() * 4 + 1);
    		
    		switch (randomNumber) {
    		case 1:
    			Game.currentTeam = Game.redTeam;
    			break;
    		case 2:
    			Game.currentTeam = Game.greenTeam;
    			break;
    		case 3:
    			Game.currentTeam = Game.yellowTeam;
    		case 4:
    			Game.currentTeam = Game.blueTeam;
    			break;
			default:
				Game.currentTeam = null;
				break;
    		}
    	}
    	
    	//se alguem ja tiver jogado
    	else {
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
    		else {
        		Game.currentTeam = null;
        	}
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