//Progress bar
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.border.Border;
public class progress extends JFrame
{ 
JProgressBar progressBar;
JLabel icon;
	progress()
	{ setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Container content = getContentPane();
	icon=new JLabel(); 
	icon.setIcon(new ImageIcon("car.png"));
	add(icon);
	//---------------------------
	progressBar = new JProgressBar(0,80);
	progressBar.setValue(1);
	progressBar.setStringPainted(true);
	add(progressBar, BorderLayout.AFTER_LAST_LINE);
	progressBar.setBackground(Color.blue);
	progressBar.setForeground(Color.black);
	setUndecorated(true);
	setSize(796,436);
	setVisible(true); 
	setLocation(240,170);//screen location
	runn();
	}
	public void runn()
	{
	for(int i=1;i<=100;i++)
	{
	progressBar.setValue(i);
	try {
	Thread.sleep(30);
	} catch (Exception e) { }
	}
	setVisible(false);
	new logwin();
	//JOptionPane.showMessageDialog(null, "Completed");
	}
	/*public ImageIcon Resize(String ImagePath)
	{
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newlmg = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
	
		ImageIcon image = new ImageIcon(newlmg);
		return image;
	}*/
	
	public static void main(String args[])
	{
	new progress();
	}
}