package common.db;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import manager.vo.ProductBean;
import reservation.service.ReservationInsertService;
import reservation.vo.ReservationBean;

public class InsertProductData {
	// insertMembers() 로 생성한 회원정보 지우기 (리뷰, 예약정보 포함)
	/*
	 => DELETE FROM review WHERE review_member_id LIKE 'test%';
		DELETE FROM reservation WHERE reservation_member_id LIKE 'test%';
		DELETE FROM member WHERE member_id LIKE 'test%';
	*///순서대로
	 
	// insertProducts() 는 모두 지우는 방법 뿐임,, 
	// => 아니면 관리자 페이지에서 수동으로,,
	
	
	

	// package_product 의 데이터를 임의로 생성하는 메서드
	// packate_category 의 데이터가 미리 입력되어 있어야 함
	public int insertProducts() {
		
		// 커넥션풀에서 커넥션 가져오기
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Random random = new Random();
		// category 테이블의 모든 category_code 를 조회
		String sql = "SELECT package_category_code from package_category";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			long min_date = Date.UTC(2017-1900, 12-1, 1, 0, 0, 0);
			long max_date = Date.UTC(2020-1900, 3-1, 31, 0, 0, 0);
			int i = 1;
			// 조회한 카테고리 코드 하나마다 반복 수행
			while (rs.next()) {
				System.out.println(i++ + "번째 package_category 작업중...");
				String category_code = rs.getString(1);
				// 카테고리 하나에 생성할 프로덕트 수
				int productCount = 5 + random.nextInt(15);
				// 중복없이 5~20개의 날짜 생성
				Set<Date> depart_dates = new HashSet<Date>();
				while (depart_dates.size() < productCount) {
					// 2017.12.1 ~ 2020.3.31 사이의 날짜(long) 생성
					depart_dates.add(new Date(min_date + (long)(random.nextDouble() * (max_date - min_date))));
				}
				// Set -> Iterator 형변환
				Iterator<Date> itr = depart_dates.iterator();
				// 패키지 기간 4 ~ 9 일
				int days = 4 + random.nextInt(5);
				
				// 카테고리 하나당 5 ~ 20 개의 프로덕트 생성
				while (itr.hasNext()) {
					Date depart_date = itr.next();
					LocalDate arriv_date = depart_date.toLocalDate().plusDays(days);
					sql = "INSERT INTO package_product VALUES (?,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, category_code + depart_date.toString());
					pstmt.setString(2, category_code);
					pstmt.setDate(3, depart_date);
					pstmt.setDate(4, new Date(arriv_date.getYear()-1900, arriv_date.getMonthValue()-1, arriv_date.getDayOfMonth()));
					// 30 ~ 180 만원 가격
					pstmt.setInt(5, (random.nextInt(150) + 30) * 10000);
					// 10 ~ 40 명 인원
					pstmt.setInt(6, random.nextInt(30) + 10);
					pstmt.setInt(7, 0);
//					System.out.println(pstmt);
					int insertProductCount = pstmt.executeUpdate();
					if(insertProductCount > 0) {commit(con);};
				}
			} 
			
		} catch (SQLException e) {
			System.out.println("insertProducts 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		commit(con);
		close(con);
		
		return 0;
	}
	
	// 파라미터로 전달받은 수 만큼의 더미 회원 정보 생성
	// 회원 id : test1 ~ testN
	// DB에 있는 프로덕트 예약, 리뷰 작성까지 All-in-One
	public int insertMembers(int numberOfMembers) {
		// 커넥션풀에서 커넥션 가져오기
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		int insertReviewCount = 0;
		int insertMemberCount = 0;
		int insertReservCount = 0;
		
		Random random = new Random();
		
		ArrayList<ProductBean> products = new ArrayList<ProductBean>(); 
		// 모든 프로덕트의 정보 ArrayList<ProductBean>에 저장
		sql = "SELECT package_product_num, package_category_code, package_product_depart_date,"
				+ "package_product_price, package_product_current, package_product_total"
				+ " FROM package_product";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductBean pb = new ProductBean();
				pb.setProductNum(rs.getString(1));
				pb.setCategoryCode(rs.getString(2));
				pb.setProductDepartDate(rs.getString(3));
				pb.setProductPrice(rs.getInt(4));
				pb.setProductCurrent(rs.getInt(5));
				pb.setProductTotal(rs.getInt(6));
				products.add(pb);
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		String[] firstNames = {
				"김","김","김","박","박","서","손","송","신","오","우",
				"이","이","장","전","조","최","하","홍"
		};
		String[] lastNames = {
				"동하","종우","준영","광오","정민","은지","여민","진섭","동명","동영","지희",
				"상윤","상협","영진","건","진우","수봉","헌우","은비"
		};
		
		for(int i = 0; i < numberOfMembers; i++) {
			if((i + 1) % 10 == 0) {System.out.println((i + 1) + "번째 회원 정보 생성중...");};
			// 회원 생성
			sql = "INSERT INTO member VALUES(?,?,?,?,NULL,NULL,NULL,?,?,?,?,now(),now(),?)";
			try {
				String member_id = "test" + (i + 1);
				String member_name = firstNames[random.nextInt(firstNames.length - 1)]
						+ lastNames[random.nextInt(lastNames.length - 1)];
				StringBuffer birth = new StringBuffer();
				double random_age = random.nextDouble();
				if(random_age < 0.2) { // 20대
					birth = birth.append(19).append((90 + random.nextInt(9))).append("-12-10");
				} else if(random_age < 0.5) { // 30대
					birth = birth.append(19).append((80 + random.nextInt(10))).append("-12-10");
				} else if(random_age < 0.8) { // 40대
					birth = birth.append(19).append((70 + random.nextInt(9))).append("-12-10");
				} else if(random_age < 0.95) { // 50대
					birth = birth.append(19).append((60 + random.nextInt(9))).append("-12-10");
				} else { // 60대 이상
					birth = birth.append(19).append((50 + random.nextInt(9))).append("-12-10");
				}
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setString(2, "1234");
				pstmt.setString(3, member_name);
				pstmt.setString(4, "아이티윌부산");
				pstmt.setString(5, "01022673405");
				pstmt.setString(6, "etrangermanager@gmail.com");
				pstmt.setString(7, birth.toString());
				pstmt.setString(8, random.nextBoolean() == true ? "m" : "f");
				pstmt.setString(9, "test_acount");
//				System.out.println(pstmt);
				insertMemberCount += pstmt.executeUpdate();
				
				// 예약하기 & 리뷰쓰기
				// 예약할 프로덕트 개수
				int reservCount = random.nextInt(6);
				// 존재하는 프로덕트 개수가 적을 경우
				reservCount = reservCount > products.size() ? products.size() : reservCount; 
				Set<Integer> index_set = new HashSet<Integer>();
				// 예약할 개수 만큼의 인덱스 생성
				while(index_set.size() < reservCount) {
					index_set.add(random.nextInt(products.size()));
				}
				Iterator<Integer> itr = index_set.iterator();
				for(int k = 0; k < reservCount; k++) {
					int index = itr.next();
					sql = "INSERT INTO reservation VALUES(null,?,?,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member_id);
					pstmt.setString(2, products.get(index).getProductNum());
					pstmt.setString(3, products.get(index).getCategoryCode());
					// 예약날짜는 출발날짜의 7일 전으로 설정
					pstmt.setString(4, LocalDate.parse(products.get(index).getProductDepartDate()).minusDays(7).toString());
					// 예약 인원 1 ~ 5 명
					int headCount = 1 + random.nextInt(4);
					pstmt.setInt(5, products.get(index).getProductPrice() * headCount);
					pstmt.setInt(6, headCount);
					pstmt.setString(7, "Y");
					pstmt.setString(8, "결제완료");
//					System.out.println(pstmt);
					insertReservCount += pstmt.executeUpdate();
					
					// 리뷰쓰기 구매자의 30%만 리뷰 쓰도록
					if(random.nextDouble() < 0.3) {
						sql = "INSERT INTO review VALUES(?,?,?,?,?,?,now(),0,?,?,0)";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, 0);
						pstmt.setString(2, member_id);
						pstmt.setString(3, member_name);
						pstmt.setString(4, "Review Test Data");
						pstmt.setString(5, "bg_88.jpg");
						pstmt.setString(6, "Review Test Data");
						pstmt.setString(7, products.get(index).getCategoryCode());
						// 4 ~ 10 별점
						pstmt.setInt(8, 5 + random.nextInt(6));
//						System.out.println(pstmt);
						insertReviewCount += pstmt.executeUpdate();
					}
				}
				if(insertReviewCount > 0 && insertMemberCount > 0 && insertReservCount > 0) {commit(con);};
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
		}
		
		return 0;
	}

}
