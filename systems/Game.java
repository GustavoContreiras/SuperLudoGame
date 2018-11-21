package systems;

import javax.swing.JOptionPane;

import graphics.*;

class Game {
	
	public static Team redTeam = null;
	public static Team greenTeam = null;
	public static Team yellowTeam = null;
	public static Team blueTeam = null;
	public static Team currentTeam;
	public static Team oldTeam;
	
	static Pawn lastPawnMoved = null;
	public static int oldDice = 0;
	public static int currentDice = -1;
	public static boolean flag_firstMove = true;
	
	public Game () {
				
		Main.but_rollDice.setEnabled(true);
		Main.but_saveGame.setEnabled(true);
		
		Main.lab_lastMove.setText("Last move:");
		
		Game.currentTeam = null;
		Game.redTeam = new Team("Red");
		Game.greenTeam = new Team("Green");
		Game.yellowTeam = new Team("Yellow");
		Game.blueTeam = new Team("Blue");
		
		Game.resetPositions();
		Game.setCurrentDice(0);
		Game.setLastDice(0);
		Game.setTeamOnTurn();
	}
	
	public static void makeMoveAfterClick (Position posClicked, int rolledDice, Team currentTeam) {
		
		Pawn pawn1 = null;
		Pawn pawn2 = null;
		Pawn pawnClicked = null;
		
		if (posClicked.pawn[0] != null) {
			pawn1 = posClicked.pawn[0];					
		}
		
		if (posClicked.pawn[1] != null) {
			pawn2 = posClicked.pawn[1];
		}
		
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
			
			//se nao tiver peao na casa de saida
			if (Game.currentTeam.getPawnOnExitHouse() == null) {
			
				//ROLLED DICE EH 5
				if (Game.currentDice == 5) {
					pawnClicked.walk(1);
					Game.setLastDice(Game.currentDice);
					Game.prepareNextTurn();
				}
				
				else {
					System.out.println("Choose another pawn.");
					Main.lab_instructions.setText("Choose another.");
					Main.frame.repaint();
				}
			}
			
			//se tiver peao na casa de saida diferente do time corrente
			else if (Game.currentTeam.getPawnOnExitHouse().team != Game.currentTeam) {
				
				//ROLLED DICE EH 5
				if (Game.currentDice == 5) {
					Game.setLastDice(Game.currentDice);
					pawnClicked.walk(1);
					Game.prepareNextTurn();
				}
				
				//ROLLED DICE EH MENOR QUE 5 OU 6
				else {
					System.out.println("Choose another pawn.");
					Main.lab_instructions.setText("Choose another.");
					Main.frame.repaint();
				}
			}
			else {
				System.out.println("Exit house already have a pawn of current team (choose another).");
				Main.lab_instructions.setText("Choose another.");
				Main.frame.repaint();
			}
		}
		
		//PAWN CLICKED FORA DA CASA INICIAL
		else {
			
			System.out.println("Pawn clicked is out from home.");
			
			//se puder andar
			if (pawnClicked.canWalk(rolledDice)) {
				
				System.out.println("Pawn clicked can walk.");
				
				Game.setLastDice(Game.currentDice);
				pawnClicked.walk(rolledDice);
				
				if (rolledDice != 6) {
					Game.prepareNextTurn();
				}
				
				else {
					Game.setCurrentDice(0);
					Main.but_rollDice.setEnabled(true);
				}
			}
			
			//se nao puder andar
			else {
				
				System.out.println("Pawn clicked can not walk.");
				
				Main.lab_instructions.setText("Choose another!");
			}
				
		}
	}
	
	public static void makeMoveAfterRollDice () {
		
		Pawn savedLastPawnMoved = Game.lastPawnMoved;
		Game.lastPawnMoved = null;
			
		//se tiver peao movivel
		if (Game.currentTeam.countMovablePawns() > 0) {
			
			System.out.println("\nCurrent team has movable pawn.");
		
			//se tirar menos que 5
			if (Game.currentDice < 5) {
				
				switch(Game.currentTeam.countPawnsInHome()) {
				case 0:
				case 1:
					
					//manda escolher um peao
					Game.setLastDice(0);
					Main.lab_instructions.setText("Choose a pawn!");
					Main.but_rollDice.setEnabled(false);
					break;
					
				case 2:
					
					//se tiver uma barreira formada
					if (Game.currentTeam.countBarriers() > 0) {
											
						//se puder andar
						if (Game.currentTeam.getPawnOutOfHome().canWalk(Game.currentDice)) {
							
							//anda automaticamente com algum da barreira
							Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);	
						}
						
						Game.prepareNextTurn();
						
					}
					
					//se nao tiver barreira
					else {
						
						//manda escolher um peao
						Game.setLastDice(0);
						Main.lab_instructions.setText("Choose a pawn!");
						Main.but_rollDice.setEnabled(false);
					}
					
					break;
					
				case 3:
					
					//se puder andar
					if (Game.currentTeam.getPawnOutOfHome().canWalk(Game.currentDice)) {
						Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
					}
					Game.prepareNextTurn();
					break;
					
				case 4:
					Game.prepareNextTurn();
					break;
				}
			}
			
			//se tirar 5
			else if (Game.currentDice == 5) {
				
				/* REGRA: Um jogador terá de mover um peão de sua casa inicial para a
				respectiva casa de saída quando, após lançar o dado, obtiver o valor 5.
				Caso isso não seja possível, quer seja porque já existe um de seus peões
				na casa de saída, ou porque já não há mais peões para serem retirados da
				casa inicial, ele deverá, então, movimentar 5 casas com outro peão qualquer. */
						
				switch(Game.currentTeam.countPawnsInHome()) {
				case 0:
				case 1:
					
					//se tiver peao na casa de saida...
					if (Game.currentTeam.getPawnOnExitHouse() != null) {
						Game.setLastDice(0);
						Main.lab_instructions.setText("Choose a pawn!");
						Main.but_rollDice.setEnabled(false);
					}
					
					//se nao tiver peao na casa de saida...
					else {
						
						Game.oldTeam = Game.currentTeam;
						
						for (int i = 0; i < 4; i++) {
							if (Game.currentTeam.pawn[i].positionInx == -1) {
								if (Game.currentTeam.pawn[i].canWalk(1)) {
									Game.currentTeam.pawn[i].walk(1);
								}
								Game.prepareNextTurn();
								i = 4;
							}
						}
					}
					
					break;
						
				case 2:
					
					//se tiver peao na casa de saida...
					if (Game.currentTeam.getPawnOnExitHouse() != null) {
						
						//se tiver uma barreira formada
						if (Game.currentTeam.countBarriers() > 0) {
							
							Game.oldTeam = Game.currentTeam;
							
							if (Game.currentTeam.getPawnOutOfHome().canWalk(Game.currentDice)) {
								
								//anda automaticamente com algum da barreira
								Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
							}
							
							Game.prepareNextTurn();
						}
						
						//se nao tiver barreira formada
						else {
							Game.setLastDice(0);
							Main.lab_instructions.setText("Choose a pawn!");
							Main.but_rollDice.setEnabled(false);
						}
					}
					
					//se nao tiver peao na casa de saida...
					else {
						for (int i = 0; i < 4; i++) {
							if (Game.currentTeam.pawn[i].positionInx == -1) {
								
								//se puder andar
								if (Game.currentTeam.pawn[i].canWalk(Game.currentDice)) {
									Game.currentTeam.pawn[i].walk(1);
									i = 4;
								}
								Game.prepareNextTurn();
							}
						}
					}
							
					break;
					
				case 3:
					
					Game.oldTeam = Game.currentTeam;
					
					//se o peao fora da casa inicial estiver na casa de saida
					if (Game.currentTeam.getPawnOnExitHouse() != null) {
						
						//se puder andar
						if (Game.currentTeam.getPawnOnExitHouse().canWalk(Game.currentDice)) {
							Game.currentTeam.getPawnOnExitHouse().walk(Game.currentDice);
						}
						Game.prepareNextTurn();
					}
					
					//se o peao fora da casa inicial nao estiver na casa de saida (tira um da casa inicial)
					else {
						for (int i = 0; i < 4; i++) {
							if (Game.currentTeam.pawn[i].positionInx == -1) {
								
								//se puder andar
								if (Game.currentTeam.pawn[i].canWalk(Game.currentDice)) {
									Game.currentTeam.pawn[i].walk(1);		
									i = 4;
								}
								Game.prepareNextTurn();
							}
						}
					}
					
					break;
					
				case 4:
					
					if (Game.currentTeam.pawn[0].canWalk(1)) {
						Game.currentTeam.pawn[0].walk(1);
					}
					Game.prepareNextTurn();
					break;
				}
			}
				
			//se tirar 6
			else if (Game.currentDice == 6) {
				
				/* REGRA: O jogador que tiver dois de seus peões formando uma barreira e
				obtiver um 6 após lançar o dado estará obrigado a abrir essa barreira. A
				única exceção a esta regra ocorrerá quando um peão da barreira não puder
				ser movimentado. Caso existam duas barreiras, será desfeita aquela que 
				estiver mais próxima da reta final do jogador. */
				
				/* REGRA: O jogador que obtiver um 6 poderá jogar o dado outra vez, após
				movimentar um de seus peões. Se obtiver novamente 6, poderá realizar novo
				lançamento, após movimentar um de seus peões. Se obtiver um 6 pela terceira
				vez consecutiva, o último de seus peões que foi movimentado voltará para a
				casa inicial. */
				
				Main.but_rollDice.setEnabled(true);
				
				Game.setOldTeam(Game.currentTeam);
				
				Game.currentTeam.dicesSixRolled += 1;
				
				//se tiver tirado 6 no dado 3 vezes seguidas
				if (Game.currentTeam.dicesSixRolled == 3) {
					
					if (savedLastPawnMoved != null) {
						System.out.printf("\nSending to home pawn %d (%s Team).", savedLastPawnMoved.id, savedLastPawnMoved.team.name);
						savedLastPawnMoved.position = savedLastPawnMoved.homePosition;
						savedLastPawnMoved.positionInx = -1;
						savedLastPawnMoved.homePosition.pawn[0] = savedLastPawnMoved;
						Game.lastPawnMoved = savedLastPawnMoved;
					}
					
					Game.prepareNextTurn();
				}	
				
				else {
					switch(Game.currentTeam.countPawnsInHome()) {
					case 0:
						
						//se tiver duas barreiras formadas
						if (Game.currentTeam.countBarriers() == 2) {
							
							if (Game.currentTeam.getPawnClosestToFinish().canWalk(Game.currentDice)) {
								Game.currentTeam.getPawnClosestToFinish().walk(Game.currentDice);
							}
							Game.setCurrentDice(0);
						}
						
						//se tiver uma barreira formada
						else if (Game.currentTeam.countBarriers() == 1) {
							if (Game.currentTeam.getPawnOnBarrier().canWalk(Game.currentDice)) {
								Game.currentTeam.getPawnOnBarrier().walk(Game.currentDice);
							}
							Game.setCurrentDice(0);
						}
						
						//se nao tiver barreira formada
						else {
							Game.setLastDice(0);
							Main.lab_instructions.setText("Choose a pawn!");
							Main.but_rollDice.setEnabled(false);
						}
						
						break;
						
					case 1:
					case 2:
						
						//se tiver uma barreira formada
						if (Game.currentTeam.countBarriers() == 1) {

							if (Game.currentTeam.getPawnOutOfHome().canWalk(Game.currentDice)) {
								Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);	
							}
							Game.setCurrentDice(0);
						}
						
						//se nao tiver barreira formada
						else {
							Game.setLastDice(0);
							Main.lab_instructions.setText("Choose a pawn!");
							Main.but_rollDice.setEnabled(false);
						}
						
						break;
						
					case 3:
						
						if (Game.currentTeam.getPawnOutOfHome().canWalk(Game.currentDice)) {
							Game.currentTeam.getPawnOutOfHome().walk(Game.currentDice);
						}
						Game.setCurrentDice(0);
						break;
						
					case 4:	
						Game.setCurrentDice(0);
						break;
						
					}
				}
			}
		}
		
		//se nao tiver peao pra ser movido
		else {
			
			System.out.println("\nCurrent team has not any movable pawn.");
			
			if (Game.currentDice != 6) {
				Game.prepareNextTurn();
			}
			
			else {
				
				Game.currentTeam.dicesSixRolled++;
				
				if (Game.currentTeam.dicesSixRolled == 3) {
					Game.prepareNextTurn();
				}
				
				Main.but_rollDice.setEnabled(true);
			}
			
			Game.setCurrentDice(0);

		}
	}
	
	public static void prepareNextTurn () {
	
		Game.setCurrentDice(0);
		Game.setTeamOnTurn();
		Game.currentTeam.dicesSixRolled = 0;
		
		Main.lab_instructions.setText("");
		Main.but_rollDice.setEnabled(true);
	}
	
	public static int rollDice () {
		
		/* REGRA: De um modo geral, todas as jogadas que não precisarem de inter-
		venção do jogador terão de ser feitas automaticamente pelo programa. */
		
		int numberCheated = 0;
		
		if (Main.debugMode) {
			
			String cheatRollDice = JOptionPane.showInputDialog("Choose how much you want to roll:");
			
			if (cheatRollDice == null) {
				numberCheated = 0;
			}
			else {
				
				numberCheated = Integer.parseInt(cheatRollDice);
				
				if (numberCheated < 0 | numberCheated > 6) {
					numberCheated = 0;
				}
			}
			
	        System.out.printf("\nNumber rolled by cheating: %d", numberCheated);
		}
		
		Game.setLastDice(Game.currentDice);
		Game.setOldTeam(Game.currentTeam);
		
		if (Game.flag_firstMove == true) {
			Game.flag_firstMove = false;
		}
		
		Main.but_rollDice.setEnabled(false);
		
		int randomNumber = 0;
		
		if (numberCheated == 0) {
			randomNumber = (int) (Math.random() * 6 + 1);
		}
		else {
			randomNumber = numberCheated;
		}
		
		switch (randomNumber) {
			case 1:	Game.setCurrentDice(1); oldDice = 1; break;
			case 2: Game.setCurrentDice(2); oldDice = 2; break;	
			case 3: Game.setCurrentDice(3); oldDice = 3; break;
			case 4: Game.setCurrentDice(4); oldDice = 4; break;
			case 5: Game.setCurrentDice(5);	oldDice = 5; break;
			case 6: Game.setCurrentDice(6);	oldDice = 6; break;
		}
		
		return randomNumber;
	}

	public static void setTeamOnTurn () {
		
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
    	if (Game.currentTeam != null)
    		System.out.printf("\nTeam on turn: %s Team\n", Game.currentTeam.name);
    }

    public static void setCurrentDice (int dice) {
    	currentDice = dice;
    }
    
    public static void setLastDice (int dice) {
    	oldDice = dice;
    	Main.frame.repaint();
    }

    public static void setOldTeam (Team team) {
    	Game.oldTeam = team;
    }
    
    private static void resetPositions () {
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