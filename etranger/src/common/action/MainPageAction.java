package common.action;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.vo.ActionForward;
import manager.svc.CategoryListService;
import manager.vo.CategoryBean;
import member.service.MemberLoginProService;
import member.vo.MemberBean;
import review.service.ReviewListService;
import review.vo.ReviewBean;

public class MainPageAction implements Action {

	@Override
	// 메인페이지가 로드될 때 호출되는 액션클래스 메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 카테고리 리스트 서비스 객체 생성
		CategoryListService categoryListService = new CategoryListService();
		
		// 인기순 리스트
		ArrayList<CategoryBean> categoryList = categoryListService.getPopularList(1, 8);
		request.setAttribute("popularList", categoryList);
		
		// 추천순 리스트 
		categoryList = categoryListService.getRecommendedList(1, 3);
		request.setAttribute("recommendedList", categoryList);
		
		// 세션 객체 받아와 세션에서 로그인한 회원 정보 가져오기
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		// 동그라미 4개에 출력할 카테고리 리스트 4개 조회하기
		// 로그인상태일 경우 회원의 연령대, 성별 정보 활용해 추천
		if(member_id != null && !member_id.equals("admin")) {
			MemberLoginProService memberLoginProService = new MemberLoginProService();
			// member_name, member_email, member_birth, member_gender, member_grade 5 개의 정보 저장되어 있음
			MemberBean memberBean = memberLoginProService.getMemberInfo(member_id);
			// 나이에 따른 birth 검색 구간 설정
			// 오늘 날짜(long) 에서 회원의 생일(long) 을 빼서 나이를 계산
			LocalDate birth_date = LocalDate.parse(memberBean.getMember_birth());
			LocalDate now = LocalDate.now();
			LocalDate age_date = LocalDate.ofEpochDay(LocalDate.now().toEpochDay() - birth_date.toEpochDay());
			// EpochDay : 1970년 1월 1일 을 기준으로 이전, 이후의 시간(초)을 long 타입으로 표현 
			int age = age_date.getYear() - 1970;
			String search_birth_start = "";
			String search_birth_end = "";
			String gender = memberBean.getMember_gender();
			byte ageRange = 0;
			if(age < 30) { // 20대
				// 현재로부터 20년 전 날짜 부터 30년 전 날짜를 저장
				search_birth_start = now.minusYears(30).toString();
				search_birth_end = now.minusYears(20).toString();
				ageRange = 20;
			} else if(age < 40) {
				search_birth_start = now.minusYears(40).toString();
				search_birth_end = now.minusYears(30).toString();
				ageRange = 30;
			} else if(age < 50) {
				search_birth_start = now.minusYears(50).toString();
				search_birth_end = now.minusYears(40).toString();
				ageRange = 40;
			} else if(age < 60) {
				search_birth_start = now.minusYears(60).toString();
				search_birth_end = now.minusYears(50).toString();
				ageRange = 50;
			} else {
				search_birth_start = now.minusYears(200).toString();
				search_birth_end = now.minusYears(60).toString();
				ageRange = 60;
			}
			product.svc.CategoryListService proCateSVC = new product.svc.CategoryListService();
			categoryList = proCateSVC.getPersonalizedList(1, 4, gender, search_birth_start, search_birth_end);
			StringBuffer newList_name = new StringBuffer();
			newList_name.append(ageRange).append(" 대 ").append(gender.equalsIgnoreCase("m") ? '남' : '여').append("성이 많이 구매한 상품");
			
			request.setAttribute("newList", categoryList);
			request.setAttribute("newList_name", newList_name.toString());
		} else {
			// 로그인 상태가 아닐 경우
			categoryList = categoryListService.getNewList(1, 4);
			request.setAttribute("newList", categoryList);
			request.setAttribute("newList_name", "Most Popular Destination");
		}
		
		// 리뷰 5개 조회
		ReviewListService reviewListService = new ReviewListService();
		ArrayList<ReviewBean> reviewList = reviewListService.getArticleList(1, 5);
		request.setAttribute("reviewList", reviewList);
		
		return null;
	}

}
