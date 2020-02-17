package Lab4;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Amanuel Taddesse
 * @version 1.0
 */
public class L4View extends JFrame
{
	//Creates an instance of the L3Model class since we will be using methods from that class.
	private static final long serialVersionUID = 1L;
	
	//Creates and initializes JButtons.
	protected JButton encrypt = new JButton("Encrypt");
	protected JButton decrypt = new JButton("Decrypt");
	protected JButton save = new JButton("Save As");
	protected JButton reset = new JButton("Reset");
	
	//Creates and initializes JLabels.
	protected JLabel statusLabel = new JLabel("Status: ");
	protected JLabel statusDetail = new JLabel("Idle");
	
	//Creates and initializes a JPanel; this will house all JButtons and JLabels.
	//JPanel is made as a GridLayout of 3 by 2.
	protected JPanel grid = new JPanel(new GridLayout(3, 2));
	
	//Creating fonts.
	protected Font buttonFont= new Font("Calibri", Font.PLAIN, 18);
	protected Font statusFont = new Font("Calibri", Font.PLAIN, 18);
	
	/**
	 * Default constructor for L4View that takes no parameters.
	 */
	public L4View()
	{
		//JFrame to which elements can be added.
		JFrame frame = new JFrame();
		
		//the title, size and operation of the window.
		frame.setTitle("Encrypt or Decrypt a File");
		frame.setSize(250, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//fonts for JButtons,
		
		encrypt.setFont(buttonFont);
		
		decrypt.setFont(buttonFont);
		
		save.setFont(buttonFont);

		reset.setFont(buttonFont);
		
		//and for JLabels.
		
		statusLabel.setFont(statusFont);
		
		statusDetail.setFont(statusFont);
		
		//elements of the JPanel.
		
		grid.add(encrypt);
		
		grid.add(decrypt);
		
		grid.add(reset);
		
		grid.add(save);
		
		grid.add(statusLabel);
		
		grid.add(statusDetail);
		
		//Alignment for user readability.
		encrypt.setHorizontalAlignment(SwingConstants.CENTER);
		decrypt.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//JPanel of the JFrame.
		frame.add(grid);
		
		//Frame to visible.
		frame.setVisible(true);
	}
}
