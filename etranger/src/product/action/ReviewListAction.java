package product.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.vo.CategoryBean;
import product.svc.ProductReviewListService;
import review.vo.ReviewBean;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String categoryCode = request.getParameter("package_category_code");

		ProductReviewListService productReviewListService = new ProductReviewListService();
		int listCount = productReviewListService.getListCount(categoryCode);

		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		reviewList = productReviewListService.getReviewList(categoryCode, page, limit);

		// 페이징
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		int nowPage = pageInfo.getPage();
		maxPage = pageInfo.getMaxPage();
		startPage = pageInfo.getStartPage();
		endPage = pageInfo.getEndPage();
		listCount = pageInfo.getListCount();

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String op = "";

		if (reviewList != null && listCount > 0) {
			op += "<table class=\"table table-hover\">" 
					+"<thead>"
					+ "<tr>"
					+ "<th scope=\"col\">NO.</th>" 
					+ "<th scope=\"col\">제목</th>" 
					+ "<th scope=\"col\">작성자</th>" 
					+ "<th scope=\"col\">작성일</th>"
					+ "<th scope=\"col\">상품평점</th>" 
					+ "</tr>"
					+ "</thead>";
			for (ReviewBean rl : reviewList) {
				op += "<tr>" 
						+ "<td>" + rl.getReview_num() + "</td>" 
						+ "<td>" + rl.getReview_subject() + "</td>"
						+ "<td>" + rl.getReview_member_name() + "</td>" 
						+ "<td>" + rl.getReview_date() + "</td>"
						+ "<td>" + "<div id=\"stars\">";
				if (rl.getReview_star() == 0) {
					op += "<img src=\"images/rating0.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 1) {
					op += "<img src=\"images/rating01.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 2) {
					op += "<img src=\"images/rating02.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 3) {
					op += "<img src=\"images/rating03.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 4) {
					op += "<img src=\"images/rating04.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 5) {
					op += "<img src=\"images/rating05.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 6) {
					op += "<img src=\"images/rating06.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 7) {
					op += "<img src=\"images/rating07.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 8) {
					op += "<img src=\"images/rating08.png\" align=\"middle\" />";
				} else if (rl.getReview_star() == 9) {
					op += "<img src=\"images/rating09.png\" align=\"middle\" />";
				} else {
					op += "<img src=\"images/rating10.png\" align=\"middle\" />";
				}
				op += "</div>" 
						+ "</td>" 
						+ "</tr>";
			}
			op += "</table>"
			
			+"<div class=\"row mt-5\">"
			+"<div class=\"col text-center\">"
			+"<div class=\"block-27\">"
			+"<ul>";
			if (nowPage <= 1) {
				op+="<li><a>&lt;</a></li>";
			} else {
				op+="<li class=\"active\" style=\"cursor: pointer;\"><a onclick=\"pageNum(" + (nowPage - 1) + ")\"" + "\">&lt;</a></li>";
			}
			for (int i = startPage; i <= endPage; i++) {
				if (i == nowPage) {
					op+="<li href=\"#\" class=\"active\" style=\"cursor: pointer;\"><span>" + i + "</span></li>";
				} else {
					op+="<li><a onclick=\"pageNum(" + i + ")\" style=\"cursor: pointer;\">" + i + "</a></li>";
				}
			}
			if (nowPage >= maxPage) {
				op+="<li><a>&gt;</a></li>";
			} else {
				op+="<li class=\"active\"><a onclick=\"pageNum(" + (nowPage + 1) + ")\" style=\"cursor: pointer;\"" + "\">&gt;</a></li>";
			}
			op+="</ul>"
			+"</div>"
			+"</div>"
			+"</div>";
			
		} else {
			op += "<div id=\"emptyArticle\">등록된 글이 없습니다</div>";
		}

		out.print(op);
		return null;
	}

}
