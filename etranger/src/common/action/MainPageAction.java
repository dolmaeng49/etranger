package common.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.vo.ActionForward;
import manager.svc.CategoryListService;
import manager.vo.CategoryBean;
import review.service.ReviewListService;
import review.vo.ReviewBean;

public class MainPageAction implements Action {

	@Override
	// 메인페이지가 로드될 때 호출되는 액션클래스 메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 메인페이지에 출력까지 연결 완료
		 * CategoryListService, DAO 에서 추천순/인기순에 따른 메서드 작성 필요 
		 * 메서드 만들면 이 주석은 지워주세여
		 */
		

		// 카테고리 리스트 서비스 객체 생성
		CategoryListService categoryListService = new CategoryListService();
		// 동그라미 4개에 출력할 카테고리 리스트 4개 조회하기
		ArrayList<CategoryBean> categoryList = categoryListService.getCategoryList(1, 4);
		request.setAttribute("categoryList1", categoryList);
		
		// 꽉찬 네모 8개에 출력할 카테고리 리스트 8개 조회
		categoryList = categoryListService.getCategoryList(1, 8);
		request.setAttribute("categoryList2", categoryList);
		
		// 인기순 리스트
		ArrayList<CategoryBean> popularList = categoryListService.getpopularList(1, 8);
		request.setAttribute("popularList", popularList);
		
		// 추천순 리스트 
		ArrayList<CategoryBean> RecommendedList = categoryListService.getRecommendedList(1, 3);
		request.setAttribute("RecommendedList", RecommendedList);
		
		
		ReviewListService reviewListService = new ReviewListService();
		// 리뷰 5개 조회
		ArrayList<ReviewBean> reviewList = reviewListService.getArticleList(1, 5);
		request.setAttribute("reviewList", reviewList);
		
		return null;
	}

}
