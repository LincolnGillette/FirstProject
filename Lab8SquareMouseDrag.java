import java.util.*; //importing potentially necessary packages
import java.io.*;
import java.text.*;
import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import java.text.*;
import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.animation.*;

public class Lab8SquareMouseDrag extends Application
{
   FlowPane root = new FlowPane();
   Canvas canvas = new Canvas(500,500);
   GraphicsContext gc = canvas.getGraphicsContext2D();
   
   public void start(Stage stage)
   {
      gc.setFill(Color.DODGERBLUE);
      gc.fillRect(237,237,25,25);
      
      
      root.getChildren().add(canvas);
      Scene scene = new Scene(root,500,500);
      stage.setScene(scene);
      stage.setTitle("Lab 8 Part 2");
      stage.show();
   }
   
   public static void main(String[] args)
   {
      launch(args);
   }
   
   
}