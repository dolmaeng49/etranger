package notice.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import notice.service.NoticeWriteProService;
import notice.vo.NoticeBean;

public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		NoticeBean nb = null;
		
		String realFolder=""; 
		String saveFolder="/noticeUpload";
		int fileSize = 10*1024*1024;
		String fileName="";
		
	
		
		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄
//		System.out.println(realFolder);
		
		
		//MultiPartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
		//ReviewBean 객체 생성
		
		nb= new NoticeBean();
		nb.setNotice_member_id(multi.getParameter("notice_member_id"));
		nb.setNotice_subject(multi.getParameter("notice_subject"));
		nb.setNotice_content(multi.getParameter("notice_content"));
		nb.setNotice_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
		
		boolean isWriteSuccess =noticeWriteProService.registArticle(nb);
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter out2 = response.getWriter();
		
		if(!isWriteSuccess){
			out2.println("<script>");
			out2.println("alert('공지 등록 실패!')");
			out2.println("history.back()");
			out2.println("</script>");
		}else {
			forward= new ActionForward();
			out2.println("<script>");
			out2.println("alert('공지 등록 완료!')");
			out2.println("</script>");
			forward.setPath("NoticeList.no");
			forward.setRedirect(true); 
		}
		
		return forward;
	}

}
