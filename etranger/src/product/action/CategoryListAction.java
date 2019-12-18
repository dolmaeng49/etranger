package product.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.vo.CategoryBean;
import product.svc.CategoryListService;
import product.vo.SearchBean;

public class CategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		// 페이징 처리
		int page = 1;
		int limit = 8;
		System.out.println(request.getParameter("page"));
		// 페이지 정보 파라미터를 받은 경우
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		// 검색정보 파라미터 가져오기
		String keyword = request.getParameter("keyword");
		String arriv_date = request.getParameter("arriv_date");
		String region = request.getParameter("region");
		String city = request.getParameter("city");
		
		// 출발날짜가 선택되지않았을 경우 로컬시간 1일 후로 설정
		LocalDateTime date = LocalDateTime.now().plusDays(1);
		String depart_date = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		
		// parameter 로 받은 출발 날짜가 null 이 아니고 길이가 0이 아니라면 받아온 데이터로 초기화
		if(request.getParameter("depart_date") != null && request.getParameter("depart_date").trim().length() != 0) {
			depart_date = request.getParameter("depart_date");
		}
		// null 을 널스트링으로 대체
		keyword = keyword == null ? "" : keyword;
		arriv_date = arriv_date == null ? "" : arriv_date;
		region = region == null ? "" : region;
		city = city == null ? "" : city; 
		
		//SearchBean
		SearchBean searchBean = new SearchBean(keyword,depart_date, arriv_date,region,city);
		
		CategoryListService categoryListService = new CategoryListService();
		int listCount = categoryListService.getListCount(searchBean);
		
		ArrayList<CategoryBean> categoryList = categoryListService.getCategoryList(page, limit, searchBean);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("searchBean", searchBean);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("categoryList", categoryList);
		
		forward = new ActionForward();
		forward.setPath("/product/categorySearchList.jsp");

		return forward;
	}

}
