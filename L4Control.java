package Lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * .
 * @author Amanuel Taddesse
 * @version 1.0
 */
public class L4Control implements ActionListener
{
	
	L4Model m = new L4Model();
	
	/**
	 * Default constructor of L4Control that takes no parameters.
	 */
	public L4Control()
	{
		//Action listeners
		m.encrypt.addActionListener(this);
		m.decrypt.addActionListener(this);
		m.save.addActionListener(this);
		m.reset.addActionListener(this);
	}
	
	/**
	 * Mutator method that performs the action initiated by the user.
	 * @param action Action is the button being pressed.
	 */
	public void actionPerformed(ActionEvent action)
	{
		
		Object event = action.getSource();
		
		//If-else block that triggers the appropriate method.
		if(event == m.encrypt) {
			//Encrypt.
			m.encrpyt();
			
		} else if(event == m.decrypt) {
			//Decrypt.
			m.decrypt();
			
		} else if(event == m.save) {
			//Save As.
			m.save();
			
		} else if(event == m.reset) {
			//Reset.
			m.reset();
		}
	}
	
	/**
	 * Main method of the program.
	 * @param args string argument
	 */
	public static void main(String[] args)
	{
		
		//Begins.
		new L4Control();
	
	}
}
