package Monopoly_test;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class image extends Canvas 
{
	
	private static final long serialVersionUID=1L;
	@Override
	public void paint(Graphics img)
	{
		try {
			Image board = ImageIO.read(new File("./Icons/Kanto_Board.jpg"));
			img.drawImage(board, 0, 0, getWidth(), getHeight(), this);
		} catch (IOException ex) {
			System.out.println("Could not find the image file " + ex.toString());
		}
		
		return;
	}
}
