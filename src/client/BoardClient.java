package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

import server.BoardPacket;

public class BoardClient
{	
	
	static Random rand = new Random();
	
	public static void main(String[] args) throws IOException
	{
		
		System.out.println("Board client - enter command:");
		System.out.println("x, y, rgb (exit to exit)\n");
		
		DatagramSocket socket = new DatagramSocket();
		Scanner scan = new Scanner(System.in);
	    InetAddress address = InetAddress.getByName("localhost");
		
		while (true)
		{
			String cmd = scan.nextLine();
			
			if (cmd.equals("exit"))
				break;
			
			if (cmd.equals("random"))
			{
				for (int i = 0; i < 250; i++)
				{
					BoardPacket pack = new BoardPacket(rand.nextInt(200), rand.nextInt(200), rand.nextInt(Integer.MAX_VALUE));
					byte[] bytes = pack.serializeBoardPacket();
					DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 4445);
			        socket.send(packet);
				}
				
				continue;
			}
		
			String[] cmds = cmd.split("[,\\s]+");
			
			if (cmds.length == 3)
			{
				int x, y, rgb;
				
				try
				{
					x 	= Integer.parseInt(cmds[0]);
					y 	= Integer.parseInt(cmds[1]);
					rgb = Integer.parseInt(cmds[2]);					
				}
				catch (NumberFormatException e)
				{
					System.out.println("Invalid command");
					continue;
				}
				
				BoardPacket pack = new BoardPacket(x, y, rgb);
				byte[] bytes = pack.serializeBoardPacket();
				DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 4445);
		        socket.send(packet);
				
			}
			else
				System.out.println("Too few values in command - enter x, y, rgb");
		}
		
		socket.close();
		scan.close();
	}
}
