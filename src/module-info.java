/**
 * 
 */
/**
 * @author Monika Berlińska
 *
 */
module calculator {
	exports application;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires jdk.jshell;
	
	opens application to javafx.fxml;
}