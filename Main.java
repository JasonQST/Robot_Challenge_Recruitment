import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	 static List<Robot> robotList = new ArrayList<Robot>();//Store all robots' information
	 static int active=1;//Record the active robot
	 static BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
	 public static void main(String[] args) throws IOException{ 
		//Console Menu
		 System.out.println("All the available commands are listed below:");
		 System.out.println("PLACE X,Y,F (Place a robot)");
		 System.out.println("ROBOT <integer> (Change active robot)");
		 System.out.println("MOVE (Move forward)");
		 System.out.println("LEFT (Turn left)");
		 System.out.println("RIGHT (Turn right)");
		 System.out.println("REPORT (Show all the robots' position)");
		 System.out.println();
		 while(true) {
			 System.out.print("Your command: ");
			 String response = obj.readLine();//Get user input response
			 String[] responseSplit = response.split( "[\\s,]+" ); //Split response to identify command
			 if (responseSplit[0].equals("PLACE") && responseSplit.length == 4 && isNumeric(responseSplit[1]) && isNumeric(responseSplit[2])) {
				 try {
					 int inputX = Integer.parseInt(responseSplit[1]);//Get the X axis value
					 int inputY = Integer.parseInt(responseSplit[2]);//Get the Y axis value
					 String inputFace = responseSplit[3];//Get the facing direction
					 place(inputFace, inputX, inputY);//Call place function to place a robot
				 }catch(NumberFormatException e){
					 System.out.println("Please input integer only.");//Inform the user to input integer only
					 System.out.println("-----------------------");
				 }
			 } else if (responseSplit[0].equals("REPORT")) {
				 report();//Call report function to show all the robots' position.
			 } else if (responseSplit[0].equals("MOVE")) {
				 if(robotList.isEmpty()) {
					 System.out.println("Please place a robot first.");//Inform user to place a robot before operate
					 System.out.println("-----------------------");
				 } else {
					 Robot selectedRobot = robotList.get(active-1);// If the selected robot exist, get the robot from the list
					 selectedRobot = move(selectedRobot);//Call move function to move the robot (position validation is in the validate function)
					 robotList.set(active-1, selectedRobot);//update the selected robot to the list
				 }
			 } else if (responseSplit[0].equals("LEFT")) {
				 if(robotList.isEmpty()) {
					 System.out.println("Please place a robot first.");//Inform user to place a robot before operate
					 System.out.println("-----------------------");
				 } else {
					 Robot selectedRobot = robotList.get(active-1);// If the selected robot exist, get the robot from the list
					 selectedRobot = left(selectedRobot);//Call left function to turn the robot to the left
					 robotList.set(active-1, selectedRobot);//update the selected robot to the list 
				 }
			 } else if (responseSplit[0].equals("RIGHT")) {
				 if(robotList.isEmpty()) {
					 System.out.println("Please place a robot first.");//Inform user to place a robot before operate
					 System.out.println("-----------------------");
				 } else {
					 Robot selectedRobot = robotList.get(active-1);// If the selected robot exist, get the robot from the list
					 selectedRobot = right(selectedRobot);//Call right function to turn the robot to the right
					 robotList.set(active-1, selectedRobot);//update the selected robot to the list
				 }
			 } else if (responseSplit[0].equals("ROBOT") && responseSplit.length == 2 && isNumeric(responseSplit[1])){
				 active(responseSplit[1]);//Call active function to active robot
			 } else if (responseSplit[0].equals("EXIT")) {
				 break;//Exit the loop
			 } else {
				 System.out.println("Invalid command. Please try again.");//Inform user that the command is invalid
				 System.out.println("-----------------------");
				 }
			 }
		 }
	 
	 //Place Robot
	 public static void place(String face, int x, int y) {
		 int id = robotList.size()+1;//Assign id to the new robot
		 Robot addRobot = new Robot(id, face, x, y);//make a new robot
		 if(validate(addRobot)) {
			 robotList.add(addRobot);//add the robot to the list
			 System.out.println("Successfully place the robot.");//inform the user the robot successfully placed
			 System.out.println("-----------------------");
		 } else {
			 System.out.println("Fail to place the robot");//Inform the user that the robot fail to place
			 System.out.println("-----------------------");
		 }
		 
	 }
	 

	 //Report robot current position
	 public static void report() {
		 for(Robot robotOutput:robotList) {
			 System.out.println(robotOutput.report());//For each robot in the list, call the report function in the robot's class to show all the robots' position
		 }
		 if(robotList.size()>0) {
			 System.out.println(robotList.size() + " robots in total.");//Get robots amount and show to the user
			 System.out.println("The active robot is Robot " + active);//Get the active robot's id and show to the user
		 } else {
			 System.out.println("There are no robot currently.");//Inform user that there are no robot currently.
		 }
		 System.out.println("-----------------------");
	 }
	 
	 //Check if the position is valid
	 public static boolean validate(Robot robot) {
		 if(robot.x <= 5 && robot.x >= 0 && robot.y <= 5 && robot.y >=0) {
			 if(robot.face.equals("NORTH") || robot.face.equals("EAST") || robot.face.equals("SOUTH") || robot.face.equals("WEST")) {
				 return true;//return true if the facing direction is valid
			 } else {
				 System.out.println("Invalid facing direction");
				 return false;//return false if the facing direction is invalid
			 }
		 }else {
			 System.out.println("Invalid position");
			 return false;//return false if the position is invalid
		 }
	 }
	 
	 //Move the robot forward
	 public static Robot move(Robot robot) {
		 Robot backupRobot = new Robot(robot.id, robot.face, robot.x, robot.y);//Make a backup to store the original position of the robot
		 if(robot.face.equals("NORTH")) {
			 robot.y ++;//The Y axis value +1 if facing north 
		 } else if (robot.face.equals("EAST")) {
			 robot.x ++;//The X axis value +1 if facing east 
		 } else if (robot.face.equals("SOUTH")) {
			 robot.y --;//The Y axis value -1 if facing south 
		 } else if (robot.face.equals("WEST")) {
			 robot.x --;//The X axis value -1 if facing west 
		 }
		 if(validate(robot)) {
			 System.out.println("The robot successfully moved.");
			 System.out.println("-----------------------");
			 return robot;//If the position after move is valid, return the new position
		 } else {
			 System.out.println("The robot cannot move this way.");
			 System.out.println("-----------------------");
			 return backupRobot;//If the position after move is invalid, return the original position
		 }
	 }
	 
	 //Turn the robot to the left
	 public static Robot left(Robot robot) {
		 switch(robot.face) {
		 case "NORTH":
			 robot.face = "WEST";//Change the robot to the correct direction
			 break;
		 case "EAST":
			 robot.face = "NORTH";//Change the robot to the correct direction
			 break;
		 case "SOUTH":
			 robot.face = "EAST";//Change the robot to the correct direction
			 break;
		 case "WEST":
			 robot.face = "SOUTH";//Change the robot to the correct direction
		 default:
			 break;
		 }
		 System.out.println("The robot succeefully turn left");//Inform the user that the operation successfully done
		 System.out.println("-----------------------");
		 return robot;//return the robot with the new direction
	 }
	 
	 //Turn the robot to the right
	 public static Robot right(Robot robot) {
		 switch(robot.face) {
		 case "NORTH":
			 robot.face = "EAST";//Change the robot to the correct direction
			 break;
		 case "EAST":
			 robot.face = "SOUTH";//Change the robot to the correct direction
			 break;
		 case "SOUTH":
			 robot.face = "WEST";//Change the robot to the correct direction
			 break;
		 case "WEST":
			 robot.face = "NORTH";//Change the robot to the correct direction
		 default:
			 break;
		 }
		 System.out.println("The robot succeefully turn right");//Inform the user that the operation successfully done
		 System.out.println("-----------------------");
		 return robot;//return the robot with the new direction
	 }
	 
	 //Check if the boolean is numeric
	 public static boolean isNumeric(String str){
	        return str != null && str.matches("[0-9.]+");//Check if the string only contain numbers
	 }
	 
	 //Active Robot
	 public static void  active(String input) {
		 try {
			 if(Integer.parseInt(input)>0 && Integer.parseInt(input)<=robotList.size()) {
				 active = Integer.parseInt(input);//update the value of active
				 System.out.println("Robot " + input + " is active now");//Inform the user that the robot is active
				 System.out.println("-----------------------");
			 } else {
				System.out.println("The robot does not exist"); //Inform the user that the robot does not exist
				System.out.println("-----------------------");
			 }
		 } catch(NumberFormatException e){
			 System.out.println("Please input Robot <integer> only.");//Inform the user to input integer only.
			 System.out.println("-----------------------");
		 }
	 }
}
