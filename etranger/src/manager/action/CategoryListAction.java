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

public class CategoryListAction implements Action {

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
		
		String op = "";
		
		op += "<section id=\"productInsert\" \">"
			+ "<section class=\"ftco-section bg-light\">"
			+ "<h1>분류 리스트</h1>";
		if (productList != null && listCount > 0) {
			op += "<div class=\"container\">"
			+ "<div class=\"row\">";
			for (int i = 0; i < productList.size(); i++) {
				op += "<div class=\"col-md-6 col-lg-3\">"
				+ "<div class=\"blog-entry\">"
				+ "<a href=\"ProductDetail.ma?package_category_code="
						+ productList.get(i).getPackage_category_code() + "&package_category_theme="
						+ productList.get(i).getPackage_category_theme() + "\"class=\"block-20\""
						+ "style=\"background-image: url('ManagerImgUpload/"
						+ productList.get(i).getPackage_category_image() + "')\"></a>"
				+ "<div class=\"text p-4\">"
				+ "<div class=\"meta\">"
				+ "<div>"
				+ "<a href=\"#\">July 6, 2018</a>"
				+ "</div>"
				+ "<div>"
				+ "<a href=\"#\">Admin</a>"
				+ "</div>"
				+ "</div>"
				+ "<h3 class=\"heading\">"
				+ "<a href=\"ProductDetail.ma?package_category_code="
						+ productList.get(i).getPackage_category_code() + "&package_category_theme="
						+ productList.get(i).getPackage_category_theme() + "\">"
						+ productList.get(i).getPackage_category_name() + "</a>"
				+ "</h3>"
				+ "<p class=\"clearfix\">"
				+ "<a href=\"#\" class=\"float-left\">Read more</a> <a href=\"#\" class=\"float-right meta-chat\"><span class=\"icon-chat\"></span> 3</a>"
				+ "</p>"
				+ "</div>"
				+ "</div>"
				+ "</div>";
			}
			op += "</div>"
			+ "<div class=\"row mt-5\">"
			+ "<div class=\"col text-center\">"
			+ "<div class=\"block-27\">"
			+ "<ul>";
			if (nowPage <= 1) {
				op += "<li><a>&lt;</a></li>";
			} else {
				op += "<li class=\"active\" style=\"cursor: pointer;\"><a onclick=\"pageNum(" + (nowPage - 1) + ")\"" + "\">&lt;</a></li>";
			}
			for (int i = startPage; i <= endPage; i++) {
				if (i == nowPage) {
					op += "<li href=\"#\" class=\"active\" style=\"cursor: pointer;\"><span>" + i + "</span></li>";
				} else {
					op += "<li><a onclick=\"pageNum(" + i + ")\" style=\"cursor: pointer;\">" + i + "</a></li>";
				}
			}
			if (nowPage >= maxPage) {
				op += "<li><a>&gt;</a></li>";
			} else {
				op += "<li class=\"active\"><a onclick=\"pageNum(" + (nowPage + 1) + ")\" style=\"cursor: pointer;\"" + "\">&gt;</a></li>";
			}
			op += "</ul>"
			+ "</div>"
			+ "</div>"
			+ "</div>";
		} else {
			op += "<div id=\"emptyArticle\">등록된 글이 없습니다</div>";
		}
		op += "</div>"
		+ "</section>"
		+ "</section>"
		+ "<script src=\"js/manager.js\"></script>";
		
		out.print(op);
		return null;
	}

}
