package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class BoardView extends JFrame
{

	private BoardComponent contentPane;
	private BoardListener server;
	private Thread serverThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					BoardView frame = new BoardView();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardView()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new BoardComponent(201, 201);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		try
		{
			server = new BoardListener(4445);
			server.addOnReceivedListener((packet) -> { contentPane.SetPixel(packet.x, packet.y, packet.rgb); });
			
			serverThread = new Thread(server);
			serverThread.start();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}