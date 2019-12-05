package product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import product.svc.WishListService;

public class AddWishList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// request 에서 세션가져오기
		HttpSession session =  request.getSession();
		// 세션에서 로그인한 회원의 ID 가져오기
		String member_id = (String)session.getAttribute("member_id");
		// 찜 버튼을 클릭한 상품(카테고리) category_code 가져오기
		String category_code = request.getParameter("category_code");
		/*  wish 테이블 수정
			product_num => category_code
			외래키도 같이 바꾸기 */
		// 서비스 클래스의 addWishList 메서드 호출하여 member_id 와 category_code 전달
		WishListService wishListService = new WishListService();
		boolean isSuccess = wishListService.addWishList(member_id, category_code);
		
		if(isSuccess) { // 성공한 경우
			// 찜목록에 추가되었습니다! 메세지 출력
			
			
			// 세션의 찜목록 업데이트
			session.setAttribute("member_wishList", wishListService.getMemberWishList(member_id));
		} else { // 실패한 경우
			// 찜목록추가에 실패했습니다! 메세지 출력
			
		}
		
	
		return null;
	}

}
