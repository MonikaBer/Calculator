package application;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


/**
 * Class 'Alarm' enables creating objects of the Alert class.  
 */
public class Alarm {
	/**
	 * Field 'alert' stores an object of the Alert class.
	 */
	private Alert alert;
	
	/**
	 * Constructor for Alarm class.    
	 * @param type Type of the alert (type String).
	 * @param title Title of the alert window (type String).
	 * @param headerText Header of the alert window (type String).
	 * @param contentText Content of the alert window (type String).
	 */
	public Alarm (String type, String title, String headerText, String contentText) {
		switch (type) {
			case "warning":
				alert = new Alert(AlertType.WARNING);
				alert.setTitle(title);
				alert.setHeaderText(headerText);
				alert.setContentText(contentText);
				break;
			case "info":
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(title);
				alert.setHeaderText(headerText);
				alert.setContentText(contentText);
				break;
			default:
				break;
		}	
	}
	
	
	/**
	 * Method 'launchAlarm' enables show window with some information.
	 */
	public void launchAlarm() {
		Optional<ButtonType> result = alert.showAndWait();
		result.ifPresent( res -> System.out.println(res.getText()));
	}
}

	