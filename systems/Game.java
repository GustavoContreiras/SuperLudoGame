package systems;

import javax.swing.JOptionPane;

import graphics.*;

class Game /*implements ObservadoIF*/ {
	
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
	public static boolean flag_skipTurn = false;
	
	public Game () {
				
		Main.but_rollDice.setEnabled(true);
		Main.but_saveGame.setEnabled(true);
		Main.lab_lastMove.setText("");
		
		Game.currentTeam = null;
		Game.redTeam = new Team("Red");
		Game.greenTeam = new Team("Green");
		Game.yellowTeam = new Team("Yellow");
		Game.blueTeam = new Team("Blue");
		Game.flag_firstMove = true;
		
		Game.resetPositions();
		Game.setCurrentDice(0);
		Game.setLastDice(0);
		Game.setTeamOnTurn();
	}
	
	//public void add(ObservadorIF o) {}
	
	//public void remove(ObservadorIF o) {}
	
	/*public void get(int i) {
		switch (i) {
		case 1:
			return algumaCoisa;
		case 2:
			return outraCoisa;
		}
	}*/
	
	public static Pawn getPawnClicked(Position posClicked) {
		
		Pawn pawn1 = null;
		Pawn pawn2 = null;
		
		if (posClicked.pawn[0] != null) {
			pawn1 = posClicked.pawn[0];					
		}
		
		if (posClicked.pawn[1] != null) {
			pawn2 = posClicked.pawn[1];
		}
		
		if (pawn1 != null & pawn1.team == currentTeam) {
			return pawn1;
		}
		else if (pawn2 != null & pawn2.team == currentTeam) {
			return pawn2;
		}
		else {
			System.out.println("Position do not have pawn of the current team.");
			return null;
		}
	}
	
	public static void makeMoveAfterFinish () {
		
		Game.setCurrentDice(6);
		
		//se tiver peao movivel
		if (Game.currentTeam.countMovablePawns() > 0) {	
			
			//se for 1 peao movivel
			if (Game.currentTeam.countMovablePawns() == 1) {
				
				//anda e passa o turno
				Game.currentTeam.getFirstPawnOutOfHome().walk(Game.currentDice);
				Game.prepareNextTurn();
			}
			
			//se forem 2 em uma barreira
			else if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {
				
				//se forem chegar ao fim
				if (Game.currentTeam.getPawnOnFirstBarrier().positionInx + Game.currentDice == 56) {
					
					//anda com os dois e passa o turno
					Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
					Game.currentTeam.getFirstPawnOutOfHome().walk(Game.currentDice);
					Game.prepareNextTurn();
				}
				
				//se nao forem chegar ao fim
				else {
					
					//anda com um e passa o turno
					Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
					Game.prepareNextTurn();
					
				}
			}
			
			else {
				Main.lab_instructions.setText("Choose a pawn!");
				Main.but_rollDice.setEnabled(false);
				Game.flag_skipTurn = true;
			}
			
		}
	
		//se nao tiver peao movivel
		else if (Game.currentTeam.countMovablePawns() == 0) {
			Game.prepareNextTurn();
		}
		
		else {
			System.out.println("ERROR: makeMoveAfterFinish: movable pawns < 0");
		}
		
	}
	
	public static void makeMoveAfterClick (Position posClicked, int rolledDice, Team currentTeam) {
		
		/* O jogador que chegar com um pe�o � sua casa final poder�
		avan�ar 6 casas com qualquer um de seus outros pe�es. */
		
		Pawn pawnClicked = Game.getPawnClicked(posClicked);
		
		System.out.printf("\nCurrent team has %d movable pawn(s).\n", Game.currentTeam.countMovablePawns());
		
		System.out.printf("\nCurrent team has %d barrier(s).\n", Game.currentTeam.countBarriers());
		
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
				}
			}
			else {
				System.out.println("Exit house already have a pawn of current team (choose another).");
				Main.lab_instructions.setText("Choose another.");
			}
		}
		
		//PAWN CLICKED FORA DA CASA INICIAL
		else {
			
			System.out.println("Pawn clicked is out from home.");
			
			//se puder andar
			if (pawnClicked.canWalk(Game.currentDice)) {
				
				System.out.println("Pawn clicked can walk.");
				
				Game.setLastDice(Game.currentDice);
				pawnClicked.walk(Game.currentDice);
								
				//se o peao nao tiver chego ao fim
				if (Game.lastPawnMoved.positionInx < 56) {
					
					if (Game.currentDice != 6) {
						Game.prepareNextTurn();
					}
					else {
						
						if (Game.flag_skipTurn == true) {
							Game.prepareNextTurn();
						}
						else {
							Game.setCurrentDice(0);
							Main.lab_instructions.setText("");
							Main.but_rollDice.setEnabled(true);	
						}
					}
					
				}
				
				//se o peao tiver chego ao fim
				else if (Game.lastPawnMoved.positionInx == 56) {
					Game.makeMoveAfterFinish();
				}
				
				else {
					System.out.println("ERROR: makeMoveAfterClick: positionInx > 56");
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
		
		/* O jogador que chegar com um pe�o � sua casa final poder�
		avan�ar 6 casas com qualquer um de seus outros pe�es. */
		
		Pawn savedLastPawnMoved = Game.lastPawnMoved;
		Game.lastPawnMoved = null;
			
		//se tiver peao movivel
		if (Game.currentTeam.countMovablePawns() > 0) {
			
			System.out.printf("\nCurrent team has %d movable pawn(s).\n", Game.currentTeam.countMovablePawns());
		
			//se tirar menos que 5
			if (Game.currentDice < 5) {
				
				//se tiver um peao movivel
				if (Game.currentTeam.countMovablePawns() == 1) {
					
					Game.currentTeam.getUniqueMovablePawn(Game.currentDice).walk(Game.currentDice);
					
					//se o peao nao tiver chego ao fim
					if (Game.lastPawnMoved.positionInx < 56) {
						Game.prepareNextTurn();
					}
					
					//se o peao tiver chego ao fim
					else if (Game.lastPawnMoved.positionInx == 56) {
						
						Game.makeMoveAfterFinish();
					}
					
					else {
						System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
					}
				}
				
				//se tiver mais de um peao movivel
				else if (Game.currentTeam.countMovablePawns() > 1) {
					
					//se tiver apenas 2 moviveis e pertencerem a uma barreira
					if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {

						//se puder andar
						if (Game.currentTeam.getPawnOnFirstBarrier().canWalk(Game.currentDice)) {
							
							//anda automaticamente
							Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
							
							//se o peao nao tiver chego ao fim
							if (Game.lastPawnMoved.positionInx < 56) {
								Game.prepareNextTurn();
							}
							
							//se o peao tiver chego ao fim
							else if (Game.lastPawnMoved.positionInx == 56) {
								Game.makeMoveAfterFinish();
							}
						}
						
						//se nao puder andar
						else {
							Game.prepareNextTurn();
						}
						
					}
					
					else {
						Game.setLastDice(0);
						Main.lab_instructions.setText("Choose a pawn!");
						Main.but_rollDice.setEnabled(false);
					}
				}
				
				//se nao tiver peao movivel
				else if (Game.currentTeam.countMovablePawns() == 0) {
					Game.prepareNextTurn();
				}
				
				else {
					System.out.println("ERROR: makeMoveAfterRollDice: movable pawns < 0");
				}
			}
			
			//se tirar 5
			else if (Game.currentDice == 5) {
				
				/* REGRA: Um jogador ter� de mover um pe�o de sua casa inicial para a
				respectiva casa de sa�da quando, ap�s lan�ar o dado, obtiver o valor 5.
				Caso isso n�o seja poss�vel, quer seja porque j� existe um de seus pe�es
				na casa de sa�da, ou porque j� n�o h� mais pe�es para serem retirados da
				casa inicial, ele dever�, ent�o, movimentar 5 casas com outro pe�o qualquer. */
					
				//se tiver um peao movivel
				if (Game.currentTeam.countMovablePawns() == 1) {
					
					if (Game.currentTeam.getUniqueMovablePawn(Game.currentDice).positionInx == -1) {
						Game.currentTeam.getUniqueMovablePawn(Game.currentDice).walk(1);
					}
					else {
						Game.currentTeam.getUniqueMovablePawn(Game.currentDice).walk(Game.currentDice);
					}
					
					//se o peao nao tiver chego ao fim
					if (Game.lastPawnMoved.positionInx < 56) {
						Game.prepareNextTurn();
					}
					
					//se o peao tiver chego ao fim
					else if (Game.lastPawnMoved.positionInx == 56) {
						Game.makeMoveAfterFinish();
					}
					
					else {
						System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
					}
				}
				
				//se tiver mais de um peao movivel
				else if (Game.currentTeam.countMovablePawns() > 1) {
					
					//se a casa de saida estiver disponivel e tiver peao pra sair
					if (Game.currentTeam.canLeaveHome() == true & Game.currentTeam.getFirstPawnInHome() != null) {
						Game.currentTeam.getFirstPawnInHome().walk(1);					
						Game.prepareNextTurn();	
					}
					
					//se tiver apenas 2 moviveis e pertencerem a uma barreira
					else if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {
						
						//anda automaticamente
						Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
						
						//se o peao nao tiver chego ao fim
						if (Game.lastPawnMoved.positionInx < 56) {
							Game.prepareNextTurn();
						}
						
						//se o peao tiver chego ao fim
						else if (Game.lastPawnMoved.positionInx == 56) {
							Game.makeMoveAfterFinish();
						}
						
						else {
							System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
						}
					}
					
					else {
						Game.setLastDice(0);
						Main.lab_instructions.setText("Choose a pawn!");
						Main.but_rollDice.setEnabled(false);
					}
				}
				
				//se nao tiver peao movivel
				else {
					Game.prepareNextTurn();
				}
			}
				
			//se tirar 6
			else if (Game.currentDice == 6) {
				
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
				
				if (Game.currentTeam.hasFinished) {
					Game.prepareNextTurn();
				}
				
				Main.but_rollDice.setEnabled(true);
				
				Game.setOldTeam(Game.currentTeam);
				
				Game.currentTeam.dicesSixRolled += 1;
				
				//se tiver tirado 6 no dado 3 vezes seguidas
				if (Game.currentTeam.dicesSixRolled == 3) {
					
					if (savedLastPawnMoved != null) {
						System.out.printf("\nSending to home pawn %d (%s Team).", savedLastPawnMoved.id, savedLastPawnMoved.team.name);
						savedLastPawnMoved.position.pawn[0] = null;
						savedLastPawnMoved.position = savedLastPawnMoved.homePosition;
						savedLastPawnMoved.positionInx = -1;
						savedLastPawnMoved.homePosition.pawn[0] = savedLastPawnMoved;
						Game.lastPawnMoved = savedLastPawnMoved;
					}
					
					Game.prepareNextTurn();
				}	
				
				else {
					
					//se tiver um peao movivel
					if (Game.currentTeam.countMovablePawns() == 1) {
						Game.currentTeam.getUniqueMovablePawn(Game.currentDice).walk(Game.currentDice);
						Game.setCurrentDice(0);
					}
					
					//se tiver mais de um peao movivel
					else if (Game.currentTeam.countMovablePawns() > 1) {
						
						//se tiver 4 moviveis e pertencerem a 2 barreiras
						if (Game.currentTeam.countMovablePawns() == 4 & Game.currentTeam.countBarriers() == 2) {
							
							//anda automaticamente
							Game.currentTeam.getPawnClosestToFinish().walk(Game.currentDice);
							
							//se o peao nao tiver chego ao fim
							if (Game.lastPawnMoved.positionInx < 56) {
								Game.setCurrentDice(0);
							}
							
							//se o peao tiver chego ao fim
							else if (Game.lastPawnMoved.positionInx == 56) {
								Game.makeMoveAfterFinish();
							}
							
							else {
								System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
							}
						}
						
						//se tiver 3 peoes moviveis e 1 barreira
						else if (Game.currentTeam.countMovablePawns() == 3 & Game.currentTeam.countBarriers() == 1) {
							
							//anda automaticamente
							Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
							
							//se o peao nao tiver chego ao fim
							if (Game.lastPawnMoved.positionInx < 56) {
								Game.setCurrentDice(0);
							}
							
							//se o peao tiver chego ao fim
							else if (Game.lastPawnMoved.positionInx == 56) {
								Game.makeMoveAfterFinish();
							}
							
							else {
								System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
							}
						}
						
						//se tiver apenas 2 moviveis e tiver uma barreira
						else if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {
							
							//se o peao da barreira puder andar
							if (Game.currentTeam.getPawnOnFirstBarrier().canWalk(Game.currentDice)) {
								
								//anda automaticamente
								Game.currentTeam.getPawnOnFirstBarrier().walk(Game.currentDice);
								
								//se o peao nao tiver chego ao fim
								if (Game.lastPawnMoved.positionInx < 56) {
									Game.setCurrentDice(0);
								}
								
								//se o peao tiver chego ao fim
								else if (Game.lastPawnMoved.positionInx == 56) {
									Game.makeMoveAfterFinish();
								}
								
								else {
									System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
								}
							}
							
							
							//se o peao da barreira nao puder andar
							else {
								Game.setLastDice(0);
								Main.lab_instructions.setText("Choose a pawn!");
								Main.but_rollDice.setEnabled(false);
							}							
						}
						
						else {
							Game.setLastDice(0);
							Main.lab_instructions.setText("Choose a pawn!");
							Main.but_rollDice.setEnabled(false);
						}
					}
					
					//se nao tiver peao movivel
					else {
						Game.prepareNextTurn();
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
		
		if (Game.currentTeam.hasFinished) {
			
			/* Nesse momento o programa ir� sinalizar o corrido e exibir� a
			coloca��o dos jogadores por meio de um JOptionPane.showMessageDialog.*/

			Team secondTeam = Game.getSecondPlacedTeam();
			float secondScorePercent = (float) ((secondTeam.getScore() / 224.0) * 100.0);
			
			String thirdTeam = "Blue Team";
			int thirdScore = 0;
			
			String fourthTeam = "Green Team";
			int fourthScore = 0;
			
			String message = Game.currentTeam.name + " Team has won the match!\n" +
							 "2nd: " + secondTeam.name + " Team (" + secondScorePercent + "%)\n" +
							 "3rd: " + thirdTeam + " (" + thirdScore + ")\n" +
							 "4th: " + fourthTeam + " (" + fourthScore + ")";
			
			JOptionPane.showMessageDialog(Main.frame, message);
			
			/* Ap�s o bot�o de OK do di�logo acima tiver sido pressionado, o
			programa dever� exibir um segundo di�logo, que ir� perguntar se os 
			jogadores querem continuar a jogar ou se querem encerrar o programa
			(JOptionPane.showConfirmDialog). Caso a resposta seja pela continua��o,
			o programa dever� exibir a configura��o inicial do tabuleiro. */
			
			//...
		}
		
		else {
			Game.setCurrentDice(0);
			Game.setTeamOnTurn();
			Game.currentTeam.dicesSixRolled = 0;
			
			Main.lab_instructions.setText("");
			Main.but_rollDice.setEnabled(true);
		}
	}
		
	public static int rollDice () {
		
		/* REGRA: De um modo geral, todas as jogadas que n�o precisarem de inter-
		ven��o do jogador ter�o de ser feitas automaticamente pelo programa. */
		
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
			Main.lab_lastMove.setText("Last move:");
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
    	//Main.frame.repaint();
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

    private static Team getSecondPlacedTeam () {
    	
    	int higher = -10;
    	int redTeamCount = 0;
    	int greenTeamCount = 0;
    	int yellowTeamCount = 0;
    	int blueTeamCount = 0;
    	
    	Team secondPlacedTeam = null;
    	
    	redTeamCount = Game.redTeam.pawn[0].positionInx + Game.redTeam.pawn[1].positionInx + 
    				   Game.redTeam.pawn[2].positionInx + Game.redTeam.pawn[3].positionInx;
    	
    	//se nao for o primeiro colocado
    	if (redTeamCount < 56*4 & redTeamCount > higher) {
    		higher = redTeamCount;
    		secondPlacedTeam = Game.redTeam;
    	}
    	
    	greenTeamCount = Game.greenTeam.pawn[0].positionInx + Game.greenTeam.pawn[1].positionInx + 
		   		 		 Game.greenTeam.pawn[2].positionInx + Game.greenTeam.pawn[3].positionInx;
    	
    	//se nao for o primeiro colocado
    	if (greenTeamCount < 56*4 & greenTeamCount > higher) {
    		higher = greenTeamCount;
    		secondPlacedTeam = Game.greenTeam;
    	}
    	
    	yellowTeamCount = Game.yellowTeam.pawn[0].positionInx + Game.yellowTeam.pawn[1].positionInx + 
		   		 		  Game.yellowTeam.pawn[2].positionInx + Game.yellowTeam.pawn[3].positionInx;
    	
    	//se nao for o primeiro colocado
    	if (yellowTeamCount < 56*4 & yellowTeamCount > higher) {
    		higher = yellowTeamCount;
    		secondPlacedTeam = Game.yellowTeam;
    	}
    	
    	blueTeamCount = Game.blueTeam.pawn[0].positionInx + Game.blueTeam.pawn[1].positionInx + 
		   		 		Game.blueTeam.pawn[2].positionInx + Game.blueTeam.pawn[3].positionInx;
    	
    	//se nao for o primeiro colocado
    	if (blueTeamCount < 56*4 & blueTeamCount > higher) {
    		higher = blueTeamCount;
    		secondPlacedTeam = Game.blueTeam;
    	}
    	
    	return secondPlacedTeam;
    }

}