package application;
	

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	//accessible to all screens, set the values for these two when successfully logged in
	
	public static Connection connection;
	
	@Override
	public void start(Stage primaryStage) throws IOException, SQLException {
		
		// set up income IO interface

		Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(root);
			
		primaryStage.setTitle("NEU Classroom Searching and Booking System");
		primaryStage.setScene(scene);
		primaryStage.show();

		
		
		try {
			//test database connection
			Database db = new Database();
//			db.getData();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
