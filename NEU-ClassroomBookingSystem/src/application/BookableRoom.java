package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookableRoom extends Room{
	private Connection connect;
    private PreparedStatement prepare;

	public BookableRoom(int roomId, int roomNum, int buildingNum, String buildingName, String bookableFlag) {
		super(roomId, roomNum, buildingNum, buildingName, bookableFlag);
	}

	@Override
	public void book(int userid, int roomid, int rsid, Date date, int start, int end, String status) {

		connect = Database.connectDB();
        try {
        	String insertBooking = "INSERT INTO bookings (userid,roomid,date,start_time,end_time,booking_status) VALUES(?,?,?,?,?,?)";
        	
			prepare = connect.prepareStatement(insertBooking);
			
			//insert into bookings table
	        prepare.setInt(1, userid);
	        prepare.setInt(2,roomid);
	        prepare.setDate(3, date);
	        prepare.setInt(4, start);
	        prepare.setInt(5, end);
	        prepare.setString(6, "Booked");

	        prepare.executeUpdate();
	        
	        //update booked column in roomstatus table
	        String updateRoomStatus = "UPDATE roomstatus SET booked = 1 WHERE rsid = ?";
	        
	        prepare = connect.prepareStatement(updateRoomStatus);
	        prepare.setInt(1, rsid);

	        prepare.executeUpdate();

	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Booking Message");
	        alert.setHeaderText(null);
	        alert.setContentText("Successfully Booked!");
	        alert.showAndWait();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
