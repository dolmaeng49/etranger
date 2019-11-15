package manager.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspApplicationContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryInsertService;
import manager.svc.RegionInsertService;
import manager.vo.CategoryBean;

public class CategoryInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		CategoryBean cb = null;
		
		String realFolder = ""; // 실제 경로
		String saveFolder = "/ManagerImgUpload"; // 가상 경로(=> 이클립스에서 보이는 폴더 구조)
		int fileSize = 10 * 1024 * 1024; // 10MB 크기 지정
		
		// 현재 서블릿을 처리하는 서버의 객체를 가져와서 실제 폴더 위치 알아내기
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder); // 프로젝트 상의 가상폴더를 기준으로 실제 경로 알아내기
//		System.out.println(realFolder);
		
		// 파일 업로드 처리를 위해 MultiPartRequest 객체 생성 => cos.jar 필요
		MultipartRequest multi = new MultipartRequest(
				request, // request 객체
				realFolder, // 실제 업로드 폴더 경로
				fileSize, // 파일 크기
				"UTF-8", // 한글 파일명에 대한 인코딩 방식 지정
				new DefaultFileRenamePolicy() // 동일한 이름의 파일에 대한 처리
		);
		
	
		// 전달받은 데이터를 저장할 CategoryBean 객체생성
		cb = new CategoryBean();

		int category_citycode = Integer.parseInt(multi.getParameter("category_citycode"));
		int category_regioncode = Integer.parseInt(multi.getParameter("category_regioncode"));
		cb.setPackage_category_city(category_citycode);
		cb.setPackage_category_region(category_regioncode);
		cb.setPackage_category_name(multi.getParameter("category_name"));
		cb.setPackage_category_content(multi.getParameter("category_content"));
		cb.setPackage_category_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		String addTheme = "";
		String s = "#";
		for (int i = 0; i < multi.getParameterValues("theme").length; i++) {

			addTheme += (s + (multi.getParameterValues("theme")[i]));

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
		}

		return forward;
	}

}
