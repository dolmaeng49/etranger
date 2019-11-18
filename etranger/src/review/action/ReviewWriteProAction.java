package review.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewWriteProService;
import review.vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewWriteProAction");
		
		ActionForward forward = null;
		
		ReviewBean rb = null;
		
		String realFolder=""; 
		String saveFolder="/reviewUpload";
		int fileSize = 10*1024*1024;
		String fileName="";
		
		//클라이언트 ip 추출 
//		String writerIpAddr = request.getRemoteAddr();
//		System.out.println(writerIpAddr);
		
		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄
		System.out.println(realFolder);
		
		
		
		//MultiPartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
		
		
		fileName=multi.getOriginalFileName((String) multi.getFileNames().nextElement());
		Enumeration files = multi.getFileNames();
		String file = (String)files.nextElement(); 
//		fileName = multi.getFilesystemName(file);
		System.out.println("파일이름: "+fileName);
		String uploadPath = "/reviewUpload/"+fileName;
		System.out.println("업로드패스: "+uploadPath);
		
		UUID uuid = UUID.randomUUID();
		String org_filename = multi.getOriginalFileName((String) multi.getFileNames().nextElement());
//		String str_filename = uuid.toString() + org_filename;
		String str_filename = uuid.toString();		//UUID값만 준 랜덤닉네임
		System.out.println("원본 파일명 : " + org_filename);
		System.out.println("저장할 파일명 : " + str_filename);
		
		
		File saveFile = new File(realFolder +"/"+ str_filename); //실제 주소 세이브파일
		File saveFile2 = new File(saveFolder +"/"+ str_filename); //가상주소 세이브파일
		
		System.out.println("saveFile: "+saveFile);
//		File outputFile = new File(realFolder);
		URL url = null;
		BufferedImage bi = null;
		try {
			url = new URL(str_filename);
			bi= ImageIO.read(url);
			ImageIO.write(bi, null, saveFile); //렌더링, 포맷, 저장할 주소 순. //파일포맷 파라미터에 null값 줘서 확장자 그대로 유지.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("url", saveFile2);
		
		response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
		PrintWriter out = response.getWriter();
		out.print(jobj.toJSONString());



		
		
		
		
		
//		JSONObject jobj = new JSONObject();
//		System.out.println("json 전달값: " +jobj);
//		jobj.put("url", uploadPath);
		
//		response.setContentType("application/json"); // 데이터 타입을 json으로 설정
//		PrintWriter out = response.getWriter();
//		out.print(jobj.toJSONString());

		
		
		
		
		
		
		//ReviewBean 객체 생성
		
		rb= new ReviewBean();
		rb.setReview_member_id(multi.getParameter("review_member_id"));
		rb.setReview_subject(multi.getParameter("review_subject"));
		rb.setReview_content(multi.getParameter("review_content"));
//		rb.setReview_image(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		rb.setReview_package_catagory_code(multi.getParameter("review_package_category_code"));
		rb.setReview_member_name(multi.getParameter("review_member_name"));
//		rb.setReview_star(Integer.parseInt(multi.getParameter("review_star")));
		
		
		
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		boolean isWriteSuccess =reviewWriteProService.registArticle(rb);
		response.setContentType("text/html; charset=UTF-8"); // 문서 타입(contentType) 지정
		
		PrintWriter out2 = response.getWriter();
		
		if(!isWriteSuccess){
			out2.println("<script>");
			out2.println("alert('리뷰 등록 실패!')");
			out2.println("history.back()");
			out2.println("</script>");
		}else {
			forward= new ActionForward();
			out2.println("<script>");
			out2.println("alert('리뷰 등록 완료!')");
			out2.println("</script>");
			forward.setPath("ReviewList.rv");
			forward.setRedirect(true); 
		}
		
		return forward;
	}

}
