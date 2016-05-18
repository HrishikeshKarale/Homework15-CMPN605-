/**
* Client.java
*
* @version   $Id: Client.java,v_1.2 2014/12/07 17:20:00
*
* @author    hhk9433 (Hrishikesh Karale)
*
* Revisions:
*      Initial revision
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
* This Program acts as a client and requests for the scores found on a particular 
* given webpage to its server. and plays an audio file  provided by the server. 
*/
public class Client 
{
	
	/**
	 * This is the main method of our client class and this method takes in 
	 * the name of the team as input and requests the score found on the 
	 * webpage from the server. 
	 * 
	 * @param args
	 */
	public static void main(String args[]) throws UnknownHostException,
	IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		//socket with ocalhost and port no 14623 is opened
		Socket socket_object= new Socket("localhost", 14623);
		// a printStream object is initialized using the above socket instance variable.
		PrintStream print_stream_object= new PrintStream
				(socket_object.getOutputStream());
		Scanner scr= new Scanner(System.in);
		
		//user input is taken
		System.out.print("Enter Team Name: ");
		String message= scr.next();
		print_stream_object.println(message);
		
		//imputstream objet is initialized so as to recieve data from server. 
		InputStreamReader input_stream_reader_object= new InputStreamReader
				(socket_object.getInputStream());
		// a buffered reader object is initialized using the above inputstream
		BufferedReader buffered_reader_object= new BufferedReader(input_stream_reader_object);
		//a line s being read using buffered reader from server  and stored in input
		String input= buffered_reader_object.readLine();
		System.out.println("Server: "+input);
		
		//used to write data in file
		BufferedWriter buffered_writer_object= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("copy-im-sorry-i-cant.wav")));
		message=buffered_reader_object.readLine();
		
		// this blocck of code reads data and writes in specified file
		while(message!=null || message== "")
		{
			buffered_writer_object.write(message);
			try
			{
				message=buffered_reader_object.readLine();
			}
			catch(Exception e)
			{
				break;
			}
		}
		buffered_writer_object.close();
		
/*		
		byte[] b = new byte[270830];
		DatagramSocket ds = new DatagramSocket(1000);
		DatagramPacket dp = new DatagramPacket(b,b.length);
		FileOutputStream fos = new FileOutputStream(new File("copy-im-sorry-i-cant.wav"));
		ds.receive(dp);
		byte[] b1 = new byte[dp.getLength()];
		
		System.out.println((int)dp.getLength());
		
		fos.write(b,0,b1.length);
*/		

		System.out.println("Client: File Write Successful");
		
		//plays the audio clip.
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("copy-im-sorry-i-cant.wav")));
	        clip.start();
	        Thread.sleep(3000);
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		scr.close();
		
		socket_object.close();
	}
}
