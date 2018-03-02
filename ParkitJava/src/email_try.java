
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class email_try {
    public email_try(String billing)
    {
        String myEmail = "evanahuja@gmail.com";
        String password = "Abhi@306";
        String opponentEmail="agatti1997@gmail.com";
        Properties pro=new Properties();
        pro.put("mail.smtp.host", "smtp.gmail.com");
        pro.put("mail.smtp.starttls.enable","true");
        pro.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        pro.put("mail.smtp.auth","true");
        pro.put("mail.smtp.port","587");
        Session ss=Session.getInstance(pro, new javax.mail.Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(myEmail,password );
                 }
    });
        try
        {
            Message msg=new MimeMessage(ss);
            msg.setFrom(new InternetAddress(myEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(opponentEmail));
            msg.setSubject("Your Wish");
            msg.setText(billing);
            Transport trans = ss.getTransport("smtp"); 
            Transport.send(msg);
            System.out.println("message sent");
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        }
    }
    }
// TRY THIS CODE AND PUT CORRECT EMAIL ID AND PASSWORD