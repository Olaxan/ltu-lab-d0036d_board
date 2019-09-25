package server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BoardPacket implements Serializable
{

	int x;
	int y;
	int rgb;
	
	public BoardPacket(int x, int y, int rgb)
	{
		this.x = x;
		this.y = y;
		this.rgb = rgb;
	}
	
	public byte[] serializeBoardPacket() throws IOException
	{
		byte[] bytes;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try
		{
			out = new ObjectOutputStream(bos);
			out.writeObject(this);
			out.flush();
			bytes = bos.toByteArray();
		}
		finally
		{
			try
			{
				bos.close();
			}
			catch (IOException ex) { }
		}
		return bytes;
	}
	
	public static BoardPacket deserializeBoardPacket(byte[] bytes) throws IOException, ClassNotFoundException
	{
		BoardPacket obj;
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		try
		{
			in = new ObjectInputStream(bis);
			obj = (BoardPacket) in.readObject();
		}
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex) { }
		}
		
		return obj;
	}
}
