
public class Team {
	
	Position[] walkthrough = new Position[57];
	Pawn[] pawn = new Pawn[4];
	String name = "";
	
	public Team (String team) {
		
		name = team;
		
		switch (team) {
		
		case "Red":
			
			walkthrough = createRedWalkthrough();
			pawn[0] = new Pawn(this, 1, Position.B5, walkthrough);			
			pawn[1] = new Pawn(this, 2, Position.E5, walkthrough);			
			pawn[2] = new Pawn(this, 3, Position.B2, walkthrough);			
			pawn[3] = new Pawn(this, 4, Position.E2, walkthrough);			
			break;
			
		case "Green":
			
			walkthrough = createGreenWalkthrough();
			pawn[0] = new Pawn(this, 1, Position.K2, walkthrough);			
			pawn[1] = new Pawn(this, 2, Position.K5, walkthrough);			
			pawn[2] = new Pawn(this, 3, Position.N2, walkthrough);			
			pawn[3] = new Pawn(this, 4, Position.N5, walkthrough);			
			break;
			
		case "Yellow":
			
			walkthrough = createYellowWalkthrough();
			pawn[0] = new Pawn(this, 1, Position.N11, walkthrough);			
			pawn[1] = new Pawn(this, 2, Position.K11, walkthrough);			
			pawn[2] = new Pawn(this, 3, Position.N14, walkthrough);			
			pawn[3] = new Pawn(this, 4, Position.K14, walkthrough);			
			break;
			
		case "Blue":
			
			walkthrough = createBlueWalkthrough();
			pawn[0] = new Pawn(this, 1, Position.E14, walkthrough);			
			pawn[1] = new Pawn(this, 2, Position.E11, walkthrough);			
			pawn[2] = new Pawn(this, 3, Position.B14, walkthrough);		
			pawn[3] = new Pawn(this, 4, Position.B11, walkthrough);
			break;
		}
	}
	
	public boolean hasAllPawnsInHome() {
		if (Game.currentTeam.pawn[0].currentPosition == Game.currentTeam.pawn[0].homePosition &
			Game.currentTeam.pawn[1].currentPosition == Game.currentTeam.pawn[1].homePosition &
			Game.currentTeam.pawn[2].currentPosition == Game.currentTeam.pawn[2].homePosition &
			Game.currentTeam.pawn[3].currentPosition == Game.currentTeam.pawn[3].homePosition) {
			return true;
		}
		else {
			return false;
	}
		
	}
	
	private Position[] createRedWalkthrough() {
		walkthrough[0] = Position.B7;
		walkthrough[1] = Position.C7;
		walkthrough[2] = Position.D7;
		walkthrough[3] = Position.E7;
		walkthrough[4] = Position.F7;
		walkthrough[5] = Position.G6;
		walkthrough[6] = Position.G5;
		walkthrough[7] = Position.G4;
		walkthrough[8] = Position.G3;
		walkthrough[9] = Position.G2;
		walkthrough[10] = Position.G1;
		walkthrough[11] = Position.H1;
		walkthrough[12] = Position.I1;
		walkthrough[13] = Position.I2;
		walkthrough[14] = Position.I3;
		walkthrough[15] = Position.I4;
		walkthrough[16] = Position.I5;
		walkthrough[17] = Position.I6;
		walkthrough[18] = Position.J7;
		walkthrough[19] = Position.K7;
		walkthrough[20] = Position.L7;
		walkthrough[21] = Position.M7;
		walkthrough[22] = Position.N7;
		walkthrough[23] = Position.O7;
		walkthrough[24] = Position.O8;
		walkthrough[25] = Position.O9;
		walkthrough[26] = Position.N9;
		walkthrough[27] = Position.M9;
		walkthrough[28] = Position.L9;
		walkthrough[29] = Position.K9;
		walkthrough[30] = Position.J9;
		walkthrough[31] = Position.I10;
		walkthrough[32] = Position.I11;
		walkthrough[33] = Position.I12;
		walkthrough[34] = Position.I13;
		walkthrough[35] = Position.I14;
		walkthrough[36] = Position.I15;
		walkthrough[37] = Position.H15;
		walkthrough[38] = Position.G15;
		walkthrough[39] = Position.G14;
		walkthrough[40] = Position.G13;
		walkthrough[41] = Position.G12;
		walkthrough[42] = Position.G11;
		walkthrough[43] = Position.G10;
		walkthrough[44] = Position.F9;
		walkthrough[45] = Position.E9;
		walkthrough[46] = Position.D9;
		walkthrough[47] = Position.C9;
		walkthrough[48] = Position.B9;
		walkthrough[49] = Position.A9;
		walkthrough[50] = Position.A8;
		walkthrough[51] = Position.B8;
		walkthrough[52] = Position.C8;
		walkthrough[53] = Position.D8;
		walkthrough[54] = Position.E8;
		walkthrough[55] = Position.F8;
		walkthrough[56] = Position.G8;
		
		return walkthrough;
	}

	private Position[] createGreenWalkthrough() {
		walkthrough[0] = Position.I2;
		walkthrough[1] = Position.I3;
		walkthrough[2] = Position.I4;
		walkthrough[3] = Position.I5;
		walkthrough[4] = Position.I6;
		walkthrough[5] = Position.J7;
		walkthrough[6] = Position.K7;
		walkthrough[7] = Position.L7;
		walkthrough[8] = Position.M7;
		walkthrough[9] = Position.N7;
		walkthrough[10] = Position.O7;
		walkthrough[11] = Position.O8;
		walkthrough[12] = Position.O9;
		walkthrough[13] = Position.N9;
		walkthrough[14] = Position.M9;
		walkthrough[15] = Position.L9;
		walkthrough[16] = Position.K9;
		walkthrough[17] = Position.J9;
		walkthrough[18] = Position.I10;
		walkthrough[19] = Position.I11;
		walkthrough[20] = Position.I12;
		walkthrough[21] = Position.I13;
		walkthrough[22] = Position.I14;
		walkthrough[23] = Position.I15;
		walkthrough[24] = Position.H15;
		walkthrough[25] = Position.G15;
		walkthrough[26] = Position.G14;
		walkthrough[27] = Position.G13;
		walkthrough[28] = Position.G12;
		walkthrough[29] = Position.G11;
		walkthrough[30] = Position.G10;
		walkthrough[31] = Position.F9;
		walkthrough[32] = Position.E9;
		walkthrough[33] = Position.D9;
		walkthrough[34] = Position.C9;
		walkthrough[35] = Position.B9;
		walkthrough[36] = Position.A9;
		walkthrough[37] = Position.A8;
		walkthrough[38] = Position.A7;
		walkthrough[39] = Position.B7;
		walkthrough[40] = Position.C7;
		walkthrough[41] = Position.D7;
		walkthrough[42] = Position.E7;
		walkthrough[43] = Position.F7;
		walkthrough[44] = Position.G6;
		walkthrough[45] = Position.G5;
		walkthrough[46] = Position.G4;
		walkthrough[47] = Position.G3;
		walkthrough[48] = Position.G2;
		walkthrough[49] = Position.G1;
		walkthrough[50] = Position.H1;
		walkthrough[51] = Position.H2;
		walkthrough[52] = Position.H3;
		walkthrough[53] = Position.H4;
		walkthrough[54] = Position.H5;
		walkthrough[55] = Position.H6;
		walkthrough[56] = Position.H7;
		
		return walkthrough;
	}
	
	private Position[] createYellowWalkthrough() {
		walkthrough[0] = Position.N9;
		walkthrough[1] = Position.M9;
		walkthrough[2] = Position.L9;
		walkthrough[3] = Position.K9;
		walkthrough[4] = Position.J9;
		walkthrough[5] = Position.I10;
		walkthrough[6] = Position.I11;
		walkthrough[7] = Position.I12;
		walkthrough[8] = Position.I13;
		walkthrough[9] = Position.I14;
		walkthrough[10] = Position.I15;
		walkthrough[11] = Position.H15;
		walkthrough[12] = Position.G15;
		walkthrough[13] = Position.G14;
		walkthrough[14] = Position.G13;
		walkthrough[15] = Position.G12;
		walkthrough[16] = Position.G11;
		walkthrough[17] = Position.G10;
		walkthrough[18] = Position.F9;
		walkthrough[19] = Position.E9;
		walkthrough[20] = Position.D9;
		walkthrough[21] = Position.C9;
		walkthrough[22] = Position.B9;
		walkthrough[23] = Position.A9;
		walkthrough[24] = Position.A8;
		walkthrough[25] = Position.A7;
		walkthrough[26] = Position.B7;
		walkthrough[27] = Position.C7;
		walkthrough[28] = Position.D7;
		walkthrough[29] = Position.E7;
		walkthrough[30] = Position.F7;
		walkthrough[31] = Position.G6;
		walkthrough[32] = Position.G5;
		walkthrough[33] = Position.G4;
		walkthrough[34] = Position.G3;
		walkthrough[35] = Position.G2;
		walkthrough[36] = Position.G1;
		walkthrough[37] = Position.H1;
		walkthrough[38] = Position.I1;
		walkthrough[39] = Position.I2;
		walkthrough[40] = Position.I3;
		walkthrough[41] = Position.I4;
		walkthrough[42] = Position.I5;
		walkthrough[43] = Position.I6;
		walkthrough[44] = Position.J7;
		walkthrough[45] = Position.K7;
		walkthrough[46] = Position.L7;
		walkthrough[47] = Position.M7;
		walkthrough[48] = Position.N7;
		walkthrough[49] = Position.O7;
		walkthrough[50] = Position.O8;
		walkthrough[51] = Position.N8;
		walkthrough[52] = Position.M8;
		walkthrough[53] = Position.L8;
		walkthrough[54] = Position.K8;
		walkthrough[55] = Position.J8;
		walkthrough[56] = Position.I8;
		
		return walkthrough;
	}
	
	private Position[] createBlueWalkthrough() {
		walkthrough[0] = Position.G14;
		walkthrough[1] = Position.G13;
		walkthrough[2] = Position.G12;
		walkthrough[3] = Position.G11;
		walkthrough[4] = Position.G10;
		walkthrough[5] = Position.F9;
		walkthrough[6] = Position.E9;
		walkthrough[7] = Position.D9;
		walkthrough[8] = Position.C9;
		walkthrough[9] = Position.B9;
		walkthrough[10] = Position.A9;
		walkthrough[11] = Position.A8;
		walkthrough[12] = Position.A7;
		walkthrough[13] = Position.B7;
		walkthrough[14] = Position.C7;
		walkthrough[15] = Position.D7;
		walkthrough[16] = Position.E7;
		walkthrough[17] = Position.F7;
		walkthrough[18] = Position.G6;
		walkthrough[19] = Position.G5;
		walkthrough[20] = Position.G4;
		walkthrough[21] = Position.G3;
		walkthrough[22] = Position.G2;
		walkthrough[23] = Position.G1;
		walkthrough[24] = Position.H1;
		walkthrough[25] = Position.I1;
		walkthrough[26] = Position.I2;
		walkthrough[27] = Position.I3;
		walkthrough[28] = Position.I4;
		walkthrough[29] = Position.I5;
		walkthrough[30] = Position.I6;
		walkthrough[31] = Position.J7;
		walkthrough[32] = Position.K7;
		walkthrough[33] = Position.L7;
		walkthrough[34] = Position.M7;
		walkthrough[35] = Position.N7;
		walkthrough[36] = Position.O7;
		walkthrough[37] = Position.O8;
		walkthrough[38] = Position.O9;
		walkthrough[39] = Position.N9;
		walkthrough[40] = Position.M9;
		walkthrough[41] = Position.L9;
		walkthrough[42] = Position.K9;
		walkthrough[43] = Position.J9;
		walkthrough[44] = Position.I10;
		walkthrough[45] = Position.I11;
		walkthrough[46] = Position.I12;
		walkthrough[47] = Position.I13;
		walkthrough[48] = Position.I14;
		walkthrough[49] = Position.I15;
		walkthrough[50] = Position.H15;
		walkthrough[51] = Position.H14;
		walkthrough[52] = Position.H13;
		walkthrough[53] = Position.H12;
		walkthrough[54] = Position.H11;
		walkthrough[55] = Position.H10;
		walkthrough[56] = Position.H9;
		
		return walkthrough;
	}	
}
