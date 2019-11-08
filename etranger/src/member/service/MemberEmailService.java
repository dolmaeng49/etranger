package member.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberEmailService {
	public static void mailSend(String email, String subject, String content) {
		System.out.println("MemberEmailService - mailSend");
		String user = "etrangermanager"; // gmail 계정
	    String userAddress = user;
	    String password = "etranger1234";   // 패스워드
	    // SMTP 서버 정보를 설정한다.
	    Properties prop = new Properties();
	    prop.put("mail.smtp.auth", "true"); 
	    prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.host", "smtp.gmail.com"); 
		prop.put("mail.smtp.port", 465); 
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication(user, password);
	          }
	      });
	
	      try {
	          MimeMessage message = new MimeMessage(session);
	          message.setFrom(new InternetAddress(userAddress));
	
	          //수신자메일주소
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
	
	          // Subject
	          message.setSubject(subject); //메일 제목을 입력
	
	          // Text
	          message.setText(content);    //메일 내용을 입력
	
	          // send the message
	          Transport.send(message); ////전송
	          System.out.println("message sent successfully...");
	      } catch (AddressException e) {
	          System.out.println("mailSend 실패! 주소 예외 발생! : " + e.getMessage());
	      } catch (MessagingException e) {
	          System.out.println("mailSend 실패! 메시지 예외 발생! : " + e.getMessage());
	      }
	  }
//	
//	public static void mailSend(String email, String subject, String content) {
//      String user = ""; // 네이버 계정
//      String id = "";
//      String userAddress = id; // 보내는 사람 메일 주소
//      String password = "!";  // 패스워드
//      // SMTP 서버 정보를 설정한다.
//      Properties prop = new Properties();
//      prop.put("mail.smtp.auth", "true"); 
//	    prop.put("mail.smtp.host", "smtp.naver.com"); 
//	    prop.put("mail.smtp.port", 587); 
//      Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
//          protected PasswordAuthentication getPasswordAuthentication() {
//              return new PasswordAuthentication(user, password);
//          }
//      });
//
//      try {
//          MimeMessage message = new MimeMessage(session);
//          message.setFrom(new InternetAddress(userAddress));
//
//          //수신자메일주소
//          message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
//
//          // Subject
//          message.setSubject(subject); //메일 제목을 입력
//
//          // Text
//          message.setText(content);    //메일 내용을 입력
//
//          // send the message
//          Transport.send(message); ////전송
//          System.out.println("message sent successfully...");
//      } catch (AddressException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      } catch (MessagingException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
//  }
}
