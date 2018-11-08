
public class Game {
	
	static Pawn lastPawnMoved = null;
	static Team redTeam = null;
	static Team greenTeam = null;
	static Team yellowTeam = null;
	static Team blueTeam = null;
	static Team currentTeam;
	static Team oldTeam;
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
		Pawn pawnClicked = null;
		
		//pega os peoes dessa posicao
		if (posClicked.pawn[0] != null) {
			pawn1 = posClicked.pawn[0];		
			
			if (posClicked.pawn[1] != null) {
				pawn2 = posClicked.pawn[1];
			}
		}
		
		
		//pega o peao dessa posicao que pertence ao time corrente
		if (pawn1 != null & pawn1.team == currentTeam) {
			pawnClicked = pawn1;
		}
		else if (pawn2 != null & pawn2.team == currentTeam) {
			pawnClicked = pawn2;
		}
		else {
			pawnClicked = null;
			System.out.println("Position do not have pawn of the current team.");
		}
		
		//PAWN CLICKED NA CASA INICIAL
		if (pawnClicked.positionInx == -1) {
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
				Main.lab_oldTeam.setText("");
				
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
				Main.lab_oldTeam.setText("");
				Main.but_rollDice.setEnabled(true);
			}
		}
	}
	
	public static void prepareNextTurn () {
		
		Game.oldTeam = Game.currentTeam;
		Game.setCurrentDice(0);
		Game.setTeamOnTurn();
		Game.currentTeam.dicesSixRolled = 0;
		
		Main.lab_rolledA.setText("");
		Main.lab_oldTeam.setText("");
		Main.lab_instructions.setText("");
		Main.but_rollDice.setEnabled(true);
		Main.frame.repaint();
	}
	
	public static int rollDice () {
		
		/* REGRA: De um modo geral, todas as jogadas que n�o precisarem de inter-
		ven��o do jogador ter�o de ser feitas automaticamente pelo programa. */
		
		Main.but_rollDice.setEnabled(false);
		
		int randomNumber = (int) (Math.random() * 6 + 1);
		
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
			
			//se tiver 3 peoes na casa inicial...
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				//anda automaticamente com o que nao ta na casa inicial
				Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
				Game.prepareNextTurn();
			}
			
			//se tiver 2 peoes na casa inicial...
			else if (Game.currentTeam.hasTwoPawnsInHome()) {
				
				//se tiver uma barreira formada
				if (Game.currentTeam.getNumberOfBarriers() > 0) {
					
					//anda automaticamente com algum da barreira
					Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
					Game.prepareNextTurn();
				}
				else {
					Game.oldTeam = Game.currentTeam;
					Main.lab_instructions.setText("Choose a pawn!");
					Main.but_rollDice.setEnabled(false);
				}
			}
		}
		
		else if (randomNumber == 5) {
			
			/* REGRA: Um jogador ter� de mover um pe�o de sua casa inicial para a
			respectiva casa de sa�da quando, ap�s lan�ar o dado, obtiver o valor 5.
			Caso isso n�o seja poss�vel, quer seja porque j� existe um de seus pe�es
			na casa de sa�da, ou porque j� n�o h� mais pe�es para serem retirados da
			casa inicial, ele dever�, ent�o, movimentar 5 casas com outro pe�o qualquer. */
			
			if (Game.currentTeam.hasAllPawnsInHome()) {
				
				Game.currentTeam.pawn[0].walk(1);
				Game.prepareNextTurn();
			}
			
			//se tiver 3 peoes na casa inicial...
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				//se o peao fora da casa inicial estiver na casa de saida
				if (Game.currentTeam.getPawnOnExitHouse() != null) {
					
					Game.currentTeam.getPawnOnExitHouse().walk(Game.currentDice);
					Game.prepareNextTurn();
				}
				
				//se o peao fora da casa inicial nao estiver na casa de saida (tira um da casa inicial)
				else {
					for (int i = 0; i < 4; i++) {
						if (Game.currentTeam.pawn[i].positionInx == -1) {
							Game.currentTeam.pawn[i].walk(1);
							Game.prepareNextTurn();
							i = 4;
						}
					}
				}
			}
			
			//se tiver 2 peoes na casa inicial...
			else if (Game.currentTeam.hasTwoPawnsInHome()) {
				
				//se tiver uma barreira formada
				if (Game.currentTeam.getNumberOfBarriers() > 0) {
					
					//anda automaticamente com algum da barreira
					Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
					Game.prepareNextTurn();
				}
				
				//se nao tiver barreira formada
				else {
					
					//se tiver peao na casa de saida...
					if (Game.currentTeam.getPawnOnExitHouse() != null) {
						Game.oldTeam = Game.currentTeam;
						Main.lab_instructions.setText("Choose a pawn!");
						Main.but_rollDice.setEnabled(false);
					}
					
					//se nao tiver peao na casa de saida...
					else {
						for (int i = 0; i < 4; i++) {
							if (Game.currentTeam.pawn[i].positionInx == -1) {
								Game.currentTeam.pawn[i].walk(1);
								Game.prepareNextTurn();
								i = 4;
							}
						}
					}
				}
			}
		}
			
		else if (randomNumber == 6) {
			
			/* REGRA: O jogador que tiver dois de seus pe�es formando uma barreira e
			obtiver um 6 ap�s lan�ar o dado estar� obrigado a abrir essa barreira. A
			�nica exce��o a esta regra ocorrer� quando um pe�o da barreira n�o puder
			ser movimentado. Caso existam duas barreiras, ser� desfeita aquela que 
			estiver mais pr�xima da reta final do jogador. */
			
			/* REGRA: O jogador que obtiver um 6 poder� jogar o dado outra vez, ap�s
			movimentar um de seus pe�es. Se obtiver novamente 6, poder� realizar novo
			lan�amento, ap�s movimentar um de seus pe�es. Se obtiver um 6 pela terceira
			vez consecutiva, o �ltimo de seus pe�es que foi movimentado voltar� para a
			casa inicial. */
			
			Main.but_rollDice.setEnabled(true);
			
			Game.oldTeam = Game.currentTeam;
			
			//4 pe�es na casa inicial
			if (Game.currentTeam.hasAllPawnsInHome()) {
				
				Game.currentTeam.pawn[0].walk(1);
				Game.setCurrentDice(0);
			}
			
			//3 pe�es na casa inicial
			else if (Game.currentTeam.hasThreePawnsInHome()) {
				
				//se o peao fora da casa inicial estiver na casa de saida
				if (Game.currentTeam.getPawnOnExitHouse() != null) {
					
					Game.currentTeam.getPawnOnExitHouse().walk(Game.currentDice);
					Game.setCurrentDice(0);
				}
				
				//se o peao fora da casa inicial nao estiver na casa de saida
				else {
					Game.oldTeam = Game.currentTeam;
					Main.lab_instructions.setText("Choose a pawn!");
					Main.but_rollDice.setEnabled(false);
				}
			}
			
			//2 pe�es na casa inicial
			else if (Game.currentTeam.hasTwoPawnsInHome()) {
				
				//se tiver uma barreira formada
				if (Game.currentTeam.getNumberOfBarriers() > 0) {
					
					//anda automaticamente com algum da barreira
					Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
				}
				else {
					Game.oldTeam = Game.currentTeam;
					Main.lab_instructions.setText("Choose a pawn!");
					Main.but_rollDice.setEnabled(false);
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
    	if (dice > 0) 
    		System.out.printf("\n" + Game.currentTeam.name + " team rolled %d.", dice);
    	
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