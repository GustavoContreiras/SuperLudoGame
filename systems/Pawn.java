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
	
	public boolean canWalk (int rolledDice) {
		
		int newPosInx = this.positionInx + rolledDice;
		int nextPosInx = this.positionInx + 1;
		
		//checa se tem barreira na proxima posicao
		for (int i = 0; i < rolledDice; i++) {
			
			if (this.walkthrough[nextPosInx].pawn[0] != null & this.walkthrough[nextPosInx].pawn[1] != null) {
				if (this.walkthrough[nextPosInx].pawn[0].team == this.walkthrough[nextPosInx].pawn[1].team) {
					return false;
				}
			}
			nextPosInx++;
		}
		
		//checa se onde vai parar já tem 2 peões
		if (this.walkthrough[newPosInx].pawn[0] != null & this.walkthrough[newPosInx].pawn[1] != null) {
			return false;
		}
		
		//checa se ultrapassa a casa final
		if (newPosInx > 56) {
			return false;
		}
		
		return true;
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
				
				//se for casa de saída...
				if (newPos == Position.B7 | newPos == Position.I2 |
					newPos == Position.N9 | newPos == Position.G14) {
					
					System.out.println("New position is exit house.");
				
					//se a nova posicao for a casa de saida vermelha
					if (newPos == Position.B7) {
						
						//se tiver um peao vermelho na casa de saida vermelha
						if (newPos.pawn[0].team == Game.redTeam) {
							newPos.pawn[1] = pawn;
						}
						
						//se nao tiver peao vermelho na casa de saida vermelha
						else {
							
							//se o peao movido for do time vermelho (os dois dividem)
							if (pawn.team == Game.redTeam) {
								newPos.pawn[1] = pawn;
							}
							
							//se o peao movido nao for do time vermelho (ocorre captura)
							else {
								newPos.pawn[0].position = newPos.pawn[0].homePosition;
								newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
								newPos.pawn[0].positionInx = -1;
								
								newPos.pawn[0] = pawn;
							}
						}
					}
					
					//se a nova posicao for a casa de saida verde
					else if (newPos == Position.I2) {
						
						//se tiver um peao verde na casa de saida verde
						if (newPos.pawn[0].team == Game.greenTeam) {
							newPos.pawn[1] = pawn;
						}
						
						//se nao tiver peao verde na casa de saida verde
						else {
							
							//se o peao movido for do time verde (os dois dividem)
							if (pawn.team == Game.greenTeam) {
								newPos.pawn[1] = pawn;
							}
							
							//se o peao movido nao for do time verde (ocorre captura)
							else {
								newPos.pawn[0].position = newPos.pawn[0].homePosition;
								newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
								newPos.pawn[0].positionInx = -1;
								
								newPos.pawn[0] = pawn;
							}
						}
					}
					
					else if (newPos == Position.N9) {
						if (newPos.pawn[0].team == Game.yellowTeam) {
							newPos.pawn[1] = pawn;
						}
						
						//se nao tiver peao amarelo na casa de saida amarela
						else {
							
							//se o peao movido for do time amarela (os dois dividem)
							if (pawn.team == Game.yellowTeam) {
								newPos.pawn[1] = pawn;
							}
							
							//se o peao movido nao for do time amarela (ocorre captura)
							else {
								newPos.pawn[0].position = newPos.pawn[0].homePosition;
								newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
								newPos.pawn[0].positionInx = -1;
								
								newPos.pawn[0] = pawn;
							}
						}
					}
					
					else if (newPos == Position.G14) {
						if (newPos.pawn[0].team == Game.blueTeam) {
							newPos.pawn[1] = pawn;
						}
						
						//se nao tiver peao azul na casa de saida azul
						else {
							
							//se o peao movido for do time azul (os dois dividem)
							if (pawn.team == Game.blueTeam) {
								newPos.pawn[1] = pawn;
							}
							
							//se o peao movido nao for do time azul (ocorre captura)
							else {
								newPos.pawn[0].position = newPos.pawn[0].homePosition;
								newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
								newPos.pawn[0].positionInx = -1;
								
								newPos.pawn[0] = pawn;
							}
						}
					}
					
					else if (pawn.positionInx == -1) {
						newPos.pawn[1] = pawn;
					}
					
					//se o peao na casa de saida nao for da cor da casa de saida (eh capturado)
					else {
						newPos.pawn[0].position = newPos.pawn[0].homePosition;
						newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
						newPos.pawn[0].positionInx = -1;
						
						newPos.pawn[0] = pawn;
					}
					
				}
				
				//se for abrigo
				else if (newPos == Position.B9 | newPos == Position.G2 |
						 newPos == Position.N7 | newPos == Position.I14) {
					
					System.out.println("New position is safe place.");
					
					newPos.pawn[1] = pawn;
				}
				
				//se for nao for casa de saida nem abrigo...
				else {
					System.out.println("New position is not exit house or safe place.");
					
					newPos.pawn[0].position = newPos.pawn[0].homePosition;
					newPos.pawn[0].homePosition.pawn[0] = newPos.pawn[0];
					newPos.pawn[0].positionInx = -1;
					
					newPos.pawn[0] = pawn;
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
