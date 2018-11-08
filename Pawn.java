
public class Pawn {
	
	public Position homePosition;
	public Position position;
	public int positionInx;
	
	public Position[] walkthrough = new Position[57];
	
	public Team team;
	public int id;
	
	public Pawn(Team team, int id, Position homePosition, Position[] walkthrough) {
		this.id = id;
		this.team = team;
		this.homePosition = homePosition;
		this.position = homePosition;
		this.positionInx = -1;
		this.walkthrough = walkthrough;
		
		this.homePosition.pawn[0] = this;
	}
	
	public void walk (int rolledDice) {
		/*for (int i = 0; i < rolledDice; i++) {
			
			if (this.nextPos().hasABarrier() == false) {
				this.detachFromPos(this.position);
				this.addPosition(1);
				this.attachToPos(this);
				Main.frame.repaint();
			}
			
			else {
				i = rolledDice;
			}
		}*/
		
		if (this.positionInx != 56) {
			this.detachFromPos(this.position);
			this.addPosition(rolledDice);
			this.attachToPos(this);
		}
		else {
			System.out.println("Pawn has finished his walkthrough");
		}
		Main.frame.repaint();
		
		System.out.printf("Pawn[0]: %s\nPawn[1]: %s\n", this.position.pawn[0], this.position.pawn[1]);
	}
	
	private Position addPosition(int inxToAdd) {
		
		int inxNewPos = this.positionInx + inxToAdd;
		
		//se a posicao de destino tiver uma barreira
		if (walkthrough[inxNewPos].pawn[0] != null & walkthrough[inxNewPos].pawn[1] != null) {
			System.out.printf("New position has a barrier.\n", position.letter, position.number);
			return position;
		}
		
		//se a posição de destino não tiver uma barreira
		else {
			positionInx += inxToAdd;
			position = walkthrough[positionInx];
			
			System.out.printf("newPos: %s%s\n", position.letter, position.number);
						
			return position;
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
	
	private void attachToPos(Pawn pawn) {
		
		if (pawn.position.pawn[0] == null) {
			System.out.println("New position do not have pawns.");
			pawn.position.pawn[0] = pawn;
		}
		
		else if (pawn.position.pawn[0] != null) {
			System.out.println("New position already have a pawn.");
			
			if (pawn.position.pawn[0].team == pawn.team) {
				System.out.println("Pawn is of the same team.");
				
				pawn.position.pawn[1] = pawn.position.pawn[0];
				pawn.position.pawn[0] = pawn;
			}
			
			else {
				System.out.println("Pawn is not from current team.");
				
				pawn.position.pawn[0].position = pawn.position.pawn[0].homePosition;
				pawn.position.pawn[0].homePosition.pawn[0] = pawn.position.pawn[0];
				pawn.position.pawn[0].positionInx = -1;
				
				pawn.position.pawn[0] = pawn;
			}
		}
		
		else {
			System.out.println("New position has 2 pawns.");
		}
	}

	public Position nextPos() {
		return walkthrough[positionInx + 1];
	}
}
