import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledEditorKit.UnderlineAction;

import com.mysql.jdbc.Connection;


@SuppressWarnings("unused")
class rform extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7497333721133705593L;
	
	JTextArea address;
	JTextField custid,contact,cname,city;
	JLabel piclabel,custidl,contactl,cnamel,cityl,addressl,cpicl,label;
	JButton save,browse,update,delete,fetch;
	Border brd;
	JPanel pnl,pnl1;
	Connection con;
	PreparedStatement pst;
	String path;
	ImageIcon ico=new ImageIcon("3images (1).jpe");
	JLabel top=new JLabel(ico);
	//ImageIcon ico=new ImageIcon("")
	private int res;
        SendEmail sm = new SendEmail("agatti1997@gmail.com","First","VIDEO");
	rform()
	{
		
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
		pnl1=new JPanel();
		pnl1.setBackground(blue);
		pnl1.setBounds(0,0,700,100);
		add(pnl1);
		JLabel ab=new JLabel("REGISTER");
		ab.setBounds(0, 0, 100, 100);
		ab.setFont(new Font("Times New Roman",Font.PLAIN,40));
		ab.setForeground(Color.WHITE);
		pnl1.add(ab);
		
		
		brd=BorderFactory.createLineBorder(blue, 1, true);
		setLayout(null);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setUndecorated(true);
		
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(0,0, 700,800);
		pnl.setBackground(Color.white);
		pnl.setBorder(BorderFactory.createTitledBorder("Register"));
		add(pnl);
		top.setBounds(0, 0,ico.getIconWidth(), ico.getIconHeight());
		add(top);
		//////////////////////////////////////////////////
		BufferedImage myPicture;
		JLabel label1 = new JLabel();
		label1.setIcon(new ImageIcon("images"));// your image here
		label1.setBounds(0,0 , 120,120);
		pnl.add(label1);
		
		
		
		custid=new JTextField();
		custid.setBounds(180,130,200,30);
		custid.setBorder(brd);
		pnl.add(custid);
		custidl=new JLabel("CUSTOMER ID: ");
		custidl.setBounds(60,130,200,30);
		pnl.add(custidl);
		//////////////////////////////////////////////////
		
		cname=new JTextField();
		cname.setBounds(180,200,200,30);
		cname.setBorder(brd);
		pnl.add(cname);
		cnamel=new JLabel("CUSTOMER NAME:");
		cnamel.setBounds(60,200,200,30);
		pnl.add(cnamel);
		//////////////////////////////////////////////////
		
		contact=new JTextField();
		contact.setBounds(180,270,200,30);
		contact.setBorder(brd);
		pnl.add(contact);
		contactl=new JLabel("CONTACT : ");
		contactl.setBounds(60,270,200,30);
		pnl.add(contactl);
		///////////////////////////////////////////////////
		
		city=new JTextField();
		city.setBounds(180,340,200,30);
		city.setBorder(brd);
		pnl.add(city);
		cityl=new JLabel("CITY : ");
		cityl.setBounds(60,340,200,30);
		pnl.add(cityl);
		///////////////////////////////////////////////////
		
		address=new JTextArea();
		address.setBounds(180,410,200,70);
		address.setBorder(brd);
		pnl.add(address);
		addressl=new JLabel("ADDRESS : ");
		addressl.setBounds(60,410,200,30);
		pnl.add(addressl);
		
		///////////////////////////////////////////////////
		
		cpicl=new JLabel("PIC : ");
		cpicl.setBounds(60,500,200,30);
		pnl.add(cpicl);
		/*cpic=new JTextField();
		cpic.setBounds(180,500,200,30);
		pnl.add(cpic);
		
		
		/browse=new JButton("BROWSE");	
		browse.setBounds(410,500,50,30);
		pnl.add(browse);
		*/
		save=new JButton("SAVE");	
		save.setBounds(120,580,80,30);
		pnl.add(save);
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
				
			}
		});
		
		//////////////////////////////////////
		delete=new JButton("DELETE");	
		delete.setBounds(250,580,80,30);
		pnl.add(delete);
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
				
			}
		});
		//////////////////////////////////////
		update=new JButton("UPDATE");	
		update.setBounds(380,580,80,30);
		pnl.add(update);
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				
			}
		});
		//////////////////////////////
		fetch=new JButton("FETCH");	
		fetch.setBounds(490,580,80,30);
		pnl.add(fetch);
		
		fetch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fetch();
				
			}
		});
		//////////////////////////
		label = new JLabel();
		label.setBounds(500, 130, 120, 140);
		label.setBorder(brd);
		//label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setLayout(null);
		pnl.add(label);
		browse = new JButton("Browse File");
		browse.setBounds(180,500,80,30);
		browse.setLayout(null);
		browse.setBorder(brd);
		browse.addActionListener( new ActionListener() 
		{

		public void actionPerformed(ActionEvent arg0) 
		{
			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","gif","png");
			file.addChoosableFileFilter(filter);
			int result = file.showOpenDialog(null);
			if(result==JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = file.getSelectedFile();
				path = selectedFile.getAbsolutePath();
				JOptionPane.showMessageDialog(null,"Image Path is :"+ path);
				label.setIcon(Resize(path));
			}
			else if(result == JFileChooser.CANCEL_OPTION)
			{
				System.out.println("No File Selected");
			} 
		}
		});
		pnl.add(browse);
		setVisible(true);
		setSize(670,670);
		setLocation(400,0);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
	}
	public ImageIcon Resize(String ImagePath)
	{
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newlmg = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);

		ImageIcon image = new ImageIcon(newlmg);
		return image;
	}
	
	void save()
	{
		try 
		{
			pst=con.prepareStatement("insert into cregister values(?,?,?,?,?,?)");
			pst.setString(1, custid.getText());
			pst.setString(2, cname.getText());
			pst.setString(3, contact.getText());
			pst.setString(4, city.getText());
			pst.setString(5, address.getText());
			pst.setString(6, path);
			res = pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Record Saved");
			dispose();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	void update()
	{
		try 
		{
			
			pst=con.prepareStatement("update cregister set cname=?,contact=?,city=?,address=?,cpic=? where custid=?");
			pst.setString(6, custid.getText());
			pst.setString(1, cname.getText());
			pst.setString(2, contact.getText());
			pst.setString(3, city.getText());
			pst.setString(4, address.getText());
			pst.setString(5, path);
			res = pst.executeUpdate();
			//JOptionPane.showMessageDialog(null, res+"Record Updated");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	void delete()
	{
		try {
			pst=con.prepareStatement("delete from cregister where custid=?");
			pst.setString(1, custid.getText());
			res = pst.executeUpdate();
			//JOptionPane.showMessageDialog(null, res+"Record Deleted");
		} 	
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	void fetch()
	{
		try {
			pst=con.prepareStatement("select  * from cregister where custid=?");
			pst.setString(1, custid.getText());
			ResultSet res1 = pst.executeQuery();
			while(res1.next()){
				//custid,contact,cname,city
				cname.setText(res1.getString("cname"));
				contact.setText(res1.getString("contact"));
				address.setText(res1.getString("address"));
				city.setText(res1.getString("city"));
				if(res1.getString("cpic")!=null)
				label.setIcon(Resize(res1.getString("cpic")));
						
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
}

public class cregister {
	public static void main(String[] args) {
		new rform();

	}

}
