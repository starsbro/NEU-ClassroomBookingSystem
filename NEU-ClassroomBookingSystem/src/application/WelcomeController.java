package application;

import java.io.IOException;


/**
 * Sample Skeleton for 'Welcome.fxml' Controller Class
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	
	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	
	// @FXML // fx:id = "button_wellcom_exit"
	// private Button button_welcome_exit;
	
	@FXML // fx:id = "button_welcome_username"
	private TextField text_field_welcome_username; 
	
	@FXML // fx:id = "text_field_welcome_password"
	private TextField text_field_welcome_password; 
	
	@FXML // fx:id = "button_welcome_login"
	private Button button_welcome_login; //Value injected by FXMLLoader
	
	@FXML
	void Input_UserName(KeyEvent event) {
		
	}
	
	@FXML
	void Input_Password(KeyEvent event) {
		
	}
	
	@FXML
	
    private double x= 0 ;
    private double y= 0;
	
public void login(ActionEvent event){
        
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        
        Connection connect = Database.connectDB();
        String username = text_field_welcome_username.getText();
		String password = text_field_welcome_password.getText();
		
        try{ 
            Alert alert;
            
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            
            ResultSet result = prepare.executeQuery();

            if(username.isEmpty() || password.isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                	CurrentUser.userid = result.getInt("userid");
                	CurrentUser.username = result.getString("username");
                
                    // TO HIDE THE LOGIN FORM
                	button_welcome_login.getScene().getWindow().hide();
                	
                	// link to dashboard
    				Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
    				
    				Stage stage = new Stage();
    				Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event1) ->{
                        x = event1.getSceneX();
                        y = event1.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event2) ->{
                        stage.setX(event2.getScreenX() - x);
                        stage.setY(event2.getScreenY() - y);
                    });
    				
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
    				stage.setScene(scene);
    				stage.show();
                    
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Credentials!");
                    alert.showAndWait();
                }
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
	
	@FXML
	void handleEnterKey(KeyEvent event) {
		if(event.getCode().toString().equals("ENTER")) {
			String username = text_field_welcome_username.getText();
			String password = text_field_welcome_password.getText();
			if(username.equals("user1")&& password.equals("pass1")) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}	else if (username.equals("user2")&& password.equals("pass2")) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("userSearch.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}	else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Failed");
				alert.setHeaderText(null);
				alert.setContentText("Please enter the correct username and password again!");
				alert.showAndWait();
				
				text_field_welcome_username.setText("Incorrect, try again!");
				text_field_welcome_password.setText("Incorrect, try again!");
			}	
		}
	}
	
	@FXML
	void welcome_exit(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert button_welcome_login != null: "fx:id= \"button_welcome_exit\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert text_field_welcome_username != null: "fx:id= \"text_field_welcome_username\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert text_field_welcome_password != null: "fx:id= \"text_field_welcome_password\" was not injected: check your FXML file 'Welcome.fxml'.";
		
	}
	
}
