import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mysql.jdbc.Connection;


class ventry extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField licno,vehno;
	JRadioButton two,four;
	JComboBox slots,floor;
	PreparedStatement pst;
	JLabel llicno,lvehno,lvtype,lavailabity,favail;
	Border brd;
	JPanel pnl,pnl1;
	JButton save,close,avail,avail1;
	Connection con;
	String adc,a,r;
	int radio,floors,number;
	ventry()
	{
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

		con=connect.doConnect();
		brd=BorderFactory.createLineBorder(Color.BLACK, 3);
		Color blue=Color.decode("#2196F3");
		Border brd=BorderFactory.createLineBorder(blue, 1, true);
		
		pnl1=new JPanel();
		pnl1.setBackground(blue);
		pnl1.setBounds(0,0,700,100);
		add(pnl1);
		
		
		setLayout(null);
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(0,0, 700,800);
		pnl.setBackground(Color.white);
		pnl.setBorder(BorderFactory.createTitledBorder("Vehicle Entry"));
		add(pnl);
		JLabel ab=new JLabel("VEHICLE ENTRY");
		ab.setBounds(0, 0, 100, 100);
		ab.setFont(new Font("Times New Roman",Font.PLAIN,40));
		ab.setForeground(Color.WHITE);
		pnl1.add(ab);
		licno=new JTextField();
		licno.setBounds(180,130,200,30);
		licno.setBorder(brd);
		pnl.add(licno);
		llicno=new JLabel("License No :");
		llicno.setBounds(60,130,200,30);
		pnl.add(llicno);
		
		lvtype=new JLabel("Vehicle Type:");
		lvtype.setBounds(60,270,200,30);
		pnl.add(lvtype);
		
		favail=new JLabel("Floor");
		favail.setBounds(60,340,200,30);
		pnl.add(favail);
		
		lavailabity=new JLabel("Availability");
		lavailabity.setBounds(60,410,200,30);
		pnl.add(lavailabity);
		
		//////////////////////////////////////////////////
				
		vehno=new JTextField();
		vehno.setBounds(180,200,200,30);
		vehno.setBorder(brd);
		pnl.add(vehno);
		lvehno=new JLabel("Vehicle No :");
		lvehno.setBounds(60,200,200,30);
		pnl.add(lvehno);
		//////////////////////
		slots=new JComboBox();
		slots.setBounds(180, 410, 60, 30);
		pnl.add(slots);
		
		floor=new JComboBox();
		floor.setBounds(180, 340, 60, 30);
		pnl.add(floor);
		
		//////////////////////////////
		avail=new JButton("Check");
		avail.setBounds(300, 340, 80, 30);
		pnl.add(avail);
		avail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				selectfloor();
			}
		});
		////////////////////////////////
		avail1=new JButton("Check");
		avail1.setBounds(300, 410, 80, 30);
		pnl.add(avail1);
		avail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				avail1();
			}
		});
		
		two=new JRadioButton("2 Wheeler");
		four=new JRadioButton("4 Wheeler");
		ButtonGroup grp=new ButtonGroup();
		grp.add(two);
		grp.add(four);
		
		//Dimension tenn=two.getPreferredSize();
		two.setBounds(180, 270,100,30);
		four.setBounds(280, 270,100,30);
		pnl.add(two);
		pnl.add(four);
		
		save=new JButton("SAVE");	
		save.setBounds(180,460,80,30);
		pnl.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
				
			}
		});
		
		close=new JButton("CLOSE");	
		close.setBounds(280,460,80,30);
		pnl.add(close);
		close.addActionListener(new ActionListener() {
			
			@Override
		public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		setVisible(true);
		setSize(700, 600);
		setLocation(400,20);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	void avail1()
	{
		slots.removeAllItems();
		slots.addItem("select");
		floors=(int) floor.getSelectedItem();
		JOptionPane.showMessageDialog(null, floors);
		try
		{
			pst=con.prepareStatement("select slot from layout where status=1 and floor=?");
			//pst.setInt(1, radio);
			pst.setInt(1, floors);
	      	ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String slot=rs.getString("slot");
				slots.addItem(slot);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	void save()
	{
		int slot=Integer.parseInt(slots.getSelectedItem().toString());
		//JOptionPane.showMessageDialog(null, slot);
		try 
		{
			
			pst=con.prepareStatement("update layout set status=0 where wheeler=? and slot=? and floor=?");
			pst.setInt(1, radio);
			pst.setInt(2, slot);
			pst.setInt(3, floors);
			pst.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			pst=con.prepareStatement("insert into ventry(license,vehno,wheeler,slot,doe,floor,vstatus,id) values(?,?,?,?,now(),?,0,1)");
			pst.setString(1, licno.getText());
			pst.setString(2, vehno.getText());
			pst.setInt(3, radio);
			pst.setInt(4, slot);
			pst.setInt(5, floors);
			int res = pst.executeUpdate();
			JOptionPane.showMessageDialog(null, res+"Record Saved");
			pst=con.prepareStatement("Select contact,cname from cregister where custid=?");
			pst.setString(1, licno.getText());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				adc=(rs.getString("contact"));
				r=(rs.getString("cname"));
			}
			a="Hello "+String.valueOf(r)+" Allocated Floor is :"+String.valueOf(floors)+" Allocated Slot is : "+String.valueOf(slot);	
			System.out.print(adc);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		SST_SMS1.bceSunSoftSend(adc, a);
	}
	void selectfloor()
	{
		floor.removeAllItems();
		if(two.isSelected())
		{
			radio=2;
		}
		else
		{
			radio=4;
		}
		floor.addItem("select");
		try
		{
			pst=con.prepareStatement("select distinct floor from layout where status=1 and wheeler=?");
			pst.setInt(1, radio);
		//	pst.setInt(2, arg1);
	      	ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				int floorr=rs.getInt("floor");
				floor.addItem(floorr);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
public class VehicleEntry {

	public static void main(String[] args) {
		new ventry();

	}

}
