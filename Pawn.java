
public class Pawn {
	
	public Position homePosition;
	public Position currentPosition;
	public int currentPositionInx;
	
	public Position[] walkthrough = new Position[57];
	
	public Team team;
	public int id;
	
	public Pawn(Team team, int id, Position homePosition, Position[] walkthrough) {
		this.id = id;
		this.team = team;
		this.homePosition = homePosition;
		this.currentPosition = homePosition;
		this.currentPositionInx = -1;
		this.walkthrough = walkthrough;
	}
	
	public Position addPosition(int inxToAdd) {
		this.currentPositionInx += inxToAdd;
		this.currentPosition = this.walkthrough[this.currentPositionInx];
		
		//debug
		System.out.printf("newPos: %s%s\n", this.currentPosition.letter, this.currentPosition.number);
		
		Main.frame.repaint();
		
		return this.currentPosition;
	}
	
	public void detachFromPos(Position pawnCurrentPosition) {
		
		pawnCurrentPosition.pawn[0] = null;
		
		if (pawnCurrentPosition.pawn[1] != null) {
			pawnCurrentPosition.pawn[0] = pawnCurrentPosition.pawn[1];
			pawnCurrentPosition.pawn[1] = null;
		}
		
		Main.frame.repaint();
	}
	
	public void attachToPos(Position pawnCurrentPosition, Pawn pawnClicked) {
		
		//se n�o tiver pe�o na posi��o de destino
		if (pawnCurrentPosition.pawn[0] == null) {
			System.out.println("New position do not have pawns.");
			
			//anexa o pe�o na posi��o de destino
			pawnCurrentPosition.pawn[0] = pawnClicked;
		}
		
		//se tiver um pe�o na posi��o de destino
		else if (pawnCurrentPosition.pawn[0] != null & pawnCurrentPosition.pawn[1] == null) {
			System.out.println("New position already have a pawn.");
			
			//se o pe�o for do time do jogador
			if (pawnCurrentPosition.pawn[0].team == pawnClicked.team) {
				System.out.println("Pawn is of the same team.");
				
				//cria a barreira
				pawnCurrentPosition.pawn[1] = pawnClicked;
			}
			
			//se o pe�o n�o for do time do jogador
			else {
				System.out.println("Pawn is not from current team.");
				
				//come o pe�o advers�rio (manda ele pra posi��o inicial)
				pawnCurrentPosition.pawn[0].currentPosition = pawnCurrentPosition.pawn[0].homePosition;
				pawnCurrentPosition.pawn[0] = pawnClicked;
			}
		}
		
		//se tiver mais de um pe�o (barreira)
		else {
			System.out.println(" New position has 2 pawns.");
		}
		
		System.out.println("Pawn moved.");
		System.out.printf("Pawn[0]: %s\nPawn[1]: %s\n", pawnCurrentPosition.pawn[0], pawnCurrentPosition.pawn[1]);
		Main.frame.repaint();
	}
}
