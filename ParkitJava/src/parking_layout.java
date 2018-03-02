import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mysql.jdbc.Connection;

class layout extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lfloor,lcapacity,ltwo,lfour;
	JTextField floor,capacity;
	JRadioButton two,four;
	JButton save,close;
	JPanel pnl,pnl3;
	PreparedStatement pst;
	Border brd;
	Connection con;
	int radio,res;
	layout()
	{
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=connect.doConnect();
		if(con!=null)
		{
			//JOptionPane.showMessageDialog(null, "Connected");
			
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "Failed");
		}
		Color blue=Color.decode("#2196F3");
		brd=BorderFactory.createLineBorder(blue,1);
		
		setLayout(null);
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(0,0, 700,800);
		pnl.setBackground(Color.white);
		pnl.setBorder(BorderFactory.createTitledBorder("Register"));
		add(pnl);
		//////////////////////////////////////////////////
		pnl3=new JPanel();
		pnl3.setBackground(blue);
		//pnl3.setBorder(BorderFactory.createTitledBorder("Register"));
		pnl3.setBounds(0, 0, 700, 100);
		pnl.add(pnl3);
		JLabel ab=new JLabel("PARKING LAYOUT");
		ab.setBounds(200, 0, 100, 100);
		ab.setFont(new Font("Times New Roman",Font.PLAIN,40));
		ab.setForeground(Color.WHITE);
		pnl3.add(ab);
		
		floor=new JTextField();
		floor.setBounds(180,130,200,30);
		floor.setBorder(brd);
		pnl.add(floor);
		lfloor=new JLabel("FLOOR:");
		lfloor.setBounds(60,130,200,30);
		pnl.add(lfloor);
		
		//////////////////////////////////////////////////
				
		capacity=new JTextField();
		capacity.setBounds(180,200,200,30);
		capacity.setBorder(brd);
		pnl.add(capacity);
		lcapacity=new JLabel("CAPACITY:");
		lcapacity.setBounds(60,200,200,30);
		pnl.add(lcapacity);
		
		JLabel type=new JLabel("TYPE: ");
		type.setBounds(60, 270, 200, 30);
		pnl.add(type);
		
		
		two=new JRadioButton("2 Wheeler");
		four=new JRadioButton("4 Wheeler");
		ButtonGroup grp=new ButtonGroup();
		grp.add(two);
		grp.add(four);
		two.setBackground(blue);
		four.setBackground(blue);
		two.setBounds(180, 270,100,30);
		four.setBounds(280, 270,100,30);
		pnl.add(two);
		pnl.add(four);
		save=new JButton("SAVE");	
		save.setBounds(180,340,80,30);
		save.setBorder(brd);
		//save.setBackground();
		pnl.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				save();
				//JOptionPane.showMessageDialog(null, message);
			}
		});
		close=new JButton("CLOSE");	
		close.setBounds(280,340,80,30);
		pnl.add(close);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		setVisible(true);
		setSize(670, 670);
		setLocation(400,20);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	void save()
	{
		
		if(two.isSelected())
		{
			radio=2;
		}
		else
		{
			radio=4;
		}
		int c=Integer.parseInt(capacity.getText());
		try 
		{	
			pst=con.prepareStatement("delete from layout where Floor=? && wheeler=?");
			pst.setString(1, floor.getText());
			pst.setLong(2, radio);
			res=pst.executeUpdate();
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			for(int i=1;i<=c;i++)
			{
				pst=con.prepareStatement("insert into layout values(?,?,?,?)");
				pst.setString(1, floor.getText());
				pst.setInt(2, i);
				pst.setString(3, String.valueOf(radio));
				pst.setInt(4, 1);
				res=pst.executeUpdate();
			}
			
			
			JOptionPane.showMessageDialog(null, c+" Slots added at floor no." +floor.getText());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}



public class parking_layout {

	public static void main(String[] args) {
		new layout();

	}

}
