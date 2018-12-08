package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import systems.*;

abstract class Main extends JFrame implements ActionListener, MouseListener {
	
	//to remove warning
	private static final long serialVersionUID = 4582338413465297014L;
	
	//SHOW GRID LETTERS AND NUMBERS, ENABLES CHEATING
	public final static boolean debugMode = true;
	
	//BUTTONS POSITION, DIMENSION AND FONT
	public static int but_width = 150;
	public static int but_height = 35;
	public static int but_menuX = 622;
	public static int but_newGameY = 40;
	public static int but_loadGameY = 100;
	public static int but_saveGameY = 160;
	public static int but_rollDiceY = 370;
	public static Font fnt_sansSerif14 = new Font("Sans Serif",Font.BOLD,14);
	
	//LABELS POSITION, DIMENSION AND FONT
	public static int lab_onTurnWidth = 200;
	public static int lab_onTurnHeight = 35;
	public static int lab_onTurnX = 640;
	public static int lab_onTurnY = 220;
	
	public static int lab_lastMoveWidth = 200;
	public static int lab_lastMoveHeight = 35;
	public static int lab_lastMoveX = 628;
	public static int lab_lastMoveY = 340;
	
	public static int lab_instructionsWidth = 200;
	public static int lab_instructionsHeight = 35;
	public static int lab_instructionsX = 643;
	public static int lab_instructionsY = 400;
	
	public static Font fnt_courierNew25 = new Font("Courier New", Font.BOLD, 25);
	public static Font fnt_courierNew14 = new Font("Courier New", Font.BOLD, 14);
	
	//FRAME DIMENSION
	public static int frame_width = 800;
	public static int frame_heigth = 630;
	
	//MAIN CLASS STATIC VARIABLES
	public static JFrame frame = new JFrame("Ludo");
	public static JButton but_newGame = new JButton("New Game");
	public static JButton but_loadGame = new JButton("Load Game");
	public static JButton but_saveGame = new JButton("Save Game");
	public static JLabel lab_onTurn = new JLabel("On turn:");
	public static JLabel lab_lastMove = new JLabel("");
	public static JButton but_rollDice = new JButton("Roll Dice");
	public static JLabel lab_instructions = new JLabel("");
	public static Board board = new Board(false);
			
	public static void main(String[] args) {
		showGUI();
	}
	
	private static void showGUI() {
		   
		configureFrameAndMouseListener();
		configureButtonNewGame();
		configureButtonLoadGame();
		configureButtonSaveGame();
		configureLabelOnTurn();
		configureLabelLastMove();
		configureButtonRollDice();
		configureLabelInstructions();
		
		frame.add(but_newGame);
		frame.add(but_loadGame);		
		frame.add(but_saveGame);
		frame.add(lab_onTurn);
		frame.add(lab_lastMove);
		frame.add(but_rollDice);
		frame.add(lab_instructions);
		frame.add(board);	
		frame.repaint();
    }
	
	private static void configureButtonNewGame() {
		but_newGame.setBounds(but_menuX, but_newGameY, but_width, but_height);
		but_newGame.setFont(fnt_sansSerif14);
		but_newGame.setEnabled(true);
		but_newGame.setMnemonic(KeyEvent.VK_N); //ALT+N
		but_newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CtrlGame.getController().createNewGame();
				
				Main.but_rollDice.setEnabled(true);
				Main.but_saveGame.setEnabled(true);
				Main.lab_lastMove.setText("");

				board = new Board(true);
				frame.add(board);
				Main.frame.repaint();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void configureButtonLoadGame() {
		but_loadGame.setBounds(but_menuX, but_loadGameY, but_width,but_height);
		but_loadGame.setFont(fnt_sansSerif14);
		but_loadGame.setEnabled(true);
		but_loadGame.setMnemonic(KeyEvent.VK_L); //ALT+L
		but_loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("LUDOGame", "ludogame"); 
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setFileFilter(fileFilter);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				int option = fileChooser.showDialog(fileChooser, "Carregar");
				
				if (option == JFileChooser.APPROVE_OPTION)
					CtrlGame.getController().loadGame(fileChooser.getSelectedFile());
				
				Main.but_saveGame.setEnabled(true);
					
				board = new Board(true);
				
				if (CtrlGame.getController().getCurrentDice() > 0) {
					Main.but_rollDice.setEnabled(false);
					Main.lab_instructions.setText("Choose a pawn!");
					Main.lab_lastMove.setText("Last move:");
				}
				else {
					Main.but_rollDice.setEnabled(true);
				}
				
				Main.frame.repaint();
			}
		});
	}
	
	private static void configureButtonSaveGame() {
		but_saveGame.setBounds(but_menuX, but_saveGameY,but_width,but_height);
		but_saveGame.setFont(fnt_sansSerif14);
		but_saveGame.setEnabled(false);
		but_saveGame.setMnemonic(KeyEvent.VK_S); //ALT+S
		but_saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("LUDOGame", "ludogame"); 
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setFileFilter(fileFilter);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				int option = fileChooser.showDialog(fileChooser, "Salvar");
				
				if (option == JFileChooser.APPROVE_OPTION) 
					CtrlGame.getController().saveGame(fileChooser.getSelectedFile());
			}
		});
	}
	
	private static void configureButtonRollDice() {
		but_rollDice.setBounds(but_menuX, but_rollDiceY,but_width,but_height);
		but_rollDice.setFont(fnt_sansSerif14);
		but_rollDice.setEnabled(false);
		but_rollDice.setMnemonic(KeyEvent.VK_R); //ALT+R
		but_rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
				/* Após o botão de OK do diálogo acima tiver sido pressionado, o
				programa deverá exibir um segundo diálogo, que irá perguntar se os 
				jogadores querem continuar a jogar ou se querem encerrar o programa
				(JOptionPane.showConfirmDialog). Caso a resposta seja pela continuação,
				o programa deverá exibir a configuração inicial do tabuleiro. */
				
				CtrlGame.getController().rollDice(numberCheated);
				CtrlGame.getController().makeMoveAfterRollDice();
				
				Main.frame.repaint();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});	
	}
	
	private static void configureLabelOnTurn() {
		lab_onTurn.setFont(fnt_courierNew25);
		lab_onTurn.setBounds(lab_onTurnX, lab_onTurnY, lab_onTurnWidth, lab_onTurnHeight);
	}
	
	private static void configureLabelLastMove() {
		lab_lastMove.setFont(fnt_courierNew14);
		lab_lastMove.setBounds(lab_lastMoveX, lab_lastMoveY, lab_lastMoveWidth, lab_lastMoveHeight);
	}

	private static void configureLabelInstructions() {
		lab_instructions.setFont(fnt_courierNew14);
		lab_instructions.setBounds(lab_instructionsX, lab_instructionsY, lab_instructionsWidth, lab_instructionsHeight);
	}

	private static void configureFrameAndMouseListener() {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(frame_width, frame_heigth));
		if (Main.debugMode) frame.setPreferredSize(new Dimension(frame_width, frame_heigth+20));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
								
				//se clicar no tabuleiro
				if (e.getX() < 603 & e.getY() < 630) {
					
					//se for o botao esquerdo
					if (e.getButton() == MouseEvent.BUTTON1) {
						CtrlGame.getController().doMouseClick(e.getX(), e.getY());
						Main.frame.repaint();	
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
	}
}