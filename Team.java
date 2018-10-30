
public class Team {
	
	Position[] walkthrough = new Position[57];
	Pawn[] pawn = new Pawn[4];
	
	public Team (String team) {
		
		switch (team) {
		
		case "Red":
			createRedWalkthrough(this);
			pawn[0] = new Pawn(Game.redTeam, 1, Position.B5, walkthrough);
			pawn[1] = new Pawn(Game.redTeam, 2, Position.E5, walkthrough);
			pawn[2] = new Pawn(Game.redTeam, 3, Position.B2, walkthrough);
			pawn[3] = new Pawn(Game.redTeam, 4, Position.E2, walkthrough);
			break;
			
		case "Green":
			createGreenWalkthrough(this);
			pawn[0] = new Pawn(Game.greenTeam, 1, Position.K2, walkthrough);
			pawn[1] = new Pawn(Game.greenTeam, 2, Position.K5, walkthrough);
			pawn[2] = new Pawn(Game.greenTeam, 3, Position.N2, walkthrough);
			pawn[3] = new Pawn(Game.greenTeam, 4, Position.N5, walkthrough);
			break;
			
		case "Yellow":
			createYellowWalkthrough(this);
			pawn[0] = new Pawn(Game.yellowTeam, 1, Position.N11, walkthrough);
			pawn[1] = new Pawn(Game.yellowTeam, 2, Position.K11, walkthrough);
			pawn[2] = new Pawn(Game.yellowTeam, 3, Position.N14, walkthrough);
			pawn[3] = new Pawn(Game.yellowTeam, 4, Position.K14, walkthrough);
			break;
			
		case "Blue":
			createBlueWalkthrough(this);
			pawn[0] = new Pawn(Game.blueTeam, 1, Position.E14, walkthrough);
			pawn[1] = new Pawn(Game.blueTeam, 2, Position.E11, walkthrough);
			pawn[2] = new Pawn(Game.blueTeam, 3, Position.B14, walkthrough);
			pawn[3] = new Pawn(Game.blueTeam, 4, Position.B11, walkthrough);
			break;
		}
	}
	
	private void createRedWalkthrough(Team team) {
		team.walkthrough[0] = Position.B7;
		team.walkthrough[1] = Position.C7;
		team.walkthrough[2] = Position.D7;
		team.walkthrough[3] = Position.E7;
		team.walkthrough[4] = Position.F7;
		team.walkthrough[5] = Position.G6;
		team.walkthrough[6] = Position.G5;
		team.walkthrough[7] = Position.G4;
		team.walkthrough[8] = Position.G3;
		team.walkthrough[9] = Position.G2;
		team.walkthrough[10] = Position.G1;
		team.walkthrough[11] = Position.H1;
		team.walkthrough[12] = Position.I1;
		team.walkthrough[13] = Position.I2;
		team.walkthrough[14] = Position.I3;
		team.walkthrough[15] = Position.I4;
		team.walkthrough[16] = Position.I5;
		team.walkthrough[17] = Position.I6;
		team.walkthrough[18] = Position.J7;
		team.walkthrough[19] = Position.K7;
		team.walkthrough[20] = Position.L7;
		team.walkthrough[21] = Position.M7;
		team.walkthrough[22] = Position.N7;
		team.walkthrough[23] = Position.O7;
		team.walkthrough[24] = Position.O8;
		team.walkthrough[25] = Position.O9;
		team.walkthrough[26] = Position.N9;
		team.walkthrough[27] = Position.M9;
		team.walkthrough[28] = Position.L9;
		team.walkthrough[29] = Position.K9;
		team.walkthrough[30] = Position.J9;
		team.walkthrough[31] = Position.I10;
		team.walkthrough[32] = Position.I11;
		team.walkthrough[33] = Position.I12;
		team.walkthrough[34] = Position.I13;
		team.walkthrough[35] = Position.I14;
		team.walkthrough[36] = Position.I15;
		team.walkthrough[37] = Position.H15;
		team.walkthrough[38] = Position.G15;
		team.walkthrough[39] = Position.G14;
		team.walkthrough[40] = Position.G13;
		team.walkthrough[41] = Position.G12;
		team.walkthrough[42] = Position.G11;
		team.walkthrough[43] = Position.G10;
		team.walkthrough[44] = Position.F9;
		team.walkthrough[45] = Position.E9;
		team.walkthrough[46] = Position.D9;
		team.walkthrough[47] = Position.C9;
		team.walkthrough[48] = Position.B9;
		team.walkthrough[49] = Position.A9;
		team.walkthrough[50] = Position.A8;
		team.walkthrough[51] = Position.B8;
		team.walkthrough[52] = Position.C8;
		team.walkthrough[53] = Position.D8;
		team.walkthrough[54] = Position.E8;
		team.walkthrough[55] = Position.F8;
		team.walkthrough[56] = Position.G8;
	}

	private void createGreenWalkthrough(Team team) {
		team.walkthrough[0] = Position.I2;
		team.walkthrough[1] = Position.I3;
		team.walkthrough[2] = Position.I4;
		team.walkthrough[3] = Position.I5;
		team.walkthrough[4] = Position.I6;
		team.walkthrough[5] = Position.J7;
		team.walkthrough[6] = Position.K7;
		team.walkthrough[7] = Position.L7;
		team.walkthrough[8] = Position.M7;
		team.walkthrough[9] = Position.N7;
		team.walkthrough[10] = Position.O7;
		team.walkthrough[11] = Position.O8;
		team.walkthrough[12] = Position.O9;
		team.walkthrough[13] = Position.N9;
		team.walkthrough[14] = Position.M9;
		team.walkthrough[15] = Position.L9;
		team.walkthrough[16] = Position.K9;
		team.walkthrough[17] = Position.J9;
		team.walkthrough[18] = Position.I10;
		team.walkthrough[19] = Position.I11;
		team.walkthrough[20] = Position.I12;
		team.walkthrough[21] = Position.I13;
		team.walkthrough[22] = Position.I14;
		team.walkthrough[23] = Position.I15;
		team.walkthrough[24] = Position.H15;
		team.walkthrough[25] = Position.G15;
		team.walkthrough[26] = Position.G14;
		team.walkthrough[27] = Position.G13;
		team.walkthrough[28] = Position.G12;
		team.walkthrough[29] = Position.G11;
		team.walkthrough[30] = Position.G10;
		team.walkthrough[31] = Position.F9;
		team.walkthrough[32] = Position.E9;
		team.walkthrough[33] = Position.D9;
		team.walkthrough[34] = Position.C9;
		team.walkthrough[35] = Position.B9;
		team.walkthrough[36] = Position.A9;
		team.walkthrough[37] = Position.A8;
		team.walkthrough[38] = Position.A7;
		team.walkthrough[39] = Position.B7;
		team.walkthrough[40] = Position.C7;
		team.walkthrough[41] = Position.D7;
		team.walkthrough[42] = Position.E7;
		team.walkthrough[43] = Position.F7;
		team.walkthrough[44] = Position.G6;
		team.walkthrough[45] = Position.G5;
		team.walkthrough[46] = Position.G4;
		team.walkthrough[47] = Position.G3;
		team.walkthrough[48] = Position.G2;
		team.walkthrough[49] = Position.G1;
		team.walkthrough[50] = Position.H1;
		team.walkthrough[51] = Position.H2;
		team.walkthrough[52] = Position.H3;
		team.walkthrough[53] = Position.H4;
		team.walkthrough[54] = Position.H5;
		team.walkthrough[55] = Position.H6;
		team.walkthrough[56] = Position.H7;
	}
	
	private void createYellowWalkthrough(Team team) {
		team.walkthrough[0] = Position.N9;
		team.walkthrough[1] = Position.M9;
		team.walkthrough[2] = Position.L9;
		team.walkthrough[3] = Position.K9;
		team.walkthrough[4] = Position.J9;
		team.walkthrough[5] = Position.I10;
		team.walkthrough[6] = Position.I11;
		team.walkthrough[7] = Position.I12;
		team.walkthrough[8] = Position.I13;
		team.walkthrough[9] = Position.I14;
		team.walkthrough[10] = Position.I15;
		team.walkthrough[11] = Position.H15;
		team.walkthrough[12] = Position.G15;
		team.walkthrough[13] = Position.G14;
		team.walkthrough[14] = Position.G13;
		team.walkthrough[15] = Position.G12;
		team.walkthrough[16] = Position.G11;
		team.walkthrough[17] = Position.G10;
		team.walkthrough[18] = Position.F9;
		team.walkthrough[19] = Position.E9;
		team.walkthrough[20] = Position.D9;
		team.walkthrough[21] = Position.C9;
		team.walkthrough[22] = Position.B9;
		team.walkthrough[23] = Position.A9;
		team.walkthrough[24] = Position.A8;
		team.walkthrough[25] = Position.A7;
		team.walkthrough[26] = Position.B7;
		team.walkthrough[27] = Position.C7;
		team.walkthrough[28] = Position.D7;
		team.walkthrough[29] = Position.E7;
		team.walkthrough[30] = Position.F7;
		team.walkthrough[31] = Position.G6;
		team.walkthrough[32] = Position.G5;
		team.walkthrough[33] = Position.G4;
		team.walkthrough[34] = Position.G3;
		team.walkthrough[35] = Position.G2;
		team.walkthrough[36] = Position.G1;
		team.walkthrough[37] = Position.H1;
		team.walkthrough[38] = Position.I1;
		team.walkthrough[39] = Position.I2;
		team.walkthrough[40] = Position.I3;
		team.walkthrough[41] = Position.I4;
		team.walkthrough[42] = Position.I5;
		team.walkthrough[43] = Position.I6;
		team.walkthrough[44] = Position.J7;
		team.walkthrough[45] = Position.K7;
		team.walkthrough[46] = Position.L7;
		team.walkthrough[47] = Position.M7;
		team.walkthrough[48] = Position.N7;
		team.walkthrough[49] = Position.O7;
		team.walkthrough[50] = Position.O8;
		team.walkthrough[51] = Position.N8;
		team.walkthrough[52] = Position.M8;
		team.walkthrough[53] = Position.L8;
		team.walkthrough[54] = Position.K8;
		team.walkthrough[55] = Position.J8;
		team.walkthrough[56] = Position.I8;
	}
	
	private void createBlueWalkthrough(Team team) {
		team.walkthrough[0] = Position.G14;
		team.walkthrough[1] = Position.G13;
		team.walkthrough[2] = Position.G12;
		team.walkthrough[3] = Position.G11;
		team.walkthrough[4] = Position.G10;
		team.walkthrough[5] = Position.F9;
		team.walkthrough[6] = Position.E9;
		team.walkthrough[7] = Position.D9;
		team.walkthrough[8] = Position.C9;
		team.walkthrough[9] = Position.B9;
		team.walkthrough[10] = Position.A9;
		team.walkthrough[11] = Position.A8;
		team.walkthrough[12] = Position.A7;
		team.walkthrough[13] = Position.B7;
		team.walkthrough[14] = Position.C7;
		team.walkthrough[15] = Position.D7;
		team.walkthrough[16] = Position.E7;
		team.walkthrough[17] = Position.F7;
		team.walkthrough[18] = Position.G6;
		team.walkthrough[19] = Position.G5;
		team.walkthrough[20] = Position.G4;
		team.walkthrough[21] = Position.G3;
		team.walkthrough[22] = Position.G2;
		team.walkthrough[23] = Position.G1;
		team.walkthrough[24] = Position.H1;
		team.walkthrough[25] = Position.I1;
		team.walkthrough[26] = Position.I2;
		team.walkthrough[27] = Position.I3;
		team.walkthrough[28] = Position.I4;
		team.walkthrough[29] = Position.I5;
		team.walkthrough[30] = Position.I6;
		team.walkthrough[31] = Position.J7;
		team.walkthrough[32] = Position.K7;
		team.walkthrough[33] = Position.L7;
		team.walkthrough[34] = Position.M7;
		team.walkthrough[35] = Position.N7;
		team.walkthrough[36] = Position.O7;
		team.walkthrough[37] = Position.O8;
		team.walkthrough[38] = Position.O9;
		team.walkthrough[39] = Position.N9;
		team.walkthrough[40] = Position.M9;
		team.walkthrough[41] = Position.L9;
		team.walkthrough[42] = Position.K9;
		team.walkthrough[43] = Position.J9;
		team.walkthrough[44] = Position.I10;
		team.walkthrough[45] = Position.I11;
		team.walkthrough[46] = Position.I12;
		team.walkthrough[47] = Position.I13;
		team.walkthrough[48] = Position.I14;
		team.walkthrough[49] = Position.I15;
		team.walkthrough[50] = Position.H15;
		team.walkthrough[51] = Position.H14;
		team.walkthrough[52] = Position.H13;
		team.walkthrough[53] = Position.H12;
		team.walkthrough[54] = Position.H11;
		team.walkthrough[55] = Position.H10;
		team.walkthrough[56] = Position.H9;
	}	
}
