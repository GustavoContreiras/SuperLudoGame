import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Main extends JFrame implements ActionListener,MouseListener {
	
	//to remove warning
	private static final long serialVersionUID = 4582338413465297014L;
	
	//IMAGES POSITION
	public static int img_diceX = 670;
	public static int img_diceY = 275;
	
	//BUTTONS POSITION, DIMENSION AND FONT
	public static int but_width = 150;
	public static int but_height = 35;
	public static int but_menuX = 622;
	public static int but_newGameY = 40;
	public static int but_loadGameY = 100;
	public static int but_saveGameY = 160;
	public static int but_rollDiceY = 380;
	public static Font but_font = new Font("Sans Serif",Font.BOLD,14);
	
	//LABELS POSITION, DIMENSION AND FONT
	public static int lab_onTurnWidth = 200;
	public static int lab_onTurnHeight = 35;
	public static int lab_onTurnX = 640;
	public static int lab_onTurnY = 220;
	public static int lab_rolledDiceWidth = 200;
	public static int lab_rolledDiceHeight = 35;
	public static int lab_rolledDiceX = 644;
	public static int lab_rolledDiceY = 350;
	public static Font lab_font25 = new Font("Courier New", Font.BOLD, 25);
	public static Font lab_font14 = new Font("Courier New", Font.BOLD, 14);
	
	//FRAME DIMENSION
	public static int frame_width = 800;
	public static int frame_heigth = 630;
	
	//MAIN CLASS STATIC VARIABLES
	static JFrame frame = new JFrame("Ludo");
	static JLabel lab_rolledA = new JLabel("");
	static JButton but_newGame = new JButton("New Game");
	static JButton but_loadGame = new JButton("Load Game");
	static JButton but_saveGame = new JButton("Save Game");
	static JLabel lab_onTurn = new JLabel("On turn:");
	static JButton but_rollDice = new JButton("Roll Dice");
	
			
	public static void main(String[] args) {
		showGUI();
	}
	
	private static void showGUI() {
		   
		configureFrame();	
		configureButtonNewGame();
		configureButtonLoadGame();
		configureButtonSaveGame();
		configureLabelOnTurn();
		configureLabelRolledA();
		configureButtonRollDice();
					
		frame.add(lab_rolledA);
		frame.add(but_newGame);
		frame.add(but_loadGame);
		frame.add(but_saveGame);
		frame.add(lab_onTurn);
		frame.add(but_rollDice);
		
		Board board = new Board();
		
		frame.add(board);
		
		frame.repaint();
    }
	
	private static void configureButtonNewGame() {
		but_newGame.setBounds(but_menuX, but_newGameY, but_width, but_height);
		but_newGame.setFont(but_font);
		but_newGame.setEnabled(true);
		but_newGame.setMnemonic(KeyEvent.VK_N); //ALT+N
		but_newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Game();
			}
		});
	}
	
	private static void configureButtonLoadGame() {		
		but_loadGame.setBounds(but_menuX, but_loadGameY, but_width,but_height);
		but_loadGame.setFont(but_font);
		but_loadGame.setEnabled(true);
		but_loadGame.setMnemonic(KeyEvent.VK_L); //ALT+L
		but_loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("Load");
				int option = fileChooser.showOpenDialog((Component)e.getSource());
				if (option == JFileChooser.APPROVE_OPTION) {
		            //File file = fileChooser.getSelectedFile();
		        }
			}
		});
	}
	
	private static void configureButtonSaveGame() {
		but_saveGame.setBounds(but_menuX, but_saveGameY,but_width,but_height);
		but_saveGame.setFont(but_font);
		but_saveGame.setEnabled(false);
		but_saveGame.setMnemonic(KeyEvent.VK_S); //ALT+S
		but_saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("Save");
				int option = fileChooser.showOpenDialog((Component)e.getSource());
				if (option == JFileChooser.APPROVE_OPTION) {
		            //File file = fileChooser.getSelectedFile();
		        }
			}
		});
	}
	
	private static void configureButtonRollDice() {
		but_rollDice.setBounds(but_menuX, but_rollDiceY,but_width,but_height);
		but_rollDice.setFont(but_font);
		but_rollDice.setEnabled(false);
		but_rollDice.setMnemonic(KeyEvent.VK_R); //ALT+R
		but_rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rolledDice = Game.rollDice(Game.currentTeam, but_rollDice);
				
				switch (rolledDice) {
				case 1: 
					lab_rolledA.setBounds(642, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled an one!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 2: 
					lab_rolledA.setBounds(644, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled a two!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 3: 
					lab_rolledA.setBounds(639, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled a three!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 4: 
					lab_rolledA.setBounds(642, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled a four!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 5: 
					lab_rolledA.setBounds(642, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled a five!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 6: 
					lab_rolledA.setBounds(644, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Rolled a six!");
					lab_rolledA.setFont(lab_font14);
					break;
				}
				Main.frame.repaint();
			}
		});	
	}

	private static void configureLabelOnTurn() {
		lab_onTurn.setFont(lab_font25);
		lab_onTurn.setBounds(lab_onTurnX, lab_onTurnY, lab_onTurnWidth, lab_onTurnHeight);
	}
	
	private static void configureLabelRolledA() {
		lab_rolledA.setFont(lab_font14);
		lab_rolledA.setBounds(lab_rolledDiceX, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
	}

	private static void configureFrame() {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(frame_width, frame_heigth));
		if (Board.debugMode) {
			Main.frame.setPreferredSize(new Dimension(frame_width, frame_heigth + 20));
		}
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String debugMsg = "";
				
				Position clickedPos = Position.getMousePosition(e.getX(),e.getY());
				
				//se clicar no tabuleiro
				if (clickedPos.x < 600 & clickedPos.y < 600) {
					
					//for debug
					System.out.printf("clickedPos: %s%s (%d, %d)\n", clickedPos.letter, clickedPos.number, e.getX(), e.getY());
									
					//se tiver peão nessa posição clicada
					if (clickedPos.pawn[0] != null) {
						debugMsg += "Position has pawn.";
						
						Pawn pawnClicked = clickedPos.pawn[0];
						
						//se o peão clicado for do time da vez
						if (pawnClicked.team == Game.currentTeam) { //BUGANDO!!!!!!!!!!!!
							debugMsg += " Pawn belong to team on turn.";
						
							//se tiver na posiçao inicial
							if (pawnClicked.currentPosition == pawnClicked.homePosition) {
								debugMsg += " Pawn is on home position.";
								
								if (Game.currentDice >= 5) {
									
									//atualiza a posição do pawn clicked
									pawnClicked.currentPositionInx += 1;
									
									//atualiza a posição de destino do pawn clicked
									pawnClicked.refreshPosition(clickedPos.pawn[0]);
									
									//obtém a posição de destino
									Position destinationPos = clickedPos.pawn[0].currentPosition;
									
									//atualiza o pawn da posição de destino
									destinationPos.pawn[0] = pawnClicked;
									
									//atualiza a quantidade de pawns na posição clicada
									clickedPos.pawn[0] = null;
									
									Main.frame.repaint();
									
									debugMsg += " Pawn left home.\n";
								} 
								else {
									debugMsg += " Pawn did not left home.\n";
								}
							}
							
							else {
								debugMsg += " Pawn is not on home position.";
								
								//atualiza a posição do pawn clicked
								pawnClicked.currentPositionInx += Game.currentDice;
								
								//atualiza a posição de destino do pawn clicked
								pawnClicked.refreshPosition(clickedPos.pawn[0]);
								
								//obtém a posição de destino
								Position destinationPos = clickedPos.pawn[0].currentPosition;
								
								//atualiza o pawn da posição de destino
								destinationPos.pawn[0] = pawnClicked;
								
								//atualiza a quantidade de pawns na posição clicada
								clickedPos.pawn[0] = null;
								
								Main.frame.repaint();
								
								debugMsg += " Pawn moved.\n";
							}
						}
						else {
							debugMsg += " Pawn did not belong to team on turn.\n";
						}
					} 
					else {
						debugMsg += " Position has no pawn.\n";
					}
				}
				
				printDebugMsg(debugMsg);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private static void printDebugMsg (String tag) {
		System.out.println(tag);
	}
}