package mill;

public class Stone {
	boolean isBlack, onBoard, isCaptured;

	public Stone(boolean isBlack, boolean onBoard, boolean isCaptured) {
		this.isBlack = isBlack;
		this.onBoard = onBoard;
		this.isCaptured = isCaptured;
	}
	
	boolean isBlack(){
		return this.isBlack;
	}

	public boolean isOnBoard() {
		return onBoard;
	}

	public boolean isCaptured() {
		return isCaptured;
	}
	
	
}
