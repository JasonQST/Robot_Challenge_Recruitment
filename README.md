# Robot_Challenge_IOOF

This repository is for sharing the source code of the robot challenge of IOOF. The source code is written in Java.

To use the program, the following commands are available for you:
PLACE X,Y,F (Place a robot)
ROBOT <integer> (Change active robot)
MOVE (Move forward)
LEFT (Turn left)
RIGHT (Turn right)
REPORT (Show number of robots, active robot, and all the robots' positions)
  
Commands will need to be input one by one and the program will inform you if your command was successfully conducted or not.
  
Here are two examples for your convenience to test the program.

Example 1 (Report Multiple Robots):
PLACE 2,3,NORTH
PLACE 4,2,WEST
PLACE 5,3,EAST
PLACE 2,1,SOUTH
REPORT
  
Example 2 (Change Acitive Robots, Turning & Avoid Robot Moving Out of Scope):
PLACE 2,3,EAST
PLACE 4,2,NORTH
MOVE
LEFT
ROBOT 2
RIGHT
MOVE
MOVE (Will fail)
REPORT
