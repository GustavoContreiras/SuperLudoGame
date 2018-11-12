import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Main extends JFrame implements ActionListener, MouseListener {
	
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
	public static int but_rollDiceY = 400;
	public static Font but_font = new Font("Sans Serif",Font.BOLD,14);
	
	//LABELS POSITION, DIMENSION AND FONT
	public static int lab_onTurnWidth = 200;
	public static int lab_onTurnHeight = 35;
	public static int lab_onTurnX = 640;
	public static int lab_onTurnY = 220;
	
	public static int lab_oldTeamWidth = 200;
	public static int lab_oldTeamHeight = 35;
	public static int lab_oldTeamX = 646;
	public static int lab_oldTeamY = 355;
	
	public static int lab_rolledDiceWidth = 200;
	public static int lab_rolledDiceHeight = 35;
	public static int lab_rolledDiceX = 644;
	public static int lab_rolledDiceY = 370;
	
	public static int lab_instructionsWidth = 200;
	public static int lab_instructionsHeight = 35;
	public static int lab_instructionsX = 643;
	public static int lab_instructionsY = 430;
	
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
	static JLabel lab_oldTeam = new JLabel("");
	static JButton but_rollDice = new JButton("Roll Dice");
	static JLabel lab_instructions = new JLabel("");
			
	public static void main(String[] args) {
		showGUI();
	}
	
	private static void showGUI() {
		   
		configureFrameAndMouseListener();	
		configureButtonNewGame();
		configureButtonLoadGame();
		configureButtonSaveGame();
		configureLabelOnTurn();
		configureLabelOldTeam();
		configureLabelRolledA();
		configureButtonRollDice();
		configureLabelInstructions();
					
		frame.add(lab_rolledA);
		frame.add(but_newGame);
		frame.add(but_loadGame);
		frame.add(but_saveGame);
		frame.add(lab_onTurn);
		frame.add(lab_oldTeam);
		frame.add(but_rollDice);
		frame.add(lab_instructions);		
		frame.add(new Board());
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
				
				int rolledDice = Game.rollDice();
				
				switch (Game.oldTeam.name) {
				case "Red":
					lab_oldTeam.setBounds(665, lab_oldTeamY, lab_oldTeamWidth, lab_oldTeamHeight);
					break;
				case "Green":
					lab_oldTeam.setBounds(657, lab_oldTeamY, lab_oldTeamWidth, lab_oldTeamHeight);
					break;
				case "Yellow":
					lab_oldTeam.setBounds(652, lab_oldTeamY, lab_oldTeamWidth, lab_oldTeamHeight);
					break;
				case "Blue":
					lab_oldTeam.setBounds(661, lab_oldTeamY, lab_oldTeamWidth, lab_oldTeamHeight);
					break;
				}
				
				switch (rolledDice) {
				case 0: 
					lab_rolledA.setBounds(636, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Not high enough!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 1: 
					lab_rolledA.setBounds(643, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled an one!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 2: 
					lab_rolledA.setBounds(646, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled a two!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 3:
					lab_rolledA.setBounds(639, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled a three!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 4: 
					lab_rolledA.setBounds(644, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled a four!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 5: 
					lab_rolledA.setBounds(644, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled a five!");
					lab_rolledA.setFont(lab_font14);
					break;
				case 6: 
					lab_rolledA.setBounds(646, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_oldTeam.setText(Game.oldTeam.name + " team");
					lab_rolledA.setText("Rolled a six!");
					lab_rolledA.setFont(lab_font14);
					break;
				default:
					lab_rolledA.setBounds(644, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("");
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
	
	private static void configureLabelOldTeam() {
		lab_oldTeam.setFont(lab_font14);
		lab_oldTeam.setBounds(lab_oldTeamX, lab_oldTeamY, lab_oldTeamWidth, lab_oldTeamHeight);
	}
	
	private static void configureLabelRolledA() {
		lab_rolledA.setFont(lab_font14);
		lab_rolledA.setBounds(lab_rolledDiceX, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
	}
	
	private static void configureLabelInstructions() {
		lab_instructions.setFont(lab_font14);
		lab_instructions.setBounds(lab_instructionsX, lab_instructionsY, lab_instructionsWidth, lab_instructionsHeight);
	}

	private static void configureFrameAndMouseListener() {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(frame_width, frame_heigth));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
								
				//se clicar no tabuleiro
				if (e.getX() < 603 & e.getY() < 630) {
					
					Main.lab_rolledA.setText("");
					Main.lab_instructions.setText("");
										
					Position posClicked = Position.getMousePosition(e.getX(),e.getY());
					System.out.printf("\nposClicked: %s%s (%d, %d)\n", posClicked.letter, posClicked.number, e.getX(), e.getY());
					System.out.printf("posClicked.pawn[0]: %s\n", posClicked.pawn[0]);
					System.out.printf("posClicked.pawn[1]: %s\n", posClicked.pawn[1]);
					
					//se tiver peao na posicao clicada e tiver rolado o dado
					if (posClicked.pawn[0] != null & Game.currentDice != 0) {
						
						if (posClicked.pawn[0].team == Game.currentTeam ) {
							Game.makeMove(posClicked, Game.currentDice, Game.currentTeam);
						}
						else {
							System.out.printf("Pawn is not from current team.\n");
						}
					}
					else {
						System.out.printf("Need to roll dice or position do not have pawn.\n");
					}
					
					Main.frame.repaint();
				}	
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
}