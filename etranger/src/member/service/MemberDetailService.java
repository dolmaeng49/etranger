package member.service;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

public class MemberDetailService {

	public MemberBean getArticle(String member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean article = memberDAO.selectMember(member_id);
//		System.out.println(article.getMember_id());
		
		close(con);
		
		return article;
	}

}
