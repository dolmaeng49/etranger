package notice.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import notice.service.NoticeModifyProService;
import notice.vo.NoticeBean;

public class NoticeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		NoticeBean nb = null;
		
		String realFolder=""; 
		String saveFolder="/noticeUpload";
		int fileSize = 10*1024*1024;
		
		
		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄
//		System.out.println(realFolder);
		
		//MultiPartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());		
			// ReviewBean 객체에 수정할 게시물 정보를 저장한 뒤
			// Service 클래스의 modifyArticle() 메서드를 호출하여 전달
		
		int notice_num = Integer.parseInt(multi.getParameter("notice_num"));
		String page = multi.getParameter("page");
		
		nb= new NoticeBean();
		nb.setNotice_num(notice_num);
		nb.setNotice_member_id(multi.getParameter("notice_member_id"));
		nb.setNotice_subject(multi.getParameter("notice_subject"));
		nb.setNotice_content(multi.getParameter("notice_content"));
		nb.setNotice_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
			
			NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
			
			boolean isModifySuccess = noticeModifyProService.modifyArticle(nb);
			
			// 글 수정 성공 여부를 판별하여 실패했을 경우 자바스크립트를 사용하여 "글 수정 실패!" 를 출력하고
			// 성공했을 경우 "ReviewDetail.rv" 로 포워딩 => Redirect 방식 사용, review_num 과 page 전달
			
			if(!isModifySuccess) { // 글 수정 실패 여부 판단
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter(); // response 객체로부터 PrintWriter 객체 얻어오기
				out.println("<script>");
				out.println("alert('글 수정 실패!');");
				out.println("history.back()");
				out.println("</script>");
				
			} else {
				forward = new ActionForward();
				forward.setRedirect(true); // 새로운 서블릿 주소를 요청하므로 Redirect 방식 포워딩+
				forward.setPath("NoticeList.no?notice_num=" + notice_num + "&page=" + page);
			}
			
		
		
		
		return forward;
	}

}
