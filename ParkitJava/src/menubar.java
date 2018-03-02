import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;

public class menubar  extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnl;
	JLabel reg,lay,entry,search,my,exit,about,reg1,lay1,entry1,search1,my1,exit1,about1; 
	Connection con;
	JLabel icon,title,l;
	
	menubar()
	{
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=connect.doConnect();
	
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setResizable(true);
		setLocation(400, 20);
		final Color blue=Color.decode("#2196F3");
		final Border brd=BorderFactory.createLineBorder(blue, 1, true);
		getContentPane().setBackground(Color.decode("#e3f2fd"));
		
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBackground(blue);
		pnl.setBounds(0,0,600,65);
		add(pnl);
		
		
		title=new JLabel(" Welcome to Vehicle Parking");
		title.setBounds(85, 20,450, 40);
		title.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,25));
		title.setForeground(Color.WHITE);
		pnl.add(title);
		
		
		l=new JLabel("logout");
		l.setBounds(540,48,80,15);
	
		l.setFont(new Font("Arial",Font.BOLD,12));
		l.setForeground(Color.WHITE);
		pnl.add(l);
		l.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				l.setForeground(Color.white);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				l.setForeground(Color.BLUE);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				logincode.main(null);
				dispose();
				
			}
		});
		
		
		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl.add(icon);
		
		
		
		reg=new JLabel();
		reg.setBounds(60, 80, 110, 95);
		reg.setIcon(resize_img("registration.png",reg));
		add(reg);
		
		reg.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				reg.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				reg.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				reg.setBackground(blue);
				cregister.main(null);
				
			}
		});
		
		lay=new JLabel();
		lay.setBounds(240, 80, 110, 95);
		lay.setIcon(resize_img("layout.jpg",lay));
		add(lay);
		
		lay.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lay.setBorder(null);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lay.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//reg1.setForeground(blue);
				parking_layout.main(null);
			}
		});
		
		
		entry=new JLabel();
		entry.setBounds(420, 80, 110, 95);
		entry.setIcon(resize_img("entry.png",entry));
		add(entry);
		
		entry.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				entry.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				entry.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			//	reg1.setForeground(blue);
				VehicleEntry.main(null);
			}
		});
		
		
		
		search=new JLabel();
		search.setBounds(60, 270, 110, 95);
		search.setIcon(resize_img("alltable.png",search));
		add(search);
		
		
		search.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				search.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				search.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//reg1.setForeground(blue);
				new fetchall();
				
			}
		});
		my=new JLabel();
		my.setBounds(240, 270, 110, 95);
		my.setIcon(resize_img("onesearch.png",my));
		add(my);
		
		my.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				my.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				my.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			//	my.setForeground(blue);
				
				new searching();
			}
		});
		
		
		
		
		exit=new JLabel();
		exit.setBounds(420, 270, 110, 95);
		exit.setIcon(resize_img("exit.png",exit));
		add(exit);
		
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				exit.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				exit.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//reg1.setForeground(blue);
																																																																																				
				exiting.main(null);
			}
		});
		
		
		about=new JLabel();
		about.setBounds(220, 420, 110, 95);
		about.setIcon(resize_img("about.png",about));
		add(about);
		
		about.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				about.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				about.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//reg1.setForeground(blue);
				About.main(null);
			}
		});
		
		
		reg1=new JLabel("Registration");
		reg1.setFont(new Font("Arial",Font.BOLD,15));
		reg1.setBounds(80, 185, 110, 20);
		
		add(reg1);
		
		reg1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				reg1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				reg1.setForeground(Color.BLUE);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				reg1.setForeground(blue);
				cregister.main(null);
				
			}
		});
		
		lay1=new JLabel("Layout");
		lay1.setFont(new Font("Arial",Font.BOLD,15));
		lay1.setBounds(265, 185, 110, 20);
		add(lay1);
		lay1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lay1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lay1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lay1.setForeground(blue);
				parking_layout.main(null);
			}
		});
		
		
		
		entry1=new JLabel("Entry");
		//entry1.setForeground(blue);
		entry1.setFont(new Font("Arial",Font.BOLD,15));
		entry1.setBounds(455, 185, 110, 20);
		
		add(entry1);
		
		
		entry1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				entry1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				entry1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				entry1.setForeground(blue);
				VehicleEntry.main(null);
			}
		});
		
		search1=new JLabel("Show All");
		search1.setBounds(70, 370, 110, 20);
		search1.setFont(new Font("Arial",Font.BOLD,15));
		
		add(search1);
		
		
		search1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				search1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				search1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setForeground(blue);
				fetchall.main(null);
				
			}
		});
	
		my1=new JLabel("Search Vehicle");
		my1.setBounds(242, 365, 110, 20);
		my1.setFont(new Font("Arial",Font.BOLD,15));
		
		add(my1);
		
		
		my1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				my1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				my1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				my1.setForeground(blue);
				new searching();
			}
		});
	
	
		exit1=new JLabel("Exit");
		exit1.setBounds(450, 365, 110, 20);
		exit1.setFont(new Font("Arial",Font.BOLD,15));
		add(exit1);
		
		
		exit1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit1.setForeground(Color.BLACK);
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				exit1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				exit1.setForeground(blue);
				exiting.main(null);
			}
		});
	
	
	
	
		about1=new JLabel("About Us");
		about1.setBounds(240, 520, 110, 20);
		about1.setFont(new Font("Arial",Font.BOLD,15));
		add(about1);
		
		about1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				about1.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				about1.setForeground(Color.BLUE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				about1.setForeground(blue);
				About.main(null);
			}
		});
		
		
		
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
		new menubar();

	}

}
