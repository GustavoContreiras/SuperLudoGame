package systems;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.*;

import graphics.*;

class Game implements Observado {
	
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
	
	private List<Observador> lst = new ArrayList<Observador>(); //ArrayList de observadores
	
	public Game () {
		
		
		Main.but_rollDice.setEnabled(true);
		Main.but_saveGame.setEnabled(true);
		Main.lab_lastMove.setText("");
		
		
		//this.update("RtStL ;");
		
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
	
	public void add(Observador o) {
		System.out.println("Teste1");
		lst.add(o);
	}
	
	public void remove(Observador o) {
		lst.remove(o);
	}
	
	public void update(String s) {
		ListIterator<Observador> li = lst.listIterator();
		
		while (li.hasNext()) {
			li.next().notify(s);
		}
	}
	
	public static void save() {
				
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("LUDOGame", "ludogame"); 
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		int option = fileChooser.showDialog(fileChooser, "Salvar");
		
		if (option == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fw = new FileWriter (fileChooser.getSelectedFile() + ".ludogame")){
				
				String redPawn0posInx = String.valueOf(redTeam.pawn[0].positionInx);
				String redPawn0posStack = redTeam.pawn[0].getStackPos();
				String redPawn1posInx = String.valueOf(redTeam.pawn[1].positionInx);
				String redPawn1posStack = redTeam.pawn[1].getStackPos();
				String redPawn2posInx = String.valueOf(redTeam.pawn[2].positionInx);
				String redPawn2posStack = redTeam.pawn[2].getStackPos();
				String redPawn3posInx = String.valueOf(redTeam.pawn[3].positionInx);
				String redPawn3posStack = redTeam.pawn[3].getStackPos();
				
				String greenPawn0posInx = String.valueOf(greenTeam.pawn[0].positionInx);
				String greenPawn0posStack = greenTeam.pawn[0].getStackPos();
				String greenPawn1posInx = String.valueOf(greenTeam.pawn[1].positionInx);
				String greenPawn1posStack = greenTeam.pawn[1].getStackPos();
				String greenPawn2posInx = String.valueOf(greenTeam.pawn[2].positionInx);
				String greenPawn2posStack = greenTeam.pawn[2].getStackPos();
				String greenPawn3posInx = String.valueOf(greenTeam.pawn[3].positionInx);
				String greenPawn3posStack = greenTeam.pawn[3].getStackPos();
				
				String yellowPawn0posInx = String.valueOf(yellowTeam.pawn[0].positionInx);
				String yellowPawn0posStack = yellowTeam.pawn[0].getStackPos();
				String yellowPawn1posInx = String.valueOf(yellowTeam.pawn[1].positionInx);
				String yellowPawn1posStack = yellowTeam.pawn[1].getStackPos();
				String yellowPawn2posInx = String.valueOf(yellowTeam.pawn[2].positionInx);
				String yellowPawn2posStack = yellowTeam.pawn[2].getStackPos();
				String yellowPawn3posInx = String.valueOf(yellowTeam.pawn[3].positionInx);
				String yellowPawn3posStack = yellowTeam.pawn[3].getStackPos();
				
				String bluePawn0posInx = String.valueOf(blueTeam.pawn[0].positionInx);
				String bluePawn0posStack = blueTeam.pawn[0].getStackPos();
				String bluePawn1posInx = String.valueOf(blueTeam.pawn[1].positionInx);
				String bluePawn1posStack = blueTeam.pawn[1].getStackPos();
				String bluePawn2posInx = String.valueOf(blueTeam.pawn[2].positionInx);
				String bluePawn2posStack = blueTeam.pawn[2].getStackPos();
				String bluePawn3posInx = String.valueOf(blueTeam.pawn[3].positionInx);
				String bluePawn3posStack = blueTeam.pawn[3].getStackPos();
				
				char currentTeam = Game.currentTeam.getName().charAt(0);
				String lastPawnMovedId = String.valueOf(Game.lastPawnMoved.id);
				String oldDice = String.valueOf(Game.oldDice);
				char lastPawnMovedTeam = Game.lastPawnMoved.team.getName().charAt(0);
				
            	
            	fw.write(String.valueOf(redPawn0posInx + " " + redPawn0posStack + " "));
            	fw.write(String.valueOf(redPawn1posInx + " " + redPawn1posStack + " "));
            	fw.write(String.valueOf(redPawn2posInx + " " + redPawn2posStack + " "));
            	fw.write(String.valueOf(redPawn3posInx + " " + redPawn3posStack + " "));
            	fw.write(String.valueOf(greenPawn0posInx + " " + greenPawn0posStack + " "));
            	fw.write(String.valueOf(greenPawn1posInx + " " + greenPawn1posStack + " "));
            	fw.write(String.valueOf(greenPawn2posInx + " " + greenPawn2posStack + " "));
            	fw.write(String.valueOf(greenPawn3posInx + " " + greenPawn3posStack + " "));
            	fw.write(String.valueOf(yellowPawn0posInx + " " + yellowPawn0posStack + " "));
            	fw.write(String.valueOf(yellowPawn1posInx + " " + yellowPawn1posStack + " "));
            	fw.write(String.valueOf(yellowPawn2posInx + " " + yellowPawn2posStack + " "));
            	fw.write(String.valueOf(yellowPawn3posInx + " " + yellowPawn3posStack + " "));
            	fw.write(String.valueOf(bluePawn0posInx + " " + bluePawn0posStack + " "));
            	fw.write(String.valueOf(bluePawn1posInx + " " + bluePawn1posStack + " "));
            	fw.write(String.valueOf(bluePawn2posInx + " " + bluePawn2posStack + " "));
            	fw.write(String.valueOf(bluePawn3posInx + " " + bluePawn3posStack + " "));
            	fw.write(currentTeam + " ");
            	fw.write(oldDice + " ");
            	fw.write(lastPawnMovedId + " ");
            	fw.write(lastPawnMovedTeam);
            }
			
			catch (Exception e0) {
            	e0.printStackTrace();
            }
        }
	}
	
	public static void load() {
		
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("LUDOGame", "ludogame"); 
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileFilter(fileFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		int option = fileChooser.showDialog(fileChooser, "Carregar");	
		
		if (option == JFileChooser.APPROVE_OPTION) {
			
			new Game();
			
			File file;
		    Scanner sc;
		    
			try {
				
				file = fileChooser.getSelectedFile();
				sc = new Scanner(file);
				
				int redPawn0posInx = sc.nextInt();
				int redPawn0posStack = sc.nextInt();
				int redPawn1posInx = sc.nextInt();
				int redPawn1posStack = sc.nextInt();
				int redPawn2posInx = sc.nextInt();
				int redPawn2posStack = sc.nextInt();
				int redPawn3posInx = sc.nextInt();
				int redPawn3posStack = sc.nextInt();
				
				int greenPawn0posInx = sc.nextInt();
				int greenPawn0posStack = sc.nextInt();
				int greenPawn1posInx = sc.nextInt();
				int greenPawn1posStack = sc.nextInt();
				int greenPawn2posInx = sc.nextInt();
				int greenPawn2posStack = sc.nextInt();
				int greenPawn3posInx = sc.nextInt();
				int greenPawn3posStack = sc.nextInt();
				
				int yellowPawn0posInx = sc.nextInt();
				int yellowPawn0posStack = sc.nextInt();
				int yellowPawn1posInx = sc.nextInt();
				int yellowPawn1posStack = sc.nextInt();
				int yellowPawn2posInx = sc.nextInt();
				int yellowPawn2posStack = sc.nextInt();
				int yellowPawn3posInx = sc.nextInt();
				int yellowPawn3posStack = sc.nextInt();
				
				int bluePawn0posInx = sc.nextInt();
				int bluePawn0posStack = sc.nextInt();
				int bluePawn1posInx = sc.nextInt();
				int bluePawn1posStack = sc.nextInt();
				int bluePawn2posInx = sc.nextInt();
				int bluePawn2posStack = sc.nextInt();
				int bluePawn3posInx = sc.nextInt();
				int bluePawn3posStack = sc.nextInt();
				
				String currentTeam = sc.next();
				int oldDice = sc.nextInt();
				int lastPawnMovedId = sc.nextInt();
				String lastPawnMovedTeam = sc.next();
				
				Game.flag_firstMove = false;
				
				//currentTeam
				switch (currentTeam) {
				case "R":
					Game.currentTeam = redTeam;
					break;
				case "G":
					Game.currentTeam = greenTeam;
					break;
				case "Y":
					Game.currentTeam = yellowTeam;
					break;
				case "B":
					Game.currentTeam = blueTeam;
					break;
				}
				
				//last pawn moved
				switch (lastPawnMovedTeam) {
				case "R":
					Game.oldTeam = redTeam;
					break;
				case "G":
					Game.oldTeam = greenTeam;
					break;
				case "Y":
					Game.oldTeam = yellowTeam;
					break;
				case "B":
					Game.oldTeam = blueTeam;
					break;
				}
				
				//old dice
				Game.oldDice = oldDice;	
				
				//last pawn moved id
				Game.lastPawnMoved = Game.oldTeam.pawn[lastPawnMovedId-1];
				
				if (Game.oldDice > -1 & Game.lastPawnMoved != null) {
					Main.lab_lastMove.setText("Last move:");
				}
				
				//red 1
				redTeam.pawn[0].positionInx = redPawn0posInx;
				redTeam.pawn[0].position = redTeam.pawn[0].homePosition;
				if (redPawn0posInx > -1) {
					redTeam.pawn[0].position = redTeam.walkthrough[redPawn0posInx];
				}
				redTeam.pawn[0].position.pawn[redPawn0posStack] = redTeam.pawn[0];
				
				//red 2
				redTeam.pawn[1].positionInx = redPawn1posInx;
				redTeam.pawn[1].position = redTeam.pawn[1].homePosition;
				if (redPawn1posInx > -1) {
					redTeam.pawn[1].position = redTeam.walkthrough[redPawn1posInx];
				}
				redTeam.pawn[1].position.pawn[redPawn1posStack] = redTeam.pawn[1];
				
				//red 3
				redTeam.pawn[2].positionInx = redPawn2posInx;
				redTeam.pawn[2].position = redTeam.pawn[2].homePosition;
				if (redPawn2posInx > -1) {
					redTeam.pawn[2].position = redTeam.walkthrough[redPawn2posInx];
				}
				redTeam.pawn[2].position.pawn[redPawn2posStack] = redTeam.pawn[2];
				
				//red 4
				redTeam.pawn[3].positionInx = redPawn3posInx;
				redTeam.pawn[3].position = redTeam.pawn[3].homePosition;
				if (redPawn3posInx > -1) {
					redTeam.pawn[3].position = redTeam.walkthrough[redPawn3posInx];
				}
				redTeam.pawn[3].position.pawn[redPawn3posStack] = redTeam.pawn[3];
				
				//green 1
				greenTeam.pawn[0].positionInx = greenPawn0posInx;
				greenTeam.pawn[0].position = greenTeam.pawn[0].homePosition;
				if (greenPawn0posInx > -1) {
					greenTeam.pawn[0].position = greenTeam.walkthrough[greenPawn0posInx];
				}
				greenTeam.pawn[0].position.pawn[greenPawn0posStack] = greenTeam.pawn[0];
				
				//green 2
				greenTeam.pawn[1].positionInx = greenPawn1posInx;
				greenTeam.pawn[1].position = greenTeam.pawn[1].homePosition;
				if (greenPawn1posInx > -1) {
					greenTeam.pawn[1].position = greenTeam.walkthrough[greenPawn1posInx];
				}
				greenTeam.pawn[1].position.pawn[greenPawn1posStack] = greenTeam.pawn[1];
				
				//green 3
				greenTeam.pawn[2].positionInx = greenPawn2posInx;
				greenTeam.pawn[2].position = greenTeam.pawn[2].homePosition;
				if (greenPawn2posInx > -1) {
					greenTeam.pawn[2].position = greenTeam.walkthrough[greenPawn2posInx];
				}
				greenTeam.pawn[2].position.pawn[greenPawn2posStack] = greenTeam.pawn[2];
				
				//green 4
				greenTeam.pawn[3].positionInx = greenPawn3posInx;
				greenTeam.pawn[3].position = greenTeam.pawn[3].homePosition;
				if (greenPawn3posInx > -1) {
					greenTeam.pawn[3].position = greenTeam.walkthrough[greenPawn3posInx];
				}
				greenTeam.pawn[3].position.pawn[greenPawn3posStack] = greenTeam.pawn[3];
				
				//yellow 1
				yellowTeam.pawn[0].positionInx = yellowPawn0posInx;
				yellowTeam.pawn[0].position = yellowTeam.pawn[0].homePosition;
				if (yellowPawn0posInx > -1) {
					yellowTeam.pawn[0].position = yellowTeam.walkthrough[yellowPawn0posInx];
				}
				yellowTeam.pawn[0].position.pawn[yellowPawn0posStack] = yellowTeam.pawn[0];
				
				//yellow 2
				yellowTeam.pawn[1].positionInx = yellowPawn1posInx;
				yellowTeam.pawn[1].position = yellowTeam.pawn[1].homePosition;
				if (yellowPawn1posInx > -1) {
					yellowTeam.pawn[1].position = yellowTeam.walkthrough[yellowPawn1posInx];
				}
				yellowTeam.pawn[1].position.pawn[yellowPawn1posStack] = yellowTeam.pawn[1];
				
				//yellow 3
				yellowTeam.pawn[2].positionInx = yellowPawn2posInx;
				yellowTeam.pawn[2].position = yellowTeam.pawn[2].homePosition;
				if (yellowPawn2posInx > -1) {
					yellowTeam.pawn[2].position = yellowTeam.walkthrough[yellowPawn2posInx];
				}
				yellowTeam.pawn[2].position.pawn[yellowPawn2posStack] = yellowTeam.pawn[2];
				
				//yellow 4
				yellowTeam.pawn[3].positionInx = yellowPawn3posInx;
				yellowTeam.pawn[3].position = yellowTeam.pawn[3].homePosition;
				if (yellowPawn3posInx > -1) {
					yellowTeam.pawn[3].position = yellowTeam.walkthrough[yellowPawn3posInx];
				}
				yellowTeam.pawn[3].position.pawn[yellowPawn3posStack] = yellowTeam.pawn[3];
				
				//blue 1
				blueTeam.pawn[0].positionInx = bluePawn0posInx;
				blueTeam.pawn[0].position = blueTeam.pawn[0].homePosition;
				if (bluePawn0posInx > -1) {
					blueTeam.pawn[0].position = blueTeam.walkthrough[bluePawn0posInx];
				}
				blueTeam.pawn[0].position.pawn[bluePawn0posStack] = blueTeam.pawn[0];
				
				//blue 2
				blueTeam.pawn[1].positionInx = bluePawn1posInx;
				blueTeam.pawn[1].position = blueTeam.pawn[1].homePosition;
				if (bluePawn1posInx > -1) {
					blueTeam.pawn[1].position = blueTeam.walkthrough[bluePawn1posInx];
				}
				blueTeam.pawn[1].position.pawn[bluePawn1posStack] = blueTeam.pawn[1];
				
				//blue 3
				blueTeam.pawn[2].positionInx = bluePawn2posInx;
				blueTeam.pawn[2].position = blueTeam.pawn[2].homePosition;
				if (bluePawn2posInx > -1) {
					blueTeam.pawn[2].position = blueTeam.walkthrough[bluePawn2posInx];
				}
				blueTeam.pawn[2].position.pawn[bluePawn2posStack] = blueTeam.pawn[2];
				
				//blue 4
				blueTeam.pawn[3].positionInx = bluePawn3posInx;
				blueTeam.pawn[3].position = blueTeam.pawn[3].homePosition;
				if (bluePawn3posInx > -1) {
					blueTeam.pawn[3].position = blueTeam.walkthrough[bluePawn3posInx];
				}
				blueTeam.pawn[3].position.pawn[bluePawn3posStack] = blueTeam.pawn[3];	
			} 
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}			
	}
	
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
		
		/* O jogador que chegar com um peão à sua casa final poderá
		avançar 6 casas com qualquer um de seus outros peões. */
		
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
		
		/* O jogador que chegar com um peão à sua casa final poderá
		avançar 6 casas com qualquer um de seus outros peões. */
		
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
					
					//se tiver apenas 2 moviveis e 1 barreira
					if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {

						//se a barreira puder andar
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
						
						//se a barreira nao puder andar
						else {
							
							//se tiver mais de 2 peoes fora da casa inicial
							if (Game.currentTeam.countPawnsInHome() < 2) {
								Game.setLastDice(0);
								Main.lab_instructions.setText("Choose a pawn!");
								Main.but_rollDice.setEnabled(false);
							}
							
							else {
								Game.prepareNextTurn();
							}
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
				
				/* REGRA: Um jogador terá de mover um peão de sua casa inicial para a
				respectiva casa de saída quando, após lançar o dado, obtiver o valor 5.
				Caso isso não seja possível, quer seja porque já existe um de seus peões
				na casa de saída, ou porque já não há mais peões para serem retirados da
				casa inicial, ele deverá, então, movimentar 5 casas com outro peão qualquer. */
					
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
					
					//se tiver apenas 2 moviveis e 1 barreira
					else if (Game.currentTeam.countMovablePawns() == 2 & Game.currentTeam.countBarriers() == 1) {
						
						//se os peoes da barreira puderem andar
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
							
							else {
								System.out.println("ERROR: makeMoveAfterRollDice: positionInx > 56");
							}
						}
						
						//se os peoes da barreira nao puderem andar
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
			
			/* Nesse momento o programa irá sinalizar o corrido e exibirá a
			colocação dos jogadores por meio de um JOptionPane.showMessageDialog.*/

			Team secondTeam = Game.getSecondPlacedTeam();
			double secondScorePercent = Math.round((double) ((secondTeam.getScore() / 224.0) * 100.0));
			
			Team thirdTeam = Game.getThirdPlacedTeam();
			double thirdScorePercent = Math.round((double) ((thirdTeam.getScore() / 224.0) * 100.0));
			
			Team fourthTeam = Game.getFourthPlacedTeam();
			double fourthScorePercent = Math.round((double) ((fourthTeam.getScore() / 224.0) * 100.0));
			
			String messageWinner = Game.currentTeam.name + " Team has won the match!\n" +
							 "2nd: " + secondTeam.name + " Team (" + secondScorePercent + "%)\n" +
							 "3rd: " + thirdTeam.name + " Team (" + thirdScorePercent + "%)\n" +
							 "4th: " + fourthTeam.name + " (" + fourthScorePercent + "%)";
			
			JOptionPane.showMessageDialog(Main.frame, messageWinner);
			
			/* Após o botão de OK do diálogo acima tiver sido pressionado, o
			programa deverá exibir um segundo diálogo, que irá perguntar se os 
			jogadores querem continuar a jogar ou se querem encerrar o programa
			(JOptionPane.showConfirmDialog). Caso a resposta seja pela continuação,
			o programa deverá exibir a configuração inicial do tabuleiro. */
			
			int option = JOptionPane.showConfirmDialog(Main.frame, "Do you want to start a new game?", 
													   "Confirm", 
													   JOptionPane.YES_NO_OPTION,
										               JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_NO_OPTION) {
				new Game();
			}
			else {
				CtrlGame.destroy();
				System.exit(0);
			}
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


    private static Team getThirdPlacedTeam () {
    	
    	int higher = - 10;
    	int redTeamCount = 0;
    	int greenTeamCount = 0;
    	int yellowTeamCount = 0;
    	int blueTeamCount = 0;
    	
    	Team thirdPlacedTeam = null;
    	
    	redTeamCount = Game.redTeam.pawn[0].positionInx + Game.redTeam.pawn[1].positionInx + 
				   	   Game.redTeam.pawn[2].positionInx + Game.redTeam.pawn[3].positionInx;
	
		//se o time vermelho nao for o primeiro nem o segundo colocado
		if (redTeamCount < 56*4 & Game.redTeam != Game.getSecondPlacedTeam() & redTeamCount > higher) {
			higher = redTeamCount;
			thirdPlacedTeam = Game.redTeam;
		}
		
		greenTeamCount = Game.greenTeam.pawn[0].positionInx + Game.greenTeam.pawn[1].positionInx + 
		   		 		 Game.greenTeam.pawn[2].positionInx + Game.greenTeam.pawn[3].positionInx;
		
		//se o time verde nao for o primeiro nem o segundo colocado
		if (greenTeamCount < 56*4 & Game.greenTeam != Game.getSecondPlacedTeam() & greenTeamCount > higher) {
			higher = greenTeamCount;
			thirdPlacedTeam = Game.greenTeam;
		}
		
		yellowTeamCount = Game.yellowTeam.pawn[0].positionInx + Game.yellowTeam.pawn[1].positionInx + 
		   		 		  Game.yellowTeam.pawn[2].positionInx + Game.yellowTeam.pawn[3].positionInx;
		
		//se o time amarelo nao for o primeiro nem o segundo colocado
		if (yellowTeamCount < 56*4 & Game.yellowTeam != Game.getSecondPlacedTeam() & yellowTeamCount > higher) {
			higher = yellowTeamCount;
			thirdPlacedTeam = Game.yellowTeam;
		}
		
		blueTeamCount = Game.blueTeam.pawn[0].positionInx + Game.blueTeam.pawn[1].positionInx + 
		   		 		Game.blueTeam.pawn[2].positionInx + Game.blueTeam.pawn[3].positionInx;
		
		//se o time azul nao for o primeiro nem o segundo colocado
		if (blueTeamCount < 56*4 & Game.blueTeam != Game.getSecondPlacedTeam() & blueTeamCount > higher) {
			higher = blueTeamCount;
			thirdPlacedTeam = Game.blueTeam;
		}
		
		return thirdPlacedTeam;
    }


    private static Team getFourthPlacedTeam () {
    	int higher = - 10;
    	int redTeamCount = 0;
    	int greenTeamCount = 0;
    	int yellowTeamCount = 0;
    	int blueTeamCount = 0;
    	
    	Team fourthPlacedTeam = null;
    	
    	redTeamCount = Game.redTeam.pawn[0].positionInx + Game.redTeam.pawn[1].positionInx + 
				   	   Game.redTeam.pawn[2].positionInx + Game.redTeam.pawn[3].positionInx;
	
		//se o time vermelho nao for o primeiro nem o segundo colocado
		if (redTeamCount < 56*4 & 
			Game.redTeam != Game.getSecondPlacedTeam() & 
			Game.redTeam != Game.getThirdPlacedTeam() & 
			redTeamCount > higher) {
			
			higher = redTeamCount;
			fourthPlacedTeam = Game.redTeam;
		}
		
		greenTeamCount = Game.greenTeam.pawn[0].positionInx + Game.greenTeam.pawn[1].positionInx + 
		   		 		 Game.greenTeam.pawn[2].positionInx + Game.greenTeam.pawn[3].positionInx;
		
		//se o time verde nao for o primeiro nem o segundo colocado
		if (greenTeamCount < 56*4 & 
			Game.greenTeam != Game.getSecondPlacedTeam() & 
			Game.greenTeam != Game.getThirdPlacedTeam() & 
			greenTeamCount > higher) {
			
			higher = greenTeamCount;
			fourthPlacedTeam = Game.greenTeam;
		}
		
		yellowTeamCount = Game.yellowTeam.pawn[0].positionInx + Game.yellowTeam.pawn[1].positionInx + 
		   		 		  Game.yellowTeam.pawn[2].positionInx + Game.yellowTeam.pawn[3].positionInx;
		
		//se o time amarelo nao for o primeiro nem o segundo colocado
		if (yellowTeamCount < 56*4 & 
			Game.yellowTeam != Game.getSecondPlacedTeam() & 
			Game.yellowTeam != Game.getThirdPlacedTeam() & 
			yellowTeamCount > higher) {
			
			higher = yellowTeamCount;
			fourthPlacedTeam = Game.yellowTeam;
		}
		
		blueTeamCount = Game.blueTeam.pawn[0].positionInx + Game.blueTeam.pawn[1].positionInx + 
		   		 		Game.blueTeam.pawn[2].positionInx + Game.blueTeam.pawn[3].positionInx;
		
		//se o time azul nao for o primeiro nem o segundo colocado
		if (blueTeamCount < 56*4 & 
			Game.blueTeam != Game.getSecondPlacedTeam() & 
			Game.blueTeam != Game.getThirdPlacedTeam() & 
			blueTeamCount > higher) {
			
			higher = blueTeamCount;
			fourthPlacedTeam = Game.blueTeam;
		}
		
		return fourthPlacedTeam;
    }
}