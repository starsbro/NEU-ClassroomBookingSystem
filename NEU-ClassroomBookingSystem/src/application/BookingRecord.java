package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookingRecord {
	private Date date;
	private int roomNum;
	private String buildingName;
	private int startTime;
	private int endTime;
	private int rsid;
	private String bookingStatus;
	
	// attributes that not shown in dashboard table
	private int roomid;
	private int bookingId;
	
    private PreparedStatement prepare;
    
	public BookingRecord(Date date, int rsid, int roomNum, String buildingName, int startTime, int endTime, String bookingStatus) {
		super();
		this.date = date;
		this.rsid = rsid;
		this.roomNum = roomNum;
		this.buildingName = buildingName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.bookingStatus = bookingStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public void cancelBooking(Connection connect, int rsid, int bookingid) {

        try {
	        //update booked column in roomstatus table
        	String updateRoomStatus = "UPDATE roomstatus SET booked = 0 WHERE rsid = ?";
	        prepare = connect.prepareStatement(updateRoomStatus);
	        prepare.setInt(1, rsid);
	        prepare.executeUpdate();
	        
	        String updateBookings = "update bookings set booking_status='Canceled' where bookingid = ? ";
	        prepare = connect.prepareStatement(updateBookings);
	        prepare.setInt(1, bookingid);
	        prepare.executeUpdate();

	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Cancel Message");
	        alert.setHeaderText(null);
	        alert.setContentText("Successfully Canceled!");
	        alert.showAndWait();
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getRsid() {
		return rsid;
	}

	public void setRsid(int rsid) {
		this.rsid = rsid;
	}
	
	
	
}
