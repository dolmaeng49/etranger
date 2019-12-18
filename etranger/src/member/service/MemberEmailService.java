package member.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberEmailService {
	public static boolean mailSend(String email, String subject, String content) {
		boolean isMailSendSuccess = false;
		
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
//	          System.out.println("message sent successfully...");
	          isMailSendSuccess = true;
	          
	      } catch (AddressException e) {
	          System.out.println("mailSend 실패! 주소 예외 발생! : " + e.getMessage());
	      } catch (MessagingException e) {
	          System.out.println("mailSend 실패! 메시지 예외 발생! : " + e.getMessage());
	      }
	      // 성공시 true, 실패시 false 리턴
	      return isMailSendSuccess;
	  }
	
	// 영문 대문자, 숫자가 뒤섞인 난수코드를 리턴하는 메서드
	public String createCheckCode(int codeLength) {
		String checkCode = ""; // 인증코드를 저장할 변수
		ArrayList<Character> checkCodeArrayList = new ArrayList<Character>(); // 인증코드를 저장할 배열
		Random random = new Random();
		
		int len = codeLength; // 인증코드의 길이
		int len_num = random.nextInt(len); // 코드 자리수 중 숫자의 개수
		
		int k = 0; // while 문 제어변수
		while(k < len_num) {
			// ArrayList 객체에 '0' ~ '9' 범위의 난수를 len_num 개 만큼 추가
//			checkCodeArrayList.add(Integer.toString(random.nextInt(10)).charAt(0));
			checkCodeArrayList.add((char)(random.nextInt('9' - '0') + '0'));
			k++;
		}
		while(k < len) {
			// 'A' ~ 'Z' 범위의 난수를 (len) - (len_num) 개 만큼 추가
			checkCodeArrayList.add((char)(random.nextInt('Z' - 'A') + 'A'));
			k++;
		}
		// 난수들이 저장된 ArrayList 객체를 Collections 클래스를 이용해 섞음
		Collections.shuffle(checkCodeArrayList);
		
		// ArrayList 객체에서 요소 각각에 접근해 StringBuilder 객체에 추가
		// => String 타입으로 형변환
		StringBuilder strB = new StringBuilder();
		for(char ch : checkCodeArrayList) {
			strB.append(ch);
		}
		checkCode = strB.toString();
		return checkCode;
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
