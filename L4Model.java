package Lab4;

import java.awt.FileDialog;
import java.io.*;
import java.util.*;

/**
 * 
 * @author Amanuel Taddesse
 */
public class L4Model extends L4View
{
	
	private static final long serialVersionUID = 1L;
	//Declaring an ecryption key as an array.
	
	private final int[] key = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
	
	//Initializes and declares options.
	private final int encryptOption = 0;
	private final int decryptOption = 1;
	private final int writeOption = 2;
	
	//Initializes class-wide Streams.
	private InputStream in = null;
	private OutputStream out = null;
	
	//Initializes class-wide Strings for file paths.
	private String readPath = "";
	private String writePath = "";
	
	//Creates a Vector of type Byte.
	//This Vector contains all bytes of a file to be encrypted/decrypted before it is written to the hard disk.
	private Vector<Byte> data = new Vector<Byte>();
	
	//Initializes and declares an IllegalArgumentException that gets thrown when the user choses no file.
	IllegalArgumentException badFile = new IllegalArgumentException("Invalid file.");
	
	/**
	 * Mutator method that encrypts a file, but does not write.
	 */
	public void encrpyt()
	{
		//Since the whole method is dealing with FileIO, a try/catch surrounds all code.
		try {
			
			//Editing the status so the user knows what the program is doing.
			statusDetail.setText("Selecting file...");
			
			//Getting the file path from the FileDialog.
			readPath = getFilePath(encryptOption);
			
			//The program then reads the file as a FileInputStream.
			statusDetail.setText("Encoding file...");
			in = new FileInputStream(new File(readPath));
			
			//And processes each byte for encryption.
			process(encryptOption);
			
			//The encryption is done; awaiting the user to select save directory.
			statusDetail.setText("Encoded! Select save directory.");
			
			//Closing the FileInputStream.
			in.close();
		} catch(Exception e) {
			//Printing to the console any errors that occur.
			e.printStackTrace();
		}
	}
	
	/**
	 * Mutator method that decrypts a file, but does not write.
	 */
	public void decrypt()
	{
		//Since the whole method is dealing with FileIO, a try/catch surrounds all code.
		try {
			//Editing the status so the user knows what the program is doing.
			statusDetail.setText("Selecting file...");
			
			//Getting the file path from the FileDialog.
			readPath = getFilePath(decryptOption);
			
			//The program then reads the file as a FileInputStream.
			statusDetail.setText("Decoding file...");
			in = new FileInputStream(new File(readPath));
			
			//And processes each byte for decryption.
			process(decryptOption);
			
			//The encryption is done; awaiting the user to select save directory.
			statusDetail.setText("Decoded! Select save directory.");
			
			//Closing the FileInputStream.
			in.close();
		} catch(Exception e) {
			//Printing to the console any errors that occur.
			e.printStackTrace();
		}
	}
	
	/**
	 * Mutator method that saves a file, but neither encrypts nor decrypts.
	 */
	public void save()
	{
		//Since the whole method is dealing with FileIO, a try/catch surrounds all code.
		try {
			//Changing the status label to indicate that the program is writing the new file to the hard disk.
			statusDetail.setText("Selecting save directory...");
			
			//Declaring the FileDialog to write a file.
			writePath = getFilePath(writeOption);
			
			out = new FileOutputStream(new File(writePath));
			
			statusDetail.setText("Saving...");
			
			//Saving the the file.
			//For each byte in data,
			for(byte b : data)
				//Write the byte.
				out.write(b);
			
			//Close the FileOutputStream.
			out.close();
			
			//Returning the program to its default state.
			statusDetail.setText("Idle");
			reset();
		} catch(Exception e) {
			//Printing to the console any errors that occur.
			e.printStackTrace();
		}
	}
	
	private void process(int option)
	{
		//Since the whole method is dealing with FileIO, a try/catch surrounds all code.
		try {
			//Any data left over gets cleared.
			data.clear();
			
			//Declares and initializes a byte for later use.
			byte a = 0;
			byte b = 0;
			
			//For loop that iterates through each element in the Array key.
			for(int i = 0; i < key.length; i++)
			{
				//Gets the next byte of the file.
				int next = in.read();
				
				//If there are no more bytes left, end the process.
				if(next == -1)
					break;
				else
					//Otherwise, cast next to a byte.
					a = (byte)next;
				
				//Depending on the parameter...
				if(option == encryptOption)
					
					//Either add the encryption key, encrypt the byte.
					b = (byte)(a + key[i]);
				else if(option == decryptOption)
					
					//Of subtract the encryption key, decrypt the byte.
					b = (byte)(a - key[i]);
				
				//Add the modified byte to the Vector.
				data.add(b);
				
				//To avoid OutOfBoundsException, check if the loop has iterated through all elements.
				if(i == key.length - 1)
					
					//And if it has, set the counter to 0, to begin at the beginning of the Array key.
					i = 0;
			}
		} catch(Exception e) {
			//If an error occurs, print it to the console.
			e.printStackTrace();
		}
	}
	
	private String getFilePath(int option)
	{
		//Initializing a FileDialog.
		FileDialog fd = null;
		
		//Depending on the option, either declare fd to open a file to either be encrypted or decrypted,
		if(option == encryptOption)
			fd = new FileDialog(this, "Select a File to Encrypt...", FileDialog.LOAD);
		
		if(option == decryptOption)
			fd = new FileDialog(this, "Select a File to Decrypt...", FileDialog.LOAD);
		
		//Or to save a file that has already been either encrypted or decrypted.
		if(option == writeOption)
			fd = new FileDialog(this, "Select a Save Location...", FileDialog.SAVE);
		
		//Setting the FileDialog to be visible.
		fd.setVisible(true);
		
		//Getting the director and the file name of the file.
		String dir = fd.getDirectory();
		String fileName = fd.getFile();
		
		String file = dir + fileName;
		
		//Tests the file to see if "null" is contained.
		if(file.indexOf("null") != -1)
		{
			//If "null" is part of the String file, then the user must start over.
			reset();
			statusDetail.setText("Bad file! Redo.");
			throw badFile;
		}
		
		//Returning the file's complete address.
		return file;
	}
	
	/**
	 * Setter method that resets all fields, both front and back end.
	 */
	public void reset()
	{
		//Resetting the status label.
		statusDetail.setText("Idle");
		
		//Returning the Streams to their default initialization.
		in = null;
		out = null;
		
		//Clearing the Strings for file paths.
		readPath = "";
		writePath = "";
		
		//Clear any data that is in the Vector.
		data.clear();
	}
}