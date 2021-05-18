/*A program that creates a maze game based off a matrix of numbers in a file
written by Lincoln Gillette*/

import java.util.*; //importing potentially necessary packages
import java.io.*;
import java.text.*;
import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.animation.*;

public class ProjectMaze extends Application
{
   FlowPane root = new FlowPane(); //the root to hold everything
   MazeCanvas canvas = new MazeCanvas(); //the canvas to paint stuff on
   int [][] MazeArray; //the 2D array to hold the numbers from the file
   
   public void start(Stage stage)
   {
      
      root.getChildren().add(canvas); //adding the canvas to the root
      Scene scene = new Scene(root,525,525); //creating a 525x525 scene
      stage.setScene(scene);
      stage.setTitle("Maze Project"); //title of the application
      stage.show();
      
      canvas.requestFocus();
   }
   
   public static void main(String[] args)
   {
      launch(args); //making everything appear
   }
   
   public class MazeCanvas extends Canvas
   {
      GraphicsContext gc = getGraphicsContext2D(); //initializing the graphics context
      int topStartXCoord, playerXCoord, playerYCoord; //delcaring variables
      boolean allowed;
      
      public MazeCanvas()
      {
         setHeight(525); //canvas dimensions
         setWidth(525);
         setOnKeyPressed(new KeyListener()); //if a key is pressed create a new keylistener object
         
         try
         {
            Scanner read  = new Scanner(new File("ProjectMazeNumbers.txt")); //try reading the numbers from the file
            MazeArray = new int [21][21]; //make a 21x21 array for all the numbers and put them into an array called MazeArray
            for(int i=0;i<21;i++)
            {
               for(int j=0;j<21;j++)
               {
                  MazeArray[i][j]=read.nextInt(); //read the next int from the file into the next column j of the row i
               }
            }
            
         }
         catch(FileNotFoundException fnfe) //if the file cannot be found
         {
            System.out.println("The file was not found");//print this message
         }
         
         setTopStartXCoord();//calling a method setting where the player starts 
         playerXCoord = getTopStartXCoord();//setting current location of player in x direction
         playerYCoord = 0; //current location of player in y direction
         
         drawMaze(gc); //draw the maze
         drawPlayer(gc); //draw the player in the maze
         
      }
      
      public void drawMaze(GraphicsContext gc) //drawing the maze
      {
         for(int i=0;i<21;i++) //loop through 2d maze array
         {
            for(int j=0;j<21;j++)
            {
               if(MazeArray[i][j]==0)//if the array element is 0, paint a 25x25 white square at that location
               {
                  gc.setFill(Color.WHITE);
                  gc.fillRect(j*25,i*25,25,25);
               }
               else //if the array element is 1, paint a 25x25 black square at that location
               {
                  gc.setFill(Color.BLACK);
                  gc.fillRect(j*25,i*25,25,25);
               }
            }
         }
      }
      
      
      public void drawPlayer(GraphicsContext gc)//drawing the player
      {
         gc.setFill(Color.CYAN);//the player is cyan colored
         gc.fillRect(playerXCoord,playerYCoord,25,25);//the player has a location. the player's size is 25x25
         
      }
        
      public void setTopStartXCoord()//method for setting player's starting position
      {
         for(int i=0;i<21;i++)//loop through the maze array
         {
            if(MazeArray[0][i]==0) //if any element in the first maze array array is 0, that's where the player starts
            {
               topStartXCoord = 25*i;
            }
         }         
      }
      
      public int getTopStartXCoord()//accessor method for the starting coordinate
      {
         return topStartXCoord;
      }
      
      
      public class KeyListener implements EventHandler<KeyEvent>  //if a key is pressed a new instance of this class will be created
      {
         public void handle(KeyEvent event) 
         {
            if(event.getCode()==KeyCode.UP) //if the up key is pressed
            {
               if(playerYCoord>=25 && MazeArray[Math.abs(playerYCoord-25)/25][playerXCoord/25]==0) //if the player is not at the top of the maze and if the coordinates match up with an array element equal to 0
               {
                  gc.clearRect(0,0,525,525); //clear the canvas
                  playerYCoord-=25;//move the player up
                  drawMaze(gc); //draw the maze
                  drawPlayer(gc);//draw the player
               }
            }
            else if(event.getCode()==KeyCode.DOWN)//if the down key is pressed
            {
               if(playerYCoord<=475 && MazeArray[(playerYCoord+25)/25][playerXCoord/25]==0)//if the player is not at the bottom of the maze and if the coordinates match up with an array element equal to 0
               {
                  gc.clearRect(0,0,525,525);//clear the canvas
                  playerYCoord+=25;//move the player down
                  drawMaze(gc);//draw the maze
                  drawPlayer(gc);//draw the player
               }
               if(playerYCoord==500)//if the player reaches the bottom of the maze
               {
                  System.out.println("You win!");//print the message
               }
            }
            else if(event.getCode()==KeyCode.LEFT)//if the left key is pressed
            {
               if(playerXCoord>=25 && MazeArray[playerYCoord/25][Math.abs(playerXCoord-25)/25]==0)//if the player is not at the left edge of the maze and if the coordinates match up with an array element equal to 0
               {
                  gc.clearRect(0,0,525,525);//clear the canvas
                  playerXCoord-=25;//move the player left
                  drawMaze(gc);//draw the maze
                  drawPlayer(gc);//draw the player
               }
               
            }
            else if(event.getCode()==KeyCode.RIGHT)//if the right key is pressed
            {
               if(playerXCoord<=475 && MazeArray[playerYCoord/25][(playerXCoord+25)/25]==0)//if the player is not at the right edge of the maze and if the coordinates match up with an array element equal to 0
               {
                  gc.clearRect(0,0,525,525);//clear the canvas
                  playerXCoord+=25;//move the player right
                  drawMaze(gc);//draw the maze
                  drawPlayer(gc);//draw the player
               }
            }
         }
      }
   }
}