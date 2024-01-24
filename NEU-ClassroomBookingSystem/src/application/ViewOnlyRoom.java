package application;

import java.sql.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ViewOnlyRoom extends Room{

	public ViewOnlyRoom(int roomId, int roomNum, int buildingNum, String buildingName, String bookableFlag) {
		super(roomId, roomNum, buildingNum, buildingName, bookableFlag);
	}

	@Override
	public void book(int userid, int roomid,  int rsid,Date date, int start, int end, String status) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Classroom is vacant but not available for bookings. You can sit in the room without reservation.");
        alert.showAndWait();
	}

}
