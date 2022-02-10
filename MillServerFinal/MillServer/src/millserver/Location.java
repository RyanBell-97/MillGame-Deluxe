
package millserver;

public class Location {
	private Stone content;
	private int index= 0;
	private Location neighbors[];

	/*public Location() {
		this.content= null;
	}*/
	
	public Location(Stone content, int index){
		this.content= content;
		this.index= index;
	}
	
	public void setStone(Stone stone){
		this.content= stone;
	}
	
	public void setNeighbors(Location neighbors[]){
		this.neighbors= neighbors;
	}
	
	Stone getContent(){
		return content;
	}
	int getIndex(){
		return index;
	}
	
	boolean isEmpty(){
		if(content== null){
			return true;
		}
		return false;
	}
	
	boolean containsBlack(){
		if(this.isEmpty()) return false;
		return content.isBlack();
	}
	
	boolean containsWhite(){
		if(content== null){
			return false;
		}
		return !content.isBlack();
	}
	
	boolean isNeighbor(Location location){
		for (Location neighbor : neighbors) {
			if(neighbor.getIndex()== location.getIndex()){
				return true;
			}
		}
		return false;
	}
	
	boolean hasEmptyNeighbor(){
		for (Location neighbor : neighbors) {
			if(neighbor.isEmpty()) return true;
		}
		return false;
	}
}
