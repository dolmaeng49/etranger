package review.action;

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
		
		//ReviewBean 객체 생성
		
		System.out.println(request.getParameter("review_member_id"));
		System.out.println(request.getParameter("review_star"));
		System.out.println(request.getParameter("review_subject"));
		
		rb= new ReviewBean();
		
		rb.setReview_member_id(request.getParameter("review_member_id"));
		rb.setReview_subject(request.getParameter("review_subject"));
		rb.setReview_content(request.getParameter("review_content"));
		rb.setReview_image(request.getParameter("review_image"));
		rb.setReview_package_catagory_code(request.getParameter("review_package_category_code"));
		rb.setReview_member_name(request.getParameter("review_member_name"));
		rb.setReview_star(Integer.parseInt(request.getParameter("review_star")));
		rb.setReview_comment_count(Integer.parseInt(request.getParameter("review_comment_count")));
		
		System.out.println("review_package_category_code : "+rb.getReview_package_catagory_code());
		
		
		System.out.println("star: " +rb.getReview_star());
		System.out.println("content: " +rb.getReview_content());
		System.out.println("image : " +rb.getReview_image());
		
		
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		boolean isWriteSuccess =reviewWriteProService.registArticle(rb);
		response.setContentType("text/html; charset=UTF-8"); 
		
		PrintWriter out2 = response.getWriter();
		
		if(!isWriteSuccess){
			out2.println("<script>");
			out2.println("alert('리뷰 등록 실패!')");
			out2.println("history.back()");
			out2.println("</script>");
		}else {
			forward= new ActionForward();
			out2.println("<script>");
			out2.println("alert('리뷰 등록 완료!')");
			out2.println("</script>");
			forward.setPath("ReviewList.rv");
			forward.setRedirect(true); 
		}
		
		return forward;
	}

}
