package application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import javafx.scene.control.Button;


/**
* Class 'Sample Controller' which listens for events and react to them.
* 
*/
public class SampleController {
	/**
	* Field 'output' is associated with the lower part of the calculator's display.
	*/
	@FXML
	private Text output;
	
	/**
	* Field 'cache' is associated with the upper part of the calculator's display.
	* It stores current operation.
	*/
	@FXML
	private Text cache;
	
	/**
	* Field 'a' is the first argument of the operation.
	*/
	private double a = 0;
	
	/**
	* Field 'operator' is an arithmetic operator.
	*/
	private String operator = "";
	
	/**
	* Field 'snippet' is related to JShell.
	*/
	private String snippet;
	
	/**
	* Field 'jshell' related to JShell.
	*/
	JShell jshell = JShell.create();
	
	/**
	* Field 'start' says if it is the beginning of the operation
	*/
	private boolean start = true;
	
	/**
	* Field 'model' is an object of the class Model 
	*/
	private Model model = new Model();

	/**
	* Field 'temp' is a helper String to store a result of an arithmetic operation. 
	*/
	private String temp;
	
	/**
	 * Method 'processNum' 
	 * @param event Action on numButton.
	 */
	@FXML
	private void processNum(ActionEvent event) {
		
		if (start) {
			output.setText("");
			start = false;
		}
		
		String value = ((Button)event.getSource()).getText();
		output.setText(output.getText() + value);

		
		if (operator == "" && cache.getText() != "") {
			cache.setText(output.getText());
		} else {
			cache.setText(cache.getText() + output.getText());
		}
	}
	
	/**
	 * Method 'processClearOp' 
	 * @param event Action on clearButton.
	 */
	@FXML
	private void processClearOp(ActionEvent event) {
		a = Double.parseDouble("0");
		operator = "";
		output.setText("0");
		start = true;
		cache.setText("");
	}
	
	/**
	 * Method 'processEqualOp' 
	 * @param event Action on eqButton.
	 */
	@FXML
	private void processEqualOp(ActionEvent event) {
		if (operator.isEmpty())
			return;
		
		output.setText(String.valueOf(model.calculateBinaryOp(a, Double.parseDouble(output.getText()), operator)));
		
		if ( cache.getText() != "" ) {
			cache.setText(cache.getText() + " = " + output.getText());
			
			snippet = cache.getText();
			List<SnippetEvent> events = jshell.eval(snippet);
		}
			
		operator = "";
		start = true;
	}
	
	/**
	 * Method 'processUnaryOp' 
	 * @param event Action on opButton.
	 */
	@FXML
	private void processUnaryOp(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
		
		if (operator.isEmpty()) {
			operator = value;
			a = Double.parseDouble(output.getText());
			output.setText(String.valueOf(model.calculateUnaryOp(a, operator)));
			
			if ( cache.getText() != "" ) {
				cache.setText(cache.getText() + " " + operator + " = " + output.getText());
				
				snippet = cache.getText();
				List<SnippetEvent> events = jshell.eval(snippet);
			}
		} else {
			temp = String.valueOf(model.calculateBinaryOp(a, Double.parseDouble(output.getText()), operator)); 
			snippet = cache.getText() + " = " + temp;
			List<SnippetEvent> events = jshell.eval(snippet);
			
			operator = value;
			a = Double.parseDouble(temp);
			output.setText(String.valueOf(model.calculateUnaryOp(a, operator)));
			cache.setText(temp + " " + operator + " = " + output.getText());
			
			snippet = cache.getText();
			events = jshell.eval(snippet);
		}
	
		operator = "";
		start = true;
	}
	
	/**
	 * Method 'processBinaryOp' 
	 * @param event Action on opButton.
	 */
	@FXML
	private void processBinaryOp(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
				
		if (operator.isEmpty()) {
			operator = value;
			a = Double.parseDouble(output.getText());
			cache.setText(output.getText() + operator);
			output.setText("");	
		} else {
			output.setText(String.valueOf(model.calculateBinaryOp(a, Double.parseDouble(output.getText()), operator)));
			operator = value;
			a = Double.parseDouble(output.getText());
			cache.setText(cache.getText() + operator);
			start = true;
		}
	}

	/**
	 * Method 'processMemory' 
	 * @param event Action on memoryButton.
	 */
	@FXML
	private void processMemory(ActionEvent event) {

		StringBuilder allSnippets = new StringBuilder();
		jshell.snippets().forEach(s -> {
			String oneSnippet = String.format("%s\n", s.source());
			allSnippets.append(oneSnippet);
		});
		
		String text = allSnippets.toString();
		
		Alarm showMemory = new Alarm("info", "Memory of calculator", "List of operations:", text);
		showMemory.launchAlarm();
	}

}
