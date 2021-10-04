
public class Robot {
	int id; //robot's id
	String face; // robot's facing direction
	int x; // X axis position
	int y; // Y axis position
	
	//Constructor
	public Robot(int id, String face, int x, int y) {
		this.id = id;
		this.face = face;
		this.x = x;
		this.y = y;
	}
	
	//Report the position of the robot
	public String report() {
		return("Robot" + this.id + ": " + this.x + ", " + this.y + ", " + this.face);	
	}
}