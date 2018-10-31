
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
		
		for (int i = 0; i <= 56; i++) {
			this.walkthrough[i] = walkthrough[i];
		}
	}
	
	public void addPosition(int inxToAdd) {
		this.currentPositionInx += inxToAdd;
		this.currentPosition = this.walkthrough[this.currentPositionInx];
	}
}
