
package mill;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
	private int blackRemaining, whiteRemaining, blackStartStones, whiteStartStones;
	private int fromIndexBlack;
	private int fromIndexWhite;
	int fromLocation= -1;
	private boolean gameOver;
	private boolean blackWon;
	private Location locations[]= new Location[24];;
	protected boolean turnOfBlack;
	private boolean millCreated;
	private boolean oldBlackMills[];
	private boolean oldWhiteMills[];
	private int nextTo[][]= new int[][]{
		{1,7}, //0
		{0,2,9}, //1
		{1,3}, //2
		{2,4,11}, //3
		{3,5}, //4
		{4,6,13}, //5
		{5,7}, //6
		{0,6,15}, //7
		{9,15}, //8
		{1,8,10,17}, //9
		{9,11}, //10
		{3,10,12,19}, //11
		{11,13}, //12
		{5,12,14,21}, //13
		{13,15}, //14
		{7,14,8,23}, //15
		{17,23}, //16
		{9,16,18}, //17
		{17,19}, //18
		{11,18,20}, //19
		{19,21}, //20
		{13,20,22}, //21
		{21,23}, //22
		{15,22,16} //23
		};
	private int[][] mills_indexes= new int[][]{
			{0,1,2}, //0*
			{0,7,6}, //1*
			{1,9,17}, //2*
			{2,3,4}, //3*
			{3,11,19}, //4*
			{4,5,6}, //5*
			{5,13,21}, //6*
			{8,9,10}, //7*
			{10,11,12}, //8*
			{12,13,14}, //9*
			{14,15,8}, //10*
			{16,17,18}, //11*
			{18,19,20}, //12*
			{20,21,22}, //13*
			{22,23,16}, //14*
			{23,15,7} //15*
		};
	private Mill mills[]= new Mill[mills_indexes.length];

	public Board(boolean turnOfBlack) {
		gameOver= false;
		this.turnOfBlack= turnOfBlack;
		this.millCreated= false;
		fromIndexBlack= -1;
		fromIndexWhite= -1;
		for (int i= 0; i< locations.length; i++) {
			locations[i]= new Location(null, i);
		}
		blackRemaining= whiteRemaining= 0;
		blackStartStones= whiteStartStones= 9;
		
		for (int j = 0; j < this.nextTo.length; j++) {
			Location tempLocations[]= new Location[this.nextTo[j].length];
			for (int k = 0; k < nextTo[j].length; k++) {
				tempLocations[k]= locations[this.nextTo[j][k]];
			}
			locations[j].setNeighbors(tempLocations);
		}
		
		for (int j = 0; j < mills_indexes.length; j++) {
			Location tempLocations[]= new Location[this.mills_indexes[j].length];
			for (int k = 0; k < mills_indexes[j].length; k++) {
				tempLocations[k]= locations[this.mills_indexes[j][k]];
			}
			mills[j]= new Mill(tempLocations);
		}
		
		oldBlackMills= Mill.getBlackMillsStatus(mills);
		oldWhiteMills= Mill.getWhiteMillsStatus(mills);
	}
	
	boolean getTurnOfBlack(){
		return turnOfBlack;
	}
	
	boolean putStone(int locationIndex, Stone stone){
		if(gameOver) return false;
		if(!validLocation(locationIndex)) return false;
		if(!this.locations[locationIndex].isEmpty()) return false;
		if(turnOfBlack && blackStartStones<=0) return false;
		if(!turnOfBlack && whiteStartStones<=0) return false;
		
		this.locations[locationIndex].setStone(new Stone(turnOfBlack, true, false));
		if(!canMove(!turnOfBlack)){
			if(turnOfBlack && whiteStartStones<= 0){
				gameOver= true;
				blackWon= true;
			}
			if(!turnOfBlack && blackStartStones<= 0){
				gameOver= true;
				blackWon= false;
			}
		}
		
		if (turnOfBlack) {
			--blackStartStones;
			++blackRemaining;
			if(Mill.blackMillCreated(oldBlackMills, Mill.getBlackMillsStatus(mills))){
				millCreated= true;
				oldBlackMills= Mill.getBlackMillsStatus(mills);
				return true;
			}
		} else {
			--whiteStartStones;
			++whiteRemaining;
			if(Mill.whiteMillCreated(oldWhiteMills, Mill.getWhiteMillsStatus(mills))){
				millCreated= true;
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
				return true;
			}
		}
		
		turnOfBlack= !turnOfBlack;
		return true;
	}
	
	boolean moveStone(int fromIndex, int toIndex){
		if(!validLocation(fromIndex) || !validLocation(toIndex)) return false;
		if(gameOver) return false;
		if(turnOfBlack && blackStartStones> 0) return false;
		if(!turnOfBlack && whiteStartStones> 0) return false;
		
		//Redundant
		if(locations[fromIndex].isEmpty() || !locations[toIndex].isEmpty()){
			return false;
		}
		
		if(!canMove(turnOfBlack)){
			if (turnOfBlack) {
				if (blackStartStones<= 0) {
					gameOver= true;
					return false;
				}
			} else {
				if (whiteStartStones<= 0) {
					gameOver= true;
					return false;
				}
			}
		}
		
		if(turnOfBlack && locations[fromIndex].containsWhite()) return false;
		if(!turnOfBlack && locations[fromIndex].containsBlack()) return false;
		
		if(locations[fromIndex].isNeighbor(locations[toIndex])){
			locations[toIndex].setStone(locations[fromIndex].getContent());
			locations[fromIndex].setStone(null);
			if(!canMove(!turnOfBlack)){
				if(turnOfBlack && whiteStartStones<= 0){
					gameOver= true;
					blackWon= true;
				}
				if(!turnOfBlack && blackStartStones<= 0){
					gameOver= true;
					blackWon= false;
				}
			}
			if(this.turnOfBlack){
				if(Mill.blackMillCreated(oldBlackMills, Mill.getBlackMillsStatus(mills))){
					millCreated= true;
					oldBlackMills= Mill.getBlackMillsStatus(mills);
					return true;
				}
				oldBlackMills= Mill.getBlackMillsStatus(mills);
			}else{
				if(Mill.whiteMillCreated(oldWhiteMills, Mill.getWhiteMillsStatus(mills))){
					millCreated= true;
					oldWhiteMills= Mill.getWhiteMillsStatus(mills);
					return true;
				}
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
			}
			turnOfBlack= !turnOfBlack;
			return true;
		}
		return false;
	}

	
	
	boolean gameOver(){
		return gameOver;
	}
	
	boolean removeStone(int toRemove){
		if(gameOver) return false;
		if(!validLocation(toRemove)) return false;
		if(locations[toRemove].isEmpty()) return false;
		if(!millCreated) return false;
		if(turnOfBlack && locations[toRemove].containsBlack()) return false;
		if(!turnOfBlack && locations[toRemove].containsWhite()) return false;
		
		if(this.turnOfBlack){
			if(!Mill.inActiveMill(mills, locations[toRemove])) {
				if(locations[toRemove].containsWhite()) locations[toRemove].setStone(null);
				if(!canMove(!turnOfBlack)){
					if(whiteStartStones<= 0){
						gameOver= true;
						blackWon= true;
					}
				}
				turnOfBlack= !turnOfBlack;
				--whiteRemaining;
				oldBlackMills= Mill.getBlackMillsStatus(mills);
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
				millCreated= false;
				if(whiteRemaining<= 2 && whiteStartStones<= 0){
					gameOver= true;
					blackWon= true;
				}
				return true;
			}else if(onlyWhiteMillsLeft()){
				if(locations[toRemove].containsWhite()) locations[toRemove].setStone(null);
				if(!canMove(!turnOfBlack)){
					if(whiteStartStones<= 0){
						gameOver= true;
						blackWon= true;
					}
				}
				turnOfBlack= !turnOfBlack;
				--whiteRemaining;
				oldBlackMills= Mill.getBlackMillsStatus(mills);
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
				millCreated= false;
				if(whiteRemaining<= 2 && whiteStartStones<= 0){
					gameOver= true;
					blackWon= true;
				}
				return true;
			}
		}
		if(!this.turnOfBlack){
			if(!Mill.inActiveMill(mills, locations[toRemove])) {
				if(locations[toRemove].containsBlack()) locations[toRemove].setStone(null);
				if(!canMove(!turnOfBlack)){
					if(blackStartStones<= 0){
						gameOver= true;
						blackWon= false;
					}
				}
				turnOfBlack= !turnOfBlack;
				--blackRemaining;
				millCreated= false;
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
				oldBlackMills= Mill.getBlackMillsStatus(mills);
				if(blackRemaining<= 2 && blackStartStones<= 0){
					gameOver= true;
					blackWon= false;
				}
				return true;
			}else if(onlyBlackMillsLeft()){
				if(locations[toRemove].containsBlack()) locations[toRemove].setStone(null);
				if(!canMove(!turnOfBlack)){
					if(blackStartStones<= 0){
						gameOver= true;
						blackWon= false;
					}
				}
				turnOfBlack= !turnOfBlack;
				--blackRemaining;
				oldWhiteMills= Mill.getWhiteMillsStatus(mills);
				oldBlackMills= Mill.getBlackMillsStatus(mills);
				millCreated= false;
				if(blackRemaining<= 2 && blackStartStones<= 0){
					gameOver= true;
					blackWon= false;
				}
				return true;
			}
		}
		
		return false;
	}
	
	public boolean getBlackWon(){
		return blackWon;
	}

	public int getBlackStartStones() {
		return blackStartStones;
	}

	public int getWhiteStartStones() {
		return whiteStartStones;
	}

	public boolean isBlackWon() {
		return blackWon;
	}
	
	public String getBoardContentAsString(){
		String temp= "";
		for (int i = 0; i < 24; i++) {
			if(locationContainsBlackStone(i)){
				temp= temp.concat("1#");
			}else if(locationContainsWhiteStone(i)){
				temp= temp.concat("2#");
			}else{
				temp= temp.concat("0#");
			}
		}
		//System.out.println(temp);
		//temp= temp.concat("#");
		return temp;
	}
	
	public int[] getBoardContentAsArray(){
		int boardContentArray[]= new int[24];
		for (int i = 0; i < 24; i++) {
			if(locationContainsBlackStone(i)){
				boardContentArray[i]= 1;
			}else if(locationContainsWhiteStone(i)){
				boardContentArray[i]= 2;
			}else{
				boardContentArray[i]= 0;
			}
		}
		//System.out.println(temp);
		//temp= temp.concat("#");
		return boardContentArray;
	}
	
	public boolean millCreated(){
		return millCreated;
	}
	
	boolean canMove(boolean black){
		for (Location location : locations) {
			if(location.hasEmptyNeighbor()){
				if(location.containsBlack() && turnOfBlack) return true;
				if(location.containsWhite() && !turnOfBlack) return true;
			}
		}
		return false;
	}
	
	boolean onlyBlackMillsLeft(){
		for (Location location : locations) {
			if(!Mill.inActiveMill(mills, location) && location.containsBlack()) return false;
		}
		return true;
	}
	
	boolean onlyWhiteMillsLeft(){
		for (Location location : locations) {
			if(!Mill.inActiveMill(mills, location) && location.containsWhite()) return false;
		}
		return true;
	}
	
	int getBlackRemaining(){
		return blackRemaining;
	}
	int getwhiteRemaining(){
		return whiteRemaining;
	}
	boolean locationEmpty(int locationIndex){
		if(!validLocation(locationIndex)) return false;
		return locations[locationIndex].isEmpty();
	}
	boolean locationContainsBlackStone(int locationIndex){
		if(!validLocation(locationIndex)) return false;
		return locations[locationIndex].containsBlack();
	}
	boolean locationContainsWhiteStone(int locationIndex){
		if(!validLocation(locationIndex)) return false;
		return locations[locationIndex].containsWhite();
	}
	
	private boolean validLocation(int location){
		if(location< 0 || location> 23){
			return false;
		}
		return true;
	}
	
	
	
	public String execute(int index, boolean isBlack){
		String temp= "";
		/*try {
			this.wait(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
		}*/
		System.out.println("execute "+ index+ " as black? "+ isBlack+ " turn of black? "+ turnOfBlack);
		if(isBlack!= turnOfBlack) return "unauth";
		if(gameOver) return "game-over";
		
		if(this.millCreated()){
			if(this.removeStone(index)){
				if (this.getTurnOfBlack()) {
					//System.out.println("black-stone-removed");
					return "black-stone-removed";
				} else {
					//System.out.println("white-stone-removed");
					return "white-stone-removed";
				}
			}
		}else if(this.getTurnOfBlack()){
			if(blackStartStones>0){
				if(this.putStone(index, new Stone(true, true, false))){
					if (this.millCreated()) {
						return "Black has a mill!";
					} else {
						return "black-stone-placed";
					}
				}
			}else{
				if(this.moveStone(fromLocation, index)){
					fromLocation= -1;
					if (this.millCreated()) {
						return "Black has a mill!";
					} else {
						return "black-stone-placed";
					}
				}else{
					fromLocation= index;
					//System.out.println("black-from-index-selected");
					return "black-from-index-selected";
				}
			}
		}else{
			if(whiteStartStones>0){
				if(this.putStone(index, new Stone(false, true, false))){
					if (this.millCreated()) {
						return "White has a mill!";
					} else {
						return "white-stone-placed";
					}
				}
			}else{
				if(this.moveStone(fromLocation, index)){
					fromLocation= -1;
					if (this.millCreated()) {
						return "White has a mill!";
					} else {
						return "white-stone-placed";
					}
				}else{
					fromLocation= index;
					//System.out.println("white-from-index-selected");
					return "white-from-index-selected";
				}
			}
		}
		
		return "nothing-happened";
	}
	
}
