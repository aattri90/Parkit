import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.lang.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class logwin extends JFrame
{
	JLabel lname,lpwd,pic,login;
	JButton blogin,bchange,lforgot;
	JTextField tname;
	String id,psd;
	JPasswordField tpassword;
	Border brd;
	JPanel pnl,pnl1;
	boolean a,d;
	Connection con;
	PreparedStatement pst;
	private int res;
	Color blue=Color.decode("#01aae8");
	
	logwin()
	{
		con=connect.doConnect();
		if(con!=null)
		{
			JOptionPane.showMessageDialog(null, "Connected");
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Failed");
		}

//===============================================		

		brd=BorderFactory.createLineBorder(Color.BLACK, 1);
		setLayout(null);
		pnl=new JPanel();
		pnl.setBounds(0, 0, 400, 628);
		//pnl.setBackground(blue);
		//pnl.setOpaque(true);
		pnl.setLayout(null);
		add(pnl);
		Color blue=Color.decode("#2196F3");
		pnl.setBackground(Color.white);
		pnl1=new JPanel();
		pnl1.setBounds(0,0,400,100);
		pnl1.setBackground(blue);
		pnl.add(pnl1);
		login=new JLabel("LOGIN");
		login.setBounds(30, 30, 100, 50);
		login.setFont(new Font("Times New Roman",Font.PLAIN,30));
		login.setForeground(Color.WHITE);
		pnl1.add(login);
		
		
		
		/*pic=new JLabel();
		pic.setBounds(80,50,140,180);
		pic.setBorder(brd);
		pnl.add(pic);*/
		
		lname=new JLabel("LoginId");
		lname.setBounds(90,140,70,20);
		lname.setForeground(blue);
		lname.setFont(new Font("Arial",Font.PLAIN,15));
		pnl.add(lname);
		
		tname=new JTextField();
		tname.setBounds(90,170,200,20);
		tname.setBorder(brd);
		tname.setFont(new Font("Arial",Font.PLAIN,10));
		pnl.add(tname);
		
		tname.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				try 
				{
					pst=con.prepareStatement("select * from login ");
					ResultSet s=pst.executeQuery();
					while(s.next())
					{
						id=s.getString(1);
						psd=s.getString(2);
					}
					if((tname.getText()).equals(id))
					{
						d=true;
					}
					else
					{
						d=false;
					}
				
				} 
				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
				
		lpwd=new JLabel("Password");
		lpwd.setBounds(90,195,70,20);
		lpwd.setForeground(blue);
		lpwd.setFont(new Font("Arial",Font.PLAIN,15));
		pnl.add(lpwd);
		
		tpassword=new JPasswordField();
		tpassword.setBounds(90,220,200,20);
		tpassword.setBorder(brd);
		tpassword.setFont(new Font("Arial",Font.PLAIN,12));
		pnl.add(tpassword);
		
		tpassword.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if((tpassword.getText()).equals(psd))
				{
					a=true;
				}
				else
				{
					a=false;
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		final JButton frgt=new JButton("Forgot Password ?");
		frgt.setBounds(100,250,150,15);
		frgt.setBackground(Color.white);
		frgt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double x = Math.random()*1000000;
				int m=(int) x;
				SST_SMS1.bceSunSoftSend("9463422661",String.valueOf(m));
				JOptionPane.showMessageDialog(null, "New 6 digit password has been sent to registered mobile number");
				try {
					pst=con.prepareStatement("Update login set pwd=?");
					pst.setDouble(1, m);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		frgt.setFont(new Font("Arial",Font.PLAIN,10));
		frgt.setForeground(blue);
		pnl.add(frgt);
		
		blogin=new JButton("Login");
		blogin.setBackground(blue);
		blogin.setForeground(Color.WHITE);
		blogin.setBounds(95, 280, 200, 20);
		pnl.add(blogin);
		
		blogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(d==false)
				{	JOptionPane.showMessageDialog(null, "Check your LoginId");
					tname.requestFocus();
				}
				else if(a==false)
				{
					JOptionPane.showMessageDialog(null, "Incorrect Password");
					tpassword.requestFocus();
				}
				else
				{
					new menubar();
					dispose();
				}
				
			}
		});
		
		/*bchange=new JButton("Change Password");
		bchange.setBackground(blue);
		bchange.setForeground(Color.WHITE);
		bchange.setBounds(95, 330, 200, 20);
		pnl.add(bchange);
		
		bchange.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				
			}
		});*/

//======================================================
		
		
//======================================================	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setTitle("LOGIN");
		setUndecorated(true);
getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setLocation(400,50);
		setSize(400,500);
		setBackground(Color.WHITE);
		setVisible(true);
		setResizable(true);
	}
}
public class logincode
{
	public static void main(String[] args)
	{
		new logwin();
	}
}
