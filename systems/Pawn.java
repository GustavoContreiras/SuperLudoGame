package systems;

import graphics.*;

class Pawn {
	
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
		
		if (this.positionInx < 56) {
			this.detachFromPos(this.position);
			this.addPosition(rolledDice);
			this.attachToPos(this);
			Game.lastPawnMoved = this;
			if (Game.lastPawnMoved != null) {
				System.out.printf("lastPawnMoved.id: %d\n", Game.lastPawnMoved.id);
			}
		}
		else {
			System.out.println("Pawn has finished his walkthrough");
		}
		Main.frame.repaint();
		
		if (this.position.pawn[0] != null) {
			System.out.printf("newPos.Pawn[0].id: %d (%s Team)\n", this.position.pawn[0].id, this.position.pawn[0].team.name);
		}
		if (this.position.pawn[1] != null) {
			System.out.printf("newPos.Pawn[1].id: %d (%s Team)\n", this.position.pawn[1].id, this.position.pawn[1].team.name);
		}
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
		
		//se tiver na posiçao [0] e for do time da vez
		if (pawnCurrentPosition.pawn[0] != null & pawnCurrentPosition.pawn[0].team == Game.currentTeam) {
			
			//desanexa
			pawnCurrentPosition.pawn[0] = null;
			
			//se tiver algum na posicao [1]
			if (pawnCurrentPosition.pawn[1] != null) {
				
				//passa pra posicao [0]
				pawnCurrentPosition.pawn[0] = pawnCurrentPosition.pawn[1];
				
				//desanexa da [1]
				pawnCurrentPosition.pawn[1] = null;
			}
		}
		
		//se tiver na posicao [1] e for do time da vez
		else if (pawnCurrentPosition.pawn[1] != null & pawnCurrentPosition.pawn[1].team == Game.currentTeam) {
			
			//desanexa
			pawnCurrentPosition.pawn[1] = null;
		}
	}
	
	private void attachToPos(Pawn pawn) {
		
		Position newPos = pawn.position;
		
		if (newPos.pawn[0] == null) {
			System.out.println("New position do not have pawns.");
			newPos.pawn[0] = pawn;
		}
		
		else if (newPos.pawn[0] != null) {
			System.out.println("New position already have a pawn.");
			
			if (newPos.pawn[0].team == pawn.team) {
				System.out.println("Pawn is of the same team.");
				
				newPos.pawn[1] = pawn.position.pawn[0];
				newPos.pawn[0] = pawn;
			}
			
			else {
				System.out.println("Pawn is not from current team.");
				
				//se não for casa de saída...
				if (newPos != Position.B7 & newPos != Position.I2 &
					newPos != Position.N9 & newPos != Position.G14) {
					
					System.out.println("New position is not exit house.");
				
					newPos.pawn[0].position = newPos.pawn[0].homePosition;
					newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
					newPos.pawn[0].positionInx = -1;
					
					newPos.pawn[0] = pawn;
				}
				
				//se for casa de saida...
				else {
					System.out.println("New position is exit house.");
					
					//falta checar se o peao na casa de saida eh da cor da casa de saida
					newPos.pawn[1] = pawn;
				}
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
