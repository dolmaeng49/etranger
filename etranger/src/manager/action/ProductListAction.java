package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.svc.CategoryListService;
import manager.vo.CategoryBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = 1;
		int limit = 8;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		CategoryListService productListService = new CategoryListService();
		int listCount = productListService.getListCount();

		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();
		productList = productListService.getCategoryList(page, limit);

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

		out.print("<section id=\"productInsert\" \">");
		out.print("<section class=\"ftco-section bg-light\">");
		out.print("<h1>분류 리스트</h1>");
		if (productList != null && listCount > 0) {
			out.print("<div class=\"container\">");
			out.print("<div class=\"row\">");
			for (int i = 0; i < productList.size(); i++) {
				out.print("<div class=\"col-md-6 col-lg-3\">");
				out.print("<div class=\"blog-entry\">");
				out.print("<a href=\"ProductDetail.ma?package_category_code="
						+ productList.get(i).getPackage_category_code() + "&package_category_theme="
						+ productList.get(i).getPackage_category_theme() + "\"class=\"block-20\""
						+ "style=\"background-image: url('ManagerImgUpload/"
						+ productList.get(i).getPackage_category_image() + "')\"></a>");
				out.print("<div class=\"text p-4\">");
				out.print("<div class=\"meta\">");
				out.print("<div>");
				out.print("<a href=\"#\">July 6, 2018</a>");
				out.print("</div>");
				out.print("<div>");
				out.print("<a href=\"#\">Admin</a>");
				out.print("</div>");
				out.print("</div>");
				out.print("<h3 class=\"heading\">");
				out.print("<a href=\"ProductDetail.ma?package_category_code="
						+ productList.get(i).getPackage_category_code() + "&package_category_theme="
						+ productList.get(i).getPackage_category_theme() + "\">"
						+ productList.get(i).getPackage_category_name() + "</a>");
				out.print("</h3>");
				out.print("<p class=\"clearfix\">");
				out.print(
						"<a href=\"#\" class=\"float-left\">Read more</a> <a href=\"#\" class=\"float-right meta-chat\"><span class=\"icon-chat\"></span> 3</a>");
				out.print("</p>");
				out.print("</div>");
				out.print("</div>");
				out.print("</div>");
			}
			out.print("</div>");
			out.print("<div class=\"row mt-5\">");
			out.print("<div class=\"col text-center\">");
			out.print("<div class=\"block-27\">");
			out.print("<ul>");
			if (nowPage <= 1) {
				out.print("<li><a>&lt;</a></li>");
			} else {
				out.print("<li class=\"active\" style=\"cursor: pointer;\"><a onclick=\"pageNum(" + (nowPage - 1) + ")\"" + "\">&lt;</a></li>");
			}
			for (int i = startPage; i <= endPage; i++) {
				if (i == nowPage) {
					out.print("<li href=\"#\" class=\"active\" style=\"cursor: pointer;\"><span>" + i + "</span></li>");
				} else {
					out.print("<li><a onclick=\"pageNum(" + i + ")\" style=\"cursor: pointer;\">" + i + "</a></li>");
				}
			}
			if (nowPage >= maxPage) {
				out.print("<li><a>&gt;</a></li>");
			} else {
				out.print("<li class=\"active\"><a onclick=\"pageNum(" + (nowPage + 1) + ")\" style=\"cursor: pointer;\"" + "\">&gt;</a></li>");
			}
			out.print("</ul>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");
		} else {
			out.println("<div id=\"emptyArticle\">등록된 글이 없습니다</div>");
		}
		out.print("</div>");
		out.print("</section>");
		out.print("</section>");
		out.print("<script src=\"js/manager.js\"></script>");
		return null;
	}

}
