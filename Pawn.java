
public class Pawn {
	
	public Position homePosition;
	public Position currentPosition;
	public int currentPositionInx;
	
	public Position[] walkthrough = new Position[57];
	
	public Team team;
	public int id;
	
	public Pawn(Team team, int id, Position homePosition, Position[] walkthrough) {
		
		//PAWN
		this.id = id;
		this.team = team;
		this.homePosition = homePosition;
		this.currentPosition = homePosition;
		this.currentPositionInx = -1;
		
		if (team == Game.redTeam) {
			setRedPawnWalkthrough(this, walkthrough);
		}
		else if (team == Game.greenTeam) {
			setGreenPawnWalkthrough(this, walkthrough);
		}
		else if (team == Game.yellowTeam) {
			setYellowPawnWalkthrough(this, walkthrough);
		}
		else if (team == Game.blueTeam) {
			setBluePawnWalkthrough(this, walkthrough);
		}
	}
	
	public Pawn getPawn(Team team, int pawnId) {
		switch (pawnId) {
		case 1:
			if (team == Game.redTeam) {
				return Game.redTeam.pawn[0];
			}
			else if (team == Game.greenTeam) {
				return Game.greenTeam.pawn[0];
			}
			else if (team == Game.yellowTeam) {
				return Game.yellowTeam.pawn[0];
			}
			else if (team == Game.blueTeam) {
				return Game.blueTeam.pawn[0];
			}
		case 2:
			if (team == Game.redTeam) {
				return Game.redTeam.pawn[1];
			}
			else if (team == Game.greenTeam) {
				return Game.greenTeam.pawn[1];
			}
			else if (team == Game.yellowTeam) {
				return Game.yellowTeam.pawn[1];
			}
			else if (team == Game.blueTeam) {
				return Game.blueTeam.pawn[1];
			}
		case 3:
			if (team == Game.redTeam) {
				return Game.redTeam.pawn[2];
			}
			else if (team == Game.greenTeam) {
				return Game.greenTeam.pawn[2];
			}
			else if (team == Game.yellowTeam) {
				return Game.yellowTeam.pawn[2];
			}
			else if (team == Game.blueTeam) {
				return Game.blueTeam.pawn[2];
			}
		case 4:
			if (team == Game.redTeam) {
				return Game.redTeam.pawn[3];
			}
			else if (team == Game.greenTeam) {
				return Game.greenTeam.pawn[3];
			}
			else if (team == Game.yellowTeam) {
				return Game.yellowTeam.pawn[3];
			}
			else if (team == Game.blueTeam) {
				return Game.blueTeam.pawn[3];
			}
		}
		return null;
	}

	public void refreshPosition(Pawn pawn) {
		int inx = pawn.currentPositionInx;
		pawn.currentPosition = pawn.walkthrough[inx];
	}
	
	public Position getPosition(Pawn pawn) {
		return pawn.currentPosition;
	}
	
	public int getPositionInt(Pawn pawn) {
		return pawn.currentPositionInx;
	}
	
	public Position[] getWalkthrough(Pawn pawn) {
		return pawn.walkthrough;
	}
	
	public Team getTeam(Pawn pawn) {
		return pawn.team;
	}
	
	public int getId(Pawn pawn) {
		return pawn.id;
	}
	
	private void setRedPawnWalkthrough(Pawn pawn, Position[] walkthrough) {
		for (int i = 0; i <= 56; i++) {
			pawn.walkthrough[i] = walkthrough[i];
		}
	}
	
	private void setGreenPawnWalkthrough(Pawn pawn, Position[] walkthrough) {
		
		if (pawn.id == 1) {
			pawn.walkthrough[0] = walkthrough[0];
		}
		else if (pawn.id == 2) {
			pawn.walkthrough[0] = walkthrough[1];
		}
		else if (pawn.id == 3) {
			pawn.walkthrough[0] = walkthrough[2];
		}
		else if (pawn.id == 4) {
			pawn.walkthrough[0] = walkthrough[3];
		}
		
		for (int i = 0; i <= 56; i++) {
			pawn.walkthrough[i] = walkthrough[i];
		}
	}
	
	private void setYellowPawnWalkthrough(Pawn pawn, Position[] walkthrough) {
		
		if (pawn.id == 1) {
			pawn.walkthrough[0] = walkthrough[0];
		}
		else if (pawn.id == 2) {
			pawn.walkthrough[0] = walkthrough[1];
		}
		else if (pawn.id == 3) {
			pawn.walkthrough[0] = walkthrough[2];
		}
		else if (pawn.id == 4) {
			pawn.walkthrough[0] = walkthrough[3];
		}
		
		for (int i = 1; i <= 56; i++) {
			pawn.walkthrough[i] = walkthrough[i];
		}
	}
	
	private void setBluePawnWalkthrough(Pawn pawn, Position[] walkthrough) {
		
		if (pawn.id == 1) {
			pawn.walkthrough[0] = walkthrough[0];
		}
		else if (pawn.id == 2) {
			pawn.walkthrough[0] = walkthrough[1];
		}
		else if (pawn.id == 3) {
			pawn.walkthrough[0] = walkthrough[2];
		}
		else if (pawn.id == 4) {
			pawn.walkthrough[0] = walkthrough[3];
		}
		
		for (int i = 1; i <= 56; i++) {
			pawn.walkthrough[i] = walkthrough[i];
		}
	}
}
