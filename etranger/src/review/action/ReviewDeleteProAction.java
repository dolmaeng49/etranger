package review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewDeleteProService;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String review_member_id = (String) request.getParameter("review_member_id");
		String page = request.getParameter("page");
		System.out.println(review_num + review_member_id + page);

		ReviewDeleteProService reviewDeleteProService = new ReviewDeleteProService();
		boolean isRightUser = reviewDeleteProService.isArticleWriter(review_num, review_member_id);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (!isRightUser) { // 권한 없을 시
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다');");
			out.println("history.back()");
			out.println("</script>");

		} else { // 권한 있을 시

			boolean isDeleteSuccess = reviewDeleteProService.removeArticle(review_num);

			if (!isDeleteSuccess) {
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back()");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('삭제 성공');");
				out.println("</script>");

				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("ReviewList.rv?page=" + page);
			}
		}

		return forward;
	}

}
