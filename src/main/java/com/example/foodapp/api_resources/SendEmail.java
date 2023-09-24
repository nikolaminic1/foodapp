package com.example.foodapp.api_resources;

import java.util.Properties;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
//import jakarta.mail.Message;
//import jakarta.mail.MessagingException;
//import jakarta.mail.PasswordAuthentication;
//import jakarta.mail.Session;
//import jakarta.mail.Transport;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.web.servlet.server.Session;

public class SendEmail {
    public static void sendEmailGrantBusinessRequest(String email, String message){
        // Recipient's email ID needs to be mentioned.
        String to = "fromaddress@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "toaddress@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication("fromaddress@gmail.com", "*******");
//
//            }
//
//        });

//        session.setDebug(true);
//
//        try {
            // Create a default MimeMessage object.
//            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
//            mimeMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
//            mimeMessage.setSubject("This is the Subject Line!");

            // Now set the actual message
//            mimeMessage.setText("This is actual message");

//            System.out.println("sending...");
            // Send message
//            Transport.send(mimeMessage);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
    }
}
