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
		   
		configureFrameAndMouseListener();	
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
				case 0: 
					lab_rolledA.setBounds(636, lab_rolledDiceY, lab_rolledDiceWidth, lab_rolledDiceHeight);
					lab_rolledA.setText("Not high enough!");
					lab_rolledA.setFont(lab_font14);
					break;
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
										
					Position posClicked = Position.getMousePosition(e.getX(),e.getY());
					System.out.printf("\nposClicked: %s%s (%d, %d)\n", posClicked.letter, posClicked.number, e.getX(), e.getY());
					System.out.printf("posClicked.pawn[0]: %s\n", posClicked.pawn[0]);
					System.out.printf("posClicked.pawn[1]: %s\n", posClicked.pawn[1]);
					System.out.printf("posClicked.pawn[2]: %s\n", posClicked.pawn[2]);
					System.out.printf("posClicked.pawn[3]: %s\n\n", posClicked.pawn[3]);
					
					//se tiver peao na posicao clicada e tiver rolado o dado
					if (posClicked.pawn[0] != null & Game.currentDice != 0) {
						Game.makeMove(posClicked, Game.currentDice, Game.currentTeam);
					}
					else {
						printDebugMsg("Need to roll dice or position do not have pawn.");
					}
					
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
		
		if (Board.debugMode) {
			Main.frame.setPreferredSize(new Dimension(frame_width, frame_heigth + 20));
		}
	}
	
	private static void printDebugMsg (String debugMsg) {
		System.out.println(debugMsg);
	}
}