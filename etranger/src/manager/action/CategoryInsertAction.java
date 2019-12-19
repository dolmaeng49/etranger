package manager.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryInsertService;
import manager.vo.CategoryBean;

public class CategoryInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		CategoryBean cb = null;

//		System.out.println("CategoryInsertAction!");
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
		
		int category_citycode = Integer.parseInt(request.getParameter("category_citycode"));
		int category_regioncode = Integer.parseInt(request.getParameter("category_regioncode"));
		String category_content = request.getParameter("category_content");
		cb.setPackage_category_city(category_citycode);
		cb.setPackage_category_region(category_regioncode);
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
		
		// 테마 처리
		String addTheme = "";
		String s = "!";
		for (int i = 0; i < request.getParameterValues("theme").length; i++) {

			addTheme += (s + (request.getParameterValues("theme")[i]));
		}

		CategoryInsertService cis = new CategoryInsertService();
		boolean isInsertSuccess = cis.InsertCategory(cb, addTheme);

		if (!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품분류 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("ManagerMain.ma");
			forward.setRedirect(true);
		}

		return forward;
	}

}
