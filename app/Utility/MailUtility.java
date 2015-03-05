package Utility;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import play.Play;



public class MailUtility {

	public void sendMailToUser(String  email,String positionName,String  performance_level,String  positiontype,String clearance_req,String workLocation){
	
	final String username =  Play.application().configuration()
			.getString("mail.id");;
	final String password =  Play.application().configuration()
			.getString("mail.password");;
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

try {
     	//send a  order order confirmation  mail to Admin,registered(currently order placed user)  user.
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("Ardent"));
		    //Add multiple recipients. including Admin
			//message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));

			//set msg text body
			 message.setSubject( "Your Applied Job Details");
			 BodyPart messageBodyPart = new MimeBodyPart();
			 // Now set the actual message
			 Date todaysDate = new Date();
			 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			 String dateToSend = formatter.format(todaysDate);
             //set the msg body text.	 
			 messageBodyPart.setText("User Email id: "+email+"\nPosition Applied For: "+positionName+"\nPosition Type: "+positiontype+"\nPerformance  Level: "+performance_level+"\nClearance Required: "+clearance_req+"\nWork Location: "+workLocation);
		         // Create a multipart message
	         Multipart multipart = new MimeMultipart();
	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);
	         message.setContent(multipart);
		     Transport.send(message);
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		  throw new RuntimeException(e);
	}
	}
	
	
	public void sendMailToAdmin(String  email,String positionName,String  positiontype,String workLocation){
		
		final String username =  Play.application().configuration()
				.getString("mail.id");;
		final String password =  Play.application().configuration()
				.getString("mail.password");;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

	try {
	     	//send a  order order confirmation  mail to Admin,registered(currently order placed user)  user.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Ardent"));
			    //Add multiple recipients. including Admin
				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
				//message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));

				//set msg text body
				 message.setSubject( " Job Details Applied By User ");
				 BodyPart messageBodyPart = new MimeBodyPart();
				 // Now set the actual message
				/* Date todaysDate = new Date();
				 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				 String dateToSend = formatter.format(todaysDate);*/
	             //set the msg body text.	 
				 messageBodyPart.setText("User Email id: "+email+"\nPosition Applied For: "+positionName+"\nPosition Type: "+positiontype+"\nWork Location: "+workLocation);
		         // Create a multipart message
		         Multipart multipart = new MimeMultipart();
		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);
		         message.setContent(multipart);
			     Transport.send(message);
			
			
			
			} catch (Exception e) {
				e.printStackTrace();
			  throw new RuntimeException(e);
		}
		}
	
public void sendRegistrationMail(String  email,String pass){
		
		final String username =  Play.application().configuration()
				.getString("mail.id");;
		final String password =  Play.application().configuration()
				.getString("mail.password");;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

	try {
	     	//send a  order order confirmation  mail to Admin,registered(currently order placed user)  user.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Ardent"));
			    //Add multiple recipients. including Admin
			//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));

				//set msg text body
				 message.setSubject( " Your  Account is Created Successfully ");
				 BodyPart messageBodyPart = new MimeBodyPart();
				 // Now set the actual message
				/* Date todaysDate = new Date();
				 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				 String dateToSend = formatter.format(todaysDate);*/
	             //set the msg body text.	 
				 messageBodyPart.setText("Your UserId/Email is: "+email+"\nPassword is: "+pass);
		         // Create a multipart message
		         Multipart multipart = new MimeMultipart();
		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);
		         message.setContent(multipart);
			     Transport.send(message);
			
			
			} catch (Exception e) {
				e.printStackTrace();
			  throw new RuntimeException(e);
		}
		}
	
public void sendMailForgetpassword(String  email,String pass){
		
	final String username = Play.application().configuration()
			.getString("mail.id");
	final String password = Play.application().configuration()
			.getString("mail.password");

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

	try {

     		     Message message = new MimeMessage(session);
				  message.setFrom(new InternetAddress("Ardent"));
				    //Add multiple recipients. including Admin
				//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));

					//set msg text body
					 message.setSubject( " Your  password  Details  ");
					 BodyPart messageBodyPart = new MimeBodyPart();
					 // Now set the actual message
					/* Date todaysDate = new Date();
					 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String dateToSend = formatter.format(todaysDate);*/
		             //set the msg body text.	 
					 messageBodyPart.setText("Your UserId/Email is: "+email+"\nPassword is: "+pass);
			         // Create a multipart message
			         Multipart multipart = new MimeMultipart();
			         // Set text message part
			         multipart.addBodyPart(messageBodyPart);
			         message.setContent(multipart);
				     Transport.send(message);
		
	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}

}





public void sendMailToAlluser(String  mailsubject,String mailcontent,ArrayList<String> userEmailList){
	
	final String username = Play.application().configuration()
			.getString("mail.id");
	final String password = Play.application().configuration()
			.getString("mail.password");

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

	try {

     		     Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("Ardent"));
				    //Add multiple recipients. including Admin
				//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
				//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(userEmailList));
					InternetAddress[] recipientAddress = new InternetAddress[userEmailList.size()];
					int counter = 0;
					for (String recipient : userEmailList) {
					    recipientAddress[counter] = new InternetAddress(recipient.trim());
					    counter++;
					}
					message.setRecipients(Message.RecipientType.TO, recipientAddress);
					//set msg text body
					 message.setSubject( mailsubject);
					 BodyPart messageBodyPart = new MimeBodyPart();
					 // Now set the actual message
					/* Date todaysDate = new Date();
					 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String dateToSend = formatter.format(todaysDate);*/
		             //set the msg body text.	 
					 messageBodyPart.setText(mailcontent);
			         // Create a multipart message
			         Multipart multipart = new MimeMultipart();
			         // Set text message part
			         multipart.addBodyPart(messageBodyPart);
			         message.setContent(multipart);
				     Transport.send(message);
		     
		     
		
	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
	
	

}

public void sendmailAlertToUserAboutJobMatched(String  email,int matchjobJobSize){
	
	final String username = Play.application().configuration()
			.getString("mail.id");
	final String password = Play.application().configuration()
			.getString("mail.password");

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

	try {

     		     Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("Ardent"));
				    //Add multiple recipients. including Admin
				//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
				//	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(userEmailList));
				 message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email));

					//set msg text body
					 message.setSubject("New Jobs Posted at i2Simply-Done");
					 BodyPart messageBodyPart = new MimeBodyPart();
					 // Now set the actual message
					/* Date todaysDate = new Date();
					 Format formatter = new SimpleDateFormat("yyyy-MM-dd");
					 String dateToSend = formatter.format(todaysDate);*/
		             //set the msg body text.
					 String webaddress = "portal";
					 messageBodyPart.setText("You have "+matchjobJobSize+" new  Positions matching your profile,Please login to"+webaddress +"to view the profile \n\n\n\n\n Regards,\ni2Simply-Done team \nrobert.mccauley@ardentprinciples.com");
			         // Create a multipart message
			         Multipart multipart = new MimeMultipart();
			         // Set text message part
			         multipart.addBodyPart(messageBodyPart);
			         message.setContent(multipart);
				     Transport.send(message);
		     
		     
		
	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
	
	

}





}

	

