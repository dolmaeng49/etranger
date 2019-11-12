package review.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewWriteProService;
import review.vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewWriteProAction");
		
		ActionForward forward = null;
		
		ReviewBean rb = null;
		
		String realFolder=""; 
		String saveFolder="/reviewUpload";
		int fileSize = 10*1024*1024;
		
		//클라이언트 ip 추출 
//		String writerIpAddr = request.getRemoteAddr();
//		System.out.println(writerIpAddr);
				
		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄
//		System.out.println(realFolder);
		
		//MultiPartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
		//ReviewBean 객체 생성
		
		rb= new ReviewBean();
		rb.setReview_member_id(multi.getParameter("review_member_id"));
		rb.setReview_subject(multi.getParameter("subject"));
		rb.setReview_content(multi.getParameter("content"));
		rb.setReview_image(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		rb.setReview_package_catagory_code(multi.getParameter("review_package_category_code"));
	
		
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		boolean isWriteSuccess =reviewWriteProService.registArticle(rb);
		response.setContentType("text/html; charset=UTF-8"); // 문서 타입(contentType) 지정
		PrintWriter out = response.getWriter();
		
		if(!isWriteSuccess){
			//웹브라우저로 HTML 코드 등을 내보내기 위한 
			out.println("<script>");
			out.println("alert('리뷰 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward= new ActionForward();
			out.println("<script>");
			out.println("alert('리뷰 등록 완료!')");
			out.println("</script>");
			forward.setPath("ReviewList.rv");
			forward.setRedirect(true); 
		}
		
		return forward;
	}

}
