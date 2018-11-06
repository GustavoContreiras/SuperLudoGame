
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
		
		this.homePosition.pawn[0] = this;
	}
	
	public void walk (int rolledDice) {
		for (int i = 0; i < rolledDice; i++) {
			
			if (this.nextPos().hasABarrier() == false) {
				this.detachFromPos(this.currentPosition);
				this.addPosition(1);
				this.attachToPos(this.currentPosition, this);
				Main.frame.repaint();
			}
			
			else {
				i = rolledDice;
			}
		}
	}
	
	private Position addPosition(int inxToAdd) {
		
		int inxNewPos = this.currentPositionInx + inxToAdd;
		
		//se a posicao de destino tiver uma barreira
		if (walkthrough[inxNewPos].pawn[0] != null & walkthrough[inxNewPos].pawn[1] != null) {
			System.out.printf("New position has a barrier.\n", currentPosition.letter, currentPosition.number);
			return currentPosition;
		}
		
		//se a posi��o de destino n�o tiver uma barreira
		else {
			currentPositionInx += inxToAdd;
			currentPosition = walkthrough[currentPositionInx];
			
			System.out.printf("newPos: %s%s\n", currentPosition.letter, currentPosition.number);
			
			Main.frame.repaint();
			
			return currentPosition;
		}
	}
	
	private void detachFromPos(Position pawnCurrentPosition) {
		
		pawnCurrentPosition.pawn[0] = null;
		
		if (pawnCurrentPosition.pawn[1] != null) {
			pawnCurrentPosition.pawn[0] = pawnCurrentPosition.pawn[1];
			pawnCurrentPosition.pawn[1] = null;
		}
		
		Main.frame.repaint();
	}
	
	private void attachToPos(Position pawnCurrentPosition, Pawn pawnClicked) {
		
		if (pawnCurrentPosition.pawn[0] == null) {
			System.out.println("New position do not have pawns.");
			pawnCurrentPosition.pawn[0] = pawnClicked;
		}
		
		else if (pawnCurrentPosition.pawn[0] != null) {
			System.out.println("New position already have a pawn.");
			
			if (pawnCurrentPosition.pawn[0].team == pawnClicked.team) {
				System.out.println("Pawn is of the same team.");
				
				pawnCurrentPosition.pawn[1] = pawnClicked;
				
				System.out.println("Pawn moved.");
			}
			
			else {
				System.out.println("Pawn is not from current team.");
				
				pawnCurrentPosition.pawn[0].currentPosition = pawnCurrentPosition.pawn[0].homePosition;
				pawnCurrentPosition.pawn[0].homePosition.pawn[0] = pawnCurrentPosition.pawn[0];
				pawnCurrentPosition.pawn[0].currentPositionInx = -1;
				
				pawnCurrentPosition.pawn[0] = pawnClicked;
				
				System.out.println("Pawn moved.");
			}
		}
		
		else {
			System.out.println("New position has 2 pawns.");
		}
		
		System.out.printf("Pawn[0]: %s\nPawn[1]: %s\nPawn[2]: %s\nPawn[3]: %s\n\n", 
						  pawnCurrentPosition.pawn[0], pawnCurrentPosition.pawn[1],
						  pawnCurrentPosition.pawn[2], pawnCurrentPosition.pawn[3]);
		Main.frame.repaint();
	}

	public Position nextPos() {
		return walkthrough[currentPositionInx + 1];
	}
}
