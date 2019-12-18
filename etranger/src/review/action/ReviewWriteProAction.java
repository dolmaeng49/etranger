package review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewWriteProService;
import review.vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		ReviewBean rb = null;
		
		String review_member_id = request.getParameter("review_member_id");
		String review_package_category_code = request.getParameter("review_package_category_code");
		

		rb = new ReviewBean();

		rb.setReview_member_id(review_member_id);
		rb.setReview_subject(request.getParameter("review_subject"));
		rb.setReview_content(request.getParameter("review_content"));
		rb.setReview_image(request.getParameter("review_image"));
		rb.setReview_package_catagory_code(review_package_category_code);
		rb.setReview_member_name(request.getParameter("review_member_name"));
		rb.setReview_star(Integer.parseInt(request.getParameter("review_star")));
		rb.setReview_comment_count(Integer.parseInt(request.getParameter("review_comment_count")));

		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();

		boolean duplicateCheck = reviewWriteProService.duplicateCheck(review_member_id, review_package_category_code);
		
		if(duplicateCheck) { // 이미 리뷰를 남겼을 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('리뷰는 패키지당 하나씩만 작성 가능합니다!')");
			out.println("history.back()");
			out.println("</script>");
		}else { //해당 상품에 남긴 리뷰가 없을 경우
			
			boolean isWriteSuccess = reviewWriteProService.registArticle(rb);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			
			if (!isWriteSuccess) {
				out.println("<script>");
				out.println("alert('리뷰 등록 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				out.println("<script>");
				out.println("alert('리뷰 등록 완료!')");
				out.println("</script>");
				
				forward.setPath("ReviewList.rv");
				forward.setRedirect(true);
			}
			
		}

		return forward;
	}

}
