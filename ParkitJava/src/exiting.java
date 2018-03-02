import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class exiting extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection con;
	
	JLabel vno,flr,wheel,date,time,flr1,date1,time1,slot,slot1,rent,rent1,cdate1,cdate,ctime,ctime1;
	JLabel day,day1,hr,hr1,bill,bill1;
	JButton fetch,done,print,calc;
	JTextField txtv,wheel1;
    JPanel pnl,pnl1,pnl2;
    Container c=getContentPane();
    Date d;
    double billing;

	int wh,days,hrs,mins;
	int ab,da;
	String abc,adc;
	ResultSet res3;
    PreparedStatement pst1,pst2,pst3;
    
    public exiting()
    {
    	setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=connect.doConnect();
		if(con!=null)
		{
			//JOptionPane.showMessageDialog(null, "Connected to Server");
		}
		else
			JOptionPane.showMessageDialog(null, "Connected Failed");
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(400, 20);
		Color blue=Color.decode("#2196F3");
		Border brd=BorderFactory.createLineBorder(blue, 1, true);
		
		pnl=new JPanel();
		pnl.setBackground(blue);
		pnl.setBounds(0,0,600,65);
		add(pnl);
		JLabel ab=new JLabel("EXIT");
		ab.setBounds(0, 0, 100, 100);
		ab.setFont(new Font("Times New Roman",Font.PLAIN,30));
		ab.setForeground(Color.WHITE);
		pnl.add(ab);
		
		
		
		vno=new JLabel("Vehicle No.");
		vno.setBounds(150, 80,80,30);
		add(vno);
		
		txtv=new JTextField();
		txtv.setBounds(240, 80, 120, 30);
		add(txtv);
		
				
		pnl1=new JPanel();
		pnl1.setBounds(100, 130, 400, 200);
		pnl1.setLayout(null);
		pnl1.setBackground(Color.WHITE);
		add(pnl1);
		
		flr=new JLabel("Floor");
		flr.setBounds(30, 20, 50, 20);
		pnl1.add(flr);
		
		flr1=new JLabel();
		flr1.setBounds(100, 20, 80, 20);
		flr1.setForeground(blue);
		flr1.setBorder(brd);
		pnl1.add(flr1);
		
		slot=new JLabel("Slot");
		slot.setBounds(200, 20, 50, 20);
		pnl1.add(slot);
		
		slot1=new JLabel();
		slot1.setBounds(240, 20, 80, 20);
		slot1.setBorder(brd);
		slot1.setForeground(blue);
		pnl1.add(slot1);
		
		wheel=new JLabel("Wheeler");
		wheel.setBounds(30,55,60,20);
		pnl1.add(wheel);
		
		wheel1=new JTextField();
		wheel1.setBounds(150,55,100,20);
		wheel1.setForeground(blue);
		wheel1.setBorder(brd);
		pnl1.add(wheel1);
		
		date=new JLabel("Date Of Parking");
		date.setBounds(30,90,100,20);
		pnl1.add(date);
		
		date1=new JLabel();
		date1.setBounds(150,90,100,20);
		date1.setForeground(blue);
		date1.setBorder(brd);
		pnl1.add(date1);
		
		 JLabel date2=new JLabel("(yyyy-mm-dd)");
		date2.setBounds(250,90,100,20);
		pnl1.add(date2);
		
		
		time=new JLabel("Time Of Parking");
		time.setBounds(30,125,100,20);
		pnl1.add(time);
		
		time1=new JLabel();
		time1.setForeground(blue);
		time1.setBounds(150,125,100,20);
		time1.setBorder(brd);
		pnl1.add(time1);
		
		rent=new JLabel("Fare(per hr)");
		rent.setBounds(30,160,100,20);
		pnl1.add(rent);
		
		rent1=new JLabel("30");
		rent1.setBounds(150,160,100,20);
		rent1.setForeground(blue);
		rent1.setBorder(brd);
		pnl1.add(rent1);
		//rent1.
		
		
		pnl2=new JPanel();
		pnl2.setBounds(100, 340, 400, 80);
		pnl2.setLayout(null);
		pnl2.setBackground(Color.WHITE);
		add(pnl2);
		
		cdate=new JLabel("Current Date");
		cdate.setBounds(30, 10, 100, 20);
		pnl2.add(cdate);
		
		cdate1=new JLabel();
		cdate1.setForeground(blue);
		cdate1.setBounds(150, 10, 100, 20);
		cdate1.setBorder(brd);
		pnl2.add(cdate1);
		/*String ss=String.valueOf(new Date());
		cdate1.setText(ss);*/
		
		
		ctime=new JLabel("Current Time");
		ctime.setBounds(30,40,100,20);
		pnl2.add(ctime);
		
		ctime1=new JLabel();
		ctime1.setBounds(150,40,100,20);
		ctime1.setForeground(blue);
		ctime1.setBorder(brd);
		pnl2.add(ctime1);
		
		
		
		fetch=new JButton("Fetch");
		fetch.setBounds(370, 80, fetch.getPreferredSize().width, 30);
		fetch.setBackground(blue);
		fetch.setForeground(Color.white);
		add(fetch);
		fetch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fetch();
				
			}
		});
		calc=new JButton("Calculate Bill");
		calc.setBounds(100, 430, 400, 20);
		calc.setForeground(Color.WHITE);
		calc.setBackground(blue);
		add(calc);
		
		calc.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e) 
			{
				//long l=Difference(ddd,d);
				//calc(l);
				calculate();
			}
		});
		
		day=new JLabel("Days");
		day.setBounds(130,460,100,20);
		add(day);
		
		hr=new JLabel("Hours");
		hr.setBounds(130,485,100,20);
		add(hr);
		
		bill=new JLabel("Bill (in Rs)");
		bill.setFont(new Font("Times New Roman",Font.BOLD,20));
		bill.setBounds(130,510,100,20);
		add(bill);
		
		
		day1=new JLabel();
		day1.setBounds(250,460,100,20);
		day1.setBorder(brd);
		add(day1);
		
		hr1=new JLabel();
		hr1.setBorder(brd);
		hr1.setBounds(250,485,100,20);
		add(hr1);
		
		bill1=new JLabel();
		bill1.setFont(new Font("Times New Roman",Font.BOLD,20));
		bill1.setBorder(brd);
		bill1.setBounds(250,510,100,20);
		add(bill1);
		
		
		done=new JButton("Done");
		done.setBounds(180,540,80,20);
		done.setBackground(blue);
		done.setForeground(Color.WHITE);
		add(done);
		
		done.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					pst2=con.prepareStatement("select license from ventry where vehno=? AND vstatus=0");
					pst2.setString(1,txtv.getText());
					res3=pst2.executeQuery();
					while(res3.next())
					{
						abc=res3.getString("license");
					}
					
				} catch (SQLException e2) 
				{
					e2.printStackTrace();
				}
				
				//////////////////////////////////////////////
				
				try 
				{
					pst1=con.prepareStatement("update layout set status=1 where slot=(select slot from ventry where vehno=? and vstatus=0)");
					pst1.setString(1,txtv.getText());
					pst1.executeUpdate();
					
					pst2=con.prepareStatement("update ventry set vstatus=1 where vehno=?");
					pst2.setString(1, txtv.getText());
					pst2.executeUpdate();
					
					/*pst1=con.prepareStatement("select * from registration where )
					String msg=SST_SMS.bceSunSoftSend(mob, "Hello Dear");
					if(msg.equals("sent"))
					JOptionPane.showMessageDialog(null, msg);
					else
					JOptionPane.showMessageDialog(null,"com/internet con. problem");
*/
					//(select license from ventry where vstatus=0 and vehno=?)				
					JOptionPane.showMessageDialog(null, "Amount to be paid is "+billing+" and details has been sent to registered contact number");
					dispose();
				}
				catch (SQLException e1) 
				{
					
					e1.printStackTrace();
				}
				//////////////////////////////////////////////////
				try 
				{
					pst2=con.prepareStatement("select contact from cregister where custid=?");
					pst2.setString(1,abc);
					res3=pst2.executeQuery();
					//ab=res3.getString("contact");
					while(res3.next())
					{
					 	adc=(String.valueOf((res3.getString("contact"))));
					}
					
					System.out.println(adc);
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				String a="The parking charges are "+billing+" for "+hrs+" hours and "+mins+"mins";
				
				SST_SMS1.bceSunSoftSend(adc, a);
				dispose();
			
			}
		});
		
		
		
		print=new JButton("Print Receipt");
		print.setBounds(300,540,130,20);
		print.setBackground(blue);
		print.setForeground(Color.WHITE);
		add(print);
		//calculate();
		setVisible(true);
		setSize(600,600);
	
    }
    
   /* void sendSMS(String m,String msg)
    {
    String resp=smsDone.bceSunSoftSend("sunsoft123","sunsoft1234",m,msg,"SUNSFT");
    if(resp.indexOf("successfully")!=-1)
    {
    JOptionPane.showMessageDialog(null, "Sent");
    }
    else
    if(resp.indexOf("invalid")!=-1)
    {
    JOptionPane.showMessageDialog(null, "Invalid....");
    }
    else
    JOptionPane.showMessageDialog(null, "Check Internet Connection");
    }*/

    
    
    void calculate()
    {
    	int r=0,hours=0,minutes;
    	//int r1=0,ab1=0;
    	try
    	{
			pst2=con.prepareStatement("SELECT TIMESTAMPDIFF (MINUTE, doe,now()) as ab from ventry where vehno=?;");
			pst2.setString(1, txtv.getText());
			ResultSet res1 = pst2.executeQuery();
			while(res1.next())
			{
				r=res1.getInt("ab");
			}
			//System.out.println(r);
			days=r/1440;
			hours=r/60;
			minutes=r%60;
			hrs=hours;
			mins=minutes;
			if(wh==4)
			{	if(hours==0)
				billing=((hours)*30)+minutes*1;
				else
				billing=((hours)*30)+minutes*0.5;
			}
			else
			{
				if(hours==0)
					billing=((hours)*15)+minutes*0.5;
				else
					billing=((hours)*15)+minutes*0.25;
			}
                        email_try et=new email_try(billing+"");
			day1.setText(String.valueOf(days));
			hr1.setText(String.valueOf(hours));
			bill1.setText(String.valueOf(billing));
			pst3=con.prepareStatement("update ventry set bill=? where vehno=?");
			pst3.setInt(1,(int) billing);
			pst3.setString(2,txtv.getText());
			pst3.executeUpdate();
    	}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
   void fetch()
	{
		try {
			pst2=con.prepareStatement("select * from ventry where vehno=? and vstatus=0");
			pst2.setString(1, txtv.getText());
			ResultSet res1 = pst2.executeQuery();
			while(res1.next()){
				wheel1.setText(String.valueOf(res1.getInt("wheeler")));
				slot1.setText(String.valueOf(res1.getInt("slot")));
				date1.setText(String.valueOf(res1.getDate("doe")));
				time1.setText(String.valueOf(res1.getTime("doe")));
				flr1.setText(String.valueOf(res1.getInt("floor")));
				wh=res1.getInt("wheeler");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		d=new Date();
		cdate1.setText(DateFormat.getDateInstance().format(d));
	
		ctime1.setText(convert(String.valueOf(DateFormat.getTimeInstance(DateFormat.SHORT).format(d))));
	}
   String convert(String t)
   {
   	DateFormat f1=new SimpleDateFormat("hh:mm a");
   	Date d=null;
   	try
   	{
   		d=f1.parse(t);
   	}
   	catch(Exception ex)
   	{
   		ex.printStackTrace();
   	}
   	DateFormat f2=new SimpleDateFormat("HH:mm");
   	String s=f2.format(d);
   	
   	return s;
   	
   }
	public static void main(String[] args) 
	{
		new exiting();

	}

}
