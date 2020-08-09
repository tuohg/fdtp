package ca.sheridancollege.project.view;
import java.io.InputStream;
import java.util.Scanner;

/**
 * This class read user/player input from the keyboard
 *  the input is divided into set of fields, which is return as array of strings
 * 
 * @author Hanan Fadel July 2020
 */ 
public class Input {

	 private Scanner input = null;
	 
	 public Input(InputStream in){
	 	input = new Scanner(in);
	 }
	 	
	 public String[] readFields(){
	 	return this.input.nextLine().trim().split("\\s+");
	 }
	 	
	 public void close(){
	 	if(null != input)
	 		this.input.close();
	 }
}
