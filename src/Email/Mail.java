package Email;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail
{

    //SETUP MAIL SERVER PROPERTIES
    //DRAFT AN EMAIL
    //SEND EMAIL

    Session newSession = null;
    MimeMessage mimeMessage = null;
    public static void MainFunction(String EmailId,String Subject,String Body) throws AddressException, MessagingException, IOException
    {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail(EmailId,Subject,Body);
        mail.sendEmail();
    }

//    public static void MainFunctionPass(String EmailId,String Subject,String Body) throws AddressException, MessagingException, IOException
//    {
//        Email.Mail mail = new Email.Mail();
//        mail.setupServerProperties();
//        mail.draftEmail(EmailId,Subject,Body);
//        mail.sendEmail();
//    }

    private void sendEmail() throws MessagingException {
        String fromUser = "youremail@example.com";  //Enter sender email id

        String fromUserPassword = "email password";  //Enter sender gmail password , this will be authenticated by gmail smtp server

        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }


    private MimeMessage draftEmail(String EmailId,String Subject,String Body) throws AddressException, MessagingException, IOException {
        String[] emailReceipients = {EmailId};  //Enter list of email recepients
        String emailSubject = Subject;
        String emailBody = Body;
        mimeMessage = new MimeMessage(newSession);

        for (int i =0 ;i<emailReceipients.length;i++)
        {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE
        // CREATE MESSAGE BODY PARTS
        // CREATE MESSAGE MULTIPART
        // ADD MESSAGE BODY PARTS ----> MULTIPART
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object


        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody,"text/plain");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties,null);
    }

}