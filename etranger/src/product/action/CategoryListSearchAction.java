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
import product.svc.CategorySearchListService;
import product.vo.SearchBean;

public class CategoryListSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		// 페이징 처리
		int page = 1;
		int limit = 8;
		
		// 페이지 정보 파라미터를 받은 경우
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		// 검색정보 파라미터 가져오기
		String keyword = request.getParameter("keyword");
//		String keyword = "";
//		String strKeyword = request.getParameter("keyword");
//		if(strKeyword!=null) {
//			keyword = strKeyword;
//		}
		
		// 출발날짜가 선택되지않았을 경우 로컬시간 1일 후로 설정
		LocalDateTime date = LocalDateTime.now().plusDays(1);
		String depart_date = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		depart_date = request.getParameter("depart_date");
		String arriv_date = request.getParameter("arriv_date");
		String region = request.getParameter("region");
		String city = request.getParameter("city");
		
		
//		int guest_count = Integer.parseInt(request.getParameter("guest_count"));

		
		/*
		 * 고객 상품 리스트 + 검색창
			
			1. 키워드로 검색
			(이름 like 키워드
			or 지역 like 키워드
			or 도시 like 키워드
			or 테마 like 키워드
			or content like 키워드)
			
			SELECT * FROM reservation
			WHERE package_name LIKE '키워드'
			OR package_region LIKE '키워드'
			OR package_city LIKE '키워드'
			OR package_theme LIKE '키워드'
			
			
			2. 날짜로 검색
			 1) 출발날짜만 지정
			 2) 도착날짜만 지정
			 3) 출발-도착날짜 사이
			
			3. 인원으로 검색
			 2) 상세상품의 남은인원보다 작거나 같을 경우
			
			SELECT c.package_category_code, c.package_category_name, c.package_category_image,
				c.package_category_image, c.package_category_content
			FROM package_product p
			JOIN package_category c
			ON p.package_category_code = c.package_category_code
			WHERE p.package_product_depart_date > 검색창날짜(오늘)
			AND package_product_current < package_product_total  // 
			---여기까지 기본 리스트-------
			-------키워드 검색-------
			AND c.package_category_name LIKE '%검색키워드%'
			AND c.package_category_content LIKE '%검색키워드%'
			---날짜 검색---
			AND p.package_product_arriv_date <= '검색창도착날짜'
			-- 인원 지정----
			AND package_product_total - package_product_current >= '지정인원'
			// 기본 리스트의 마지막줄 대신
			 * 
			 * 
			1 and 2
			
			2 and 3
			
			1 and 3
				
			1 and 2 and 3
			
			상세페이지 + 날짜 검색
			
			------------------------------------------
			별점
			1. 리뷰 table 에 컬럼으로 저장
			
				trigger, veiw 지원 안됨
			2. 1) 상품 join 해당상품코드로 리뷰테이블에서 별점평균 avg(별점)
			   2) 상품테이블에 별점평균 컬럼 추가, 리뷰글 등록 때마다 해당 컬럼 update
				=> 1) join으로 마음의 결정
			3. 상품당 리뷰갯수도 비슷한 방법
		 */
		
		
		CategorySearchListService categorySearchListService = new CategorySearchListService();
		int listCount = categorySearchListService.getListCount(keyword);

		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		categoryList = categorySearchListService.getCategoryList(page, limit, keyword);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		//SearchBean
		SearchBean searchBean = new SearchBean(keyword,depart_date, arriv_date,region,city);
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("searchBean", searchBean);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("categoryList", categoryList);

		forward = new ActionForward();
		forward.setPath("/product/categorySearchList.jsp");

		return forward;
	}

}
