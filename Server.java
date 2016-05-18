/**
* Server.java
*
* @version   $Id: Server.java,v_1.2 2014/12/07 17:20:00
*
* @author    hhk9433 (Hrishikesh Karale)
*
* Revisions:
*      Initial revision
*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This Program acts as a server and returns audio clip to its client.
 */
public class Server 
{

	/**
	 * This is the main method of our server class and this method takes in 
	 * the name of the team as input and sends back an audio clip.
	 * 
	 * @param args
	 */
	public static void main(String args[]) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		//server socket initialized
		ServerSocket server_socket_object= new ServerSocket(14623);
		//client connection accepted
		Socket socket_object= server_socket_object.accept();
		//printwriter initialized to send data to client.
		PrintWriter print_writer_object= new PrintWriter (new OutputStreamWriter(socket_object.getOutputStream()));
		//imputSreamreader initializd to recieve msg from client
		InputStreamReader input_stream_reader_object= new InputStreamReader(socket_object.getInputStream());
		//holds input from server
		BufferedReader buffered_reader_object= new BufferedReader(input_stream_reader_object);
		
		String input= buffered_reader_object.readLine();
		System.out.println("Client: "+input);
		
		//checks if msg is recieved or not and sends msg back to client for confirmation.
		if(input!=null)
		{
			PrintStream print_stream_object= new PrintStream(socket_object.getOutputStream());
			print_stream_object.println(" Request cannot be processed due to Internet Failure. Please listen to this audio.");
		}
		
		// file is initialized to the given file
		File file_object= new File("im-sorry-i-cant.wav");
		//filereader is initialized
		FileReader file_reader_object= new FileReader(file_object);
		//buffered reader is initialized to store file being read
		buffered_reader_object= new BufferedReader(file_reader_object);
		input= null;
		
		//sends the file line by line to client using printwriter object.
		while((input=buffered_reader_object.readLine()) !=null || input== "")
		{
		//	System.out.println(input);
			print_writer_object.println(input);
		}
		System.out.println("Server: read Successful");
		
/*
		DataInputStream dis = new DataInputStream(System.in);
		File file = new File("im-sorry-i-cant.wav");
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[(int)file.length()]; 
		
		System.out.println((int)file.length());
		
		fis.read(b,0,b.length);
		DatagramSocket ds = new DatagramSocket(1000);
		DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getLocalHost(),64000);
		System.out.println("Server: read Successful");
		ds.send(dp);
*/
		System.out.println("done");
		
		buffered_reader_object.close();
		server_socket_object.close();
		
	}
}
