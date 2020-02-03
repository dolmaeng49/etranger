package manager.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryUpdateService;
import manager.vo.CategoryBean;
import reservation.service.ReservUpdateService;
import review.service.ReviewModifyProService;

public class CategoryUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		CategoryBean cb = null;
//		String realFolder = ""; // 실제 경로
//		String saveFolder = "/ManagerImgUpload"; // 가상 경로
//		int fileSize = 100 * 1024 * 1024; // 100MB 크기 지정
//		ServletContext context = request.getServletContext();// 실제 폴더 위치
//		realFolder = context.getRealPath(saveFolder); // 실제 경로
//		MultipartRequest multi = new MultipartRequest(request, // request 객체
//				realFolder, // 실제 업로드 폴더 경로
//				fileSize, // 파일 크기
//				"UTF-8", // 한글 파일명에 대한 인코딩 방식 지정
//				new DefaultFileRenamePolicy() // 동일한 이름의 파일에 대한 처리
//		);
		
		// 전달받은 데이터를 저장할 CategoryBean 객체생성
		cb = new CategoryBean();
		String category_content = request.getParameter("category_content");
		String package_category_code = request.getParameter("category_code");
		String package_category_theme= request.getParameter("category_theme");
		cb.setPackage_category_code(package_category_code);
		cb.setPackage_category_name(request.getParameter("category_name"));
		cb.setPackage_category_content(category_content);
		
		
		// summernote 로 업로드된 이미지 파일명 content 에서 추출
		String[] content_splited = category_content.split("ManagerImgUpload/");
		StringBuilder image = new StringBuilder();
		// split()으로 나눈 스트링 배열의 짝수번째(구분자의 뒤쪽)만 반복 접근
		for(int i = 1; i < content_splited.length; i++) {
			if(i > 1) { // 두번째 이미지 파일명 부터는 사이에 구분자 삽입
				image = image.append('*');
			}
			image =  image.append(content_splited[i].substring(0, content_splited[i].indexOf('"')));
		}
		cb.setPackage_category_image(image.toString());

		CategoryUpdateService categoryUpdateService = new CategoryUpdateService();
		boolean isUpdateSuccess = categoryUpdateService.updateCategory(cb);
		
		if (!isUpdateSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!');");
			out.println("history.back()");
			out.println("</script>");

		} else {
			forward = new ActionForward();


			String path= "ProductDetail.ma?package_category_code="+package_category_code+"&package_category_theme=" + package_category_theme;
			forward.setPath(path);
			
//			ProductDetail.ma?package_category_code="
//					+ productList.get(i).getPackage_category_code() + "&package_category_theme="
			
		}
		
		
		return forward;
	}

}
