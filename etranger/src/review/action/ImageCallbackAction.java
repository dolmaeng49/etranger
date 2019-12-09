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

public class ImageCallbackAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String realFolder = "";
		String saveFolder = "/reviewUpload";
		int fileSize = 10 * 1024 * 1024;
		String fileName = "";

		ServletContext context = request.getServletContext(); // WAS 객체 가져와서 실제 폴더위치 찾기
		realFolder = context.getRealPath(saveFolder); // 가상폴더를 기준으로 실제경로 알아냄
//		System.out.println(realFolder);

		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",  // 여기서 객체 생성시, 파일 저장됨.
				new DefaultFileRenamePolicy());
		

		fileName = multi.getOriginalFileName((String) multi.getFileNames().nextElement());
		
		
		
		
		
//		Enumeration files = multi.getFileNames();
//		String file = (String) files.nextElement();
		
//		fileName = multi.getFilesystemName(file);
//		System.out.println("파일이름: " + fileName);
//		String uploadPath = "/reviewUpload/" + fileName;
//		System.out.println("업로드패스: " + uploadPath);
//
//		UUID uuid = UUID.randomUUID();
//		String org_filename = multi.getOriginalFileName((String) multi.getFileNames().nextElement());
//		String str_filename = uuid.toString() + org_filename;
//		System.out.println("원본 파일명 : " + org_filename);
//		System.out.println("저장할 파일명 : " + str_filename);
//
//		File saveFile = new File(realFolder + "/" + str_filename);
//		System.out.println("saveFile: " + saveFile);
//		
//		
//		
//		File outputFile = new File(realFolder);
//		URL url = null;
//		BufferedImage bi = null;
//		
//		try {
//			url = new URL(fileName);
//			bi = ImageIO.read(url);
//			ImageIO.write(bi, null, saveFile); // 파일포맷 파라미터 자리에 null값 줘서 그대로 유지.
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
//		JSONObject jobj = new JSONObject();
//		jobj.put("url", saveFile);
//
//		PrintWriter out = response.getWriter();
//		out.print(jobj.toJSONString());

		return null;
	}

}
