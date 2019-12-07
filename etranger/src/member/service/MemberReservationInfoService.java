package member.service;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import reservation.vo.ReservationInfoBean;

public class MemberReservationInfoService {

	public ReservationInfoBean MemberReservationInfo(String id) {
		System.out.println("/MemberReservationService.me");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);

		ReservationInfoBean rib = memberDAO.MemberReservationInfo(id);

		close(con);
		System.out.println("rib headcount" + " " + rib.getReservation_headcount());

		return rib;

	}

}
