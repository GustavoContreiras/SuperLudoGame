
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
		
		if (pawnCurrentPosition.pawn[0] == null) {
			System.out.println("New position do not have pawns.");
			pawnCurrentPosition.pawn[0] = pawnClicked;
		}
		
		else if (pawnCurrentPosition.pawn[0] != null) {
			System.out.println("New position already have a pawn.");
			
			if (pawnCurrentPosition.pawn[0].team == pawnClicked.team) {
				System.out.println("Pawn is of the same team.");
				pawnCurrentPosition.pawn[1] = pawnClicked;
			}
			
			else {
				System.out.println("Pawn is not from current team.");
				
				pawnCurrentPosition.pawn[0].currentPosition = pawnCurrentPosition.pawn[0].homePosition;
				pawnCurrentPosition.pawn[0].currentPositionInx = -1;
				
				pawnCurrentPosition.pawn[0] = pawnClicked;
			}
		}
		
		else {
			System.out.println(" New position has 2 pawns.");
		}
		
		System.out.println("Pawn moved.");
		System.out.printf("Pawn[0]: %s\nPawn[1]: %s\n", pawnCurrentPosition.pawn[0], pawnCurrentPosition.pawn[1]);
		Main.frame.repaint();
	}
}
