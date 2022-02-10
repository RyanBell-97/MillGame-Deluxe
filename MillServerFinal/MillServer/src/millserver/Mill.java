package millserver;

public class Mill {
	private Location locations[];

	public Mill(Location[] locations) {
		this.locations= new Location[3];
		for (int i = 0; i < this.locations.length; i++) {
			this.locations[i] = locations[i];
		}
	}

	public Location[] getLocations() {
		return locations;
	}
	
	public boolean isActiveMill(){
		for (Location location : locations) {
			if(location.isEmpty()){
				return false;
			}
		}
		boolean isBlack= locations[0].containsBlack();
		if(isBlack== locations[1].containsBlack() && isBlack== locations[2].containsBlack()){
			return true;
		}
		return false;
	}
	
	public static boolean[] getBlackMillsStatus(Mill mills[]){
		boolean tempStatus[]= new boolean[16];
		int i= 0;
		for (Mill mill: mills) {
			tempStatus[i]= mill.isActiveBlackMill();
			++i;
		}
		return tempStatus;
	}
	
	public static boolean[] getWhiteMillsStatus(Mill mills[]){
		boolean tempStatus[]= new boolean[16];
		int i= 0;
		for (Mill mill: mills) {
			tempStatus[i]= mill.isActiveWhiteMill();
			++i;
		}
		return tempStatus;
	}
	
	public static boolean blackMillCreated(boolean mills_old[], boolean mills_new[]){
		for (int i = 0; i < mills_old.length; i++) {
			if(!mills_old[i] && mills_new[i])
				return true;
		}
		return false;
	}
	
	public static boolean whiteMillCreated(boolean mills_old[], boolean mills_new[]){
		for (int i = 0; i < mills_old.length; i++) {
			if(!mills_old[i] && mills_new[i]) return true;
		}
		return false;
	}
	
	/*public static boolean blackMillCreated(Mill mills_old[], Mill mills_new[]){
		for (int i = 0; i < mills_old.length; i++) {
			if(!mills_old[i].isActiveBlackMill() && mills_new[i].isActiveBlackMill()) return true;
		}
		return false;
	}
	
	public static boolean whiteMillCreated(Mill mills_old[], Mill mills_new[]){
		for (int i = 0; i < mills_old.length; i++) {
			if(!mills_old[i].isActiveWhiteMill() && mills_new[i].isActiveWhiteMill()) return true;
		}
		return false;
	}*/
	
	public boolean isActiveBlackMill(){
		if(this.isActiveMill()){
			return this.locations[0].getContent().isBlack();
		}
		return false;
	}
	
	public boolean isActiveWhiteMill(){
		if(this.isActiveMill()){
			return !this.locations[0].getContent().isBlack();
		}
		return false;
	}
	
	public boolean inActiveBlackMill(Location location){
		if(!this.isActiveBlackMill()) return false;
		for (Location location1 : locations) {
			if(location1.getIndex()== location.getIndex()) return true;
		}
		return false;
	}
	
	public boolean inActiveWhiteMill(Location location){
		if(!this.isActiveWhiteMill()) return false;
		for (Location location1 : locations) {
			if(location1.getIndex()== location.getIndex()) return true;
		}
		return false;
	}
	
	public static boolean inActiveMill(Mill mills[], Location location){
		for (Mill mill : mills) {
			if(mill.inActiveBlackMill(location) || mill.inActiveWhiteMill(location)) return true;
		}
		return false;
	}
}
