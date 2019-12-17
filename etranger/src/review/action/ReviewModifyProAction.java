package review.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewModifyProService;
import review.vo.ReviewBean;

public class ReviewModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		ReviewBean rb = null;


		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String page = request.getParameter("page");

		rb = new ReviewBean();
		rb.setReview_num(review_num);
		rb.setReview_member_id(request.getParameter("review_member_id"));
		rb.setReview_subject(request.getParameter("review_subject"));
		rb.setReview_content(request.getParameter("review_content"));
		rb.setReview_image(request.getParameter("review_image"));
		rb.setReview_star(Integer.parseInt(request.getParameter("review_star")));

		ReviewModifyProService reviewModifyProService = new ReviewModifyProService();

		boolean isModifySuccess = reviewModifyProService.modifyArticle(rb);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!');");
			out.println("history.back()");
			out.println("</script>");

		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ReviewDetail.rv?review_num=" + review_num + "&page=" + page);
		}

		return forward;
	}

}
