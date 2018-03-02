import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class About extends JFrame  
{

	Connection con;
	JLabel work,a,title,home,b,c,d,e,f,g;
	JLabel a1,b1,c1,d1,e1,f1,g1,k;
	JPanel pnl1,pnl3,pnl2;
	PreparedStatement pst;
	
	About()
	{
		setLayout(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		con=connect.doConnect();
		
		
		final Color blue=Color.decode("#2196F3");
		final Border brd=BorderFactory.createLineBorder(blue, 2, true);
		
		pnl1=new JPanel();
	
		pnl1.setBackground(blue);
		pnl1.setLayout(null);
		pnl1.setBounds(0,0,600,65);
		add(pnl1);	
		
		JLabel my=new JLabel("Developed & Designed By Abhishek Goyal");
		my.setBounds(75,75,400,20);
		add(my);
		
		pnl3=new JPanel();
		pnl3.setLayout(null);
		pnl3.setBackground(Color.WHITE);
		pnl3.setBorder(brd);
		pnl3.setBounds(260,100,300,200);
		add(pnl3);
		
		work=new JLabel("Under the Guidance of mentor, Rajesh Kumar Bansal");
		work.setFont(new Font("Arial",Font.BOLD,15));
		work.setBounds(100,305,560,40);
		add(work);
		
		pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBackground(Color.WHITE);
		pnl2.setBorder(brd);
		pnl2.setBounds(50,340,300,200);
		add(pnl2);
		
		k=new JLabel("For querry Contact me :");
		k.setBounds(50,548,150,13);
		add(k);
		
		
		JLabel k1=new JLabel("abhishekgoyal306@gmail.com");
		k1.setForeground(blue);
		k1.setBounds(200,548,200,14);
		add(k1);
		
		
		g=new JLabel();
		g.setBorder(brd);
		g.setBounds(360,340,200,198);
		g.setIcon(resize_img("sir.jpg",g));
		
		add(g);
		
		g1=new JLabel();
		g1.setBorder(brd);
		g1.setBounds(50,100,200,198);
		g1.setIcon(resize_img("mypic.jpg",g1));
		
		add(g1);
		
		title=new JLabel("About Us");
		title.setForeground(Color.WHITE);
		title.setBounds(170, 5, 300, 50);
		title.setFont(new Font("Times New Roman",Font.PLAIN,40));
		pnl1.add(title);
		
		JLabel icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl1.add(icon);
		
		home=new JLabel("Home");
		home.setBounds(550, 70, 20,20);
		home.setIcon(resize_img("home.png",home));
		add(home);
		
		/*home.addMouseListener(new MouseListener() {
			
			
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				home.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				home.setBorder(brd);
				
			}
			
			public void mouseClicked(MouseEvent e) {
				((About) home).main(null);
				dispose();
			}
		});*/
		
		a=new JLabel("Rajesh K. Bansal");
		a.setFont(new Font("Arial",Font.BOLD,18));
		a.setBounds(60, 10, 250, 30);
		pnl2.add(a);
		
		b=new JLabel("# CEO at Sun-Soft Technologies");
		b.setFont(new Font("Arial",Font.BOLD,12));
		b.setBounds(20, 40, 250, 30);
		pnl2.add(b);
	
		
		c=new JLabel("#  Founder of Banglore Comp. Edu.");
		c.setFont(new Font("Arial",Font.BOLD,12));
		c.setBounds(20, 70, 250, 30);
		pnl2.add(c);
		
		
		d=new JLabel("# Author of REAL JAVA"); 
	
		d.setBounds(20, 100, 250, 30);
		pnl2.add(d);
		
		
		e=new JLabel("# Sun Certified Java Programmer");
		e.setBounds(20, 130, 250, 30);
		pnl2.add(e);
		
		
		f=new JLabel("# Microsoft Certified Specialist");
		f.setBounds(20, 160, 250, 30);
		pnl2.add(f);
		
		
		a1=new JLabel("Abhishek Goyal");
		a1.setFont(new Font("Arial",Font.BOLD,18));
		a1.setBounds(20, 10, 250, 30);
		pnl3.add(a1);
		
		b1=new JLabel("# Studies At Thapar University");
		b1.setFont(new Font("Arial",Font.BOLD,12));
		b1.setBounds(20, 40, 250, 30);
		pnl3.add(b1);
	
		
		c1=new JLabel("# Pursuing B.E. COE");
		c1.setFont(new Font("Arial",Font.BOLD,12));
		c1.setBounds(20, 70, 250, 30);
		pnl3.add(c1);
		
		
		d1=new JLabel("# C,C++,Java Programmer"); 
	
		d1.setBounds(20, 100, 250, 30);
		pnl3.add(d1);
		
		
		e1=new JLabel("# Project Automated Parking System");
		e1.setBounds(20, 130, 250, 30);
		pnl3.add(e1);	
		
		
		
		setResizable(false);
		setLocation(400,20);
		setVisible(true);
		setSize(600,600);
	}
	
	
	
	
	
	  public ImageIcon resize_img (String p,JLabel j)
		{
			 ImageIcon full=new ImageIcon(p);
			Image img=full.getImage().getScaledInstance(j.getWidth(),j.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon bhai=new ImageIcon(img);
			return bhai;
		
		}
	
	public static void main(String[] args)
	{
		new About();

	}

}
