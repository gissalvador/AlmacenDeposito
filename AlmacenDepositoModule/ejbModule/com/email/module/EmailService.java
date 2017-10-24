package com.email.module;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Session Bean implementation class EmailService
 */
@Stateless
@LocalBean
public class EmailService implements EmailServiceRemote {

	@Resource(name = "java:jboss/mail/gmail")
    private Session session;
	
	/**
     * Default constructor. 
     */
    public EmailService() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
    public void send(String addresses, String topic, String textMessage) {
    	 
        try {
 
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            Logger.getLogger(EmailService.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
    
    

}
