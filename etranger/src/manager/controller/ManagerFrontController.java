package manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.action.ManagerMainAction;
import manager.action.CityInsertAction;
import manager.action.CitySelectAction;
import manager.action.ProductListAction;
import manager.action.ReceiveEmailAction;
import manager.action.DeleteProductAction;
import manager.action.GetThemeListAjaxAction;
import manager.action.IsReservUpdateAction;
import manager.action.ManagerImageCallbackAction;
import manager.action.ProductDetailAction;
import manager.action.ProductInsertAction;
import manager.action.CategoryListAction;
import manager.action.CategoryUpdateAction;
import manager.action.ChangeMostAction;
import manager.action.CategoryDeleteAction;
import manager.action.CategoryInsertAction;
//import manager.action.ProductListAction;
import manager.action.ThemeInsertAction;
import manager.action.RegionInsertAction;
import manager.action.RegionSelectAction;
import manager.action.ReservManagementAction;
import manager.action.ThemeCheckBoxAction;
import manager.action.ThemeListAction;
import reservation.action.ReservDeleteAction;
import reservation.action.ReservUpdateAction;
import review.action.ImageCallbackAction;

@WebServlet("*.ma")
public class ManagerFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		String member_id = (String) request.getSession().getAttribute("member_id");

		// 관리자 권한 확인이 필요한 페이지
		if (member_id != null && member_id.equals("admin")) {
			// manager_main.jsp 페이지 이동
			if (command.equals("/ManagerMain.ma")) {
				action = new ManagerMainAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ManagerInsertProAction 클래스로 이동
			else if (command.equals("/RegionInsert.ma")) {
				action = new RegionInsertAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// CityInsertAction 클래스로 이동
			else if (command.equals("/CityInsert.ma")) {
				action = new CityInsertAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ManagerThemeInsertAction 클래스로 이동
			else if (command.equals("/ThemeInsert.ma")) {
				action = new ThemeInsertAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// CategoryInsertAction 클래스로 이동
			else if (command.equals("/CategoryInsert.ma")) {
				action = new CategoryInsertAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// CategoryInsertAction 클래스로 이동
			else if (command.equals("/CategoryUpdate.ma")) {
//				System.out.println("CategoryUpdate controller");
				action = new CategoryUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			else if (command.equals("/CategoryDelete.ma")) {
//				System.out.println("CategoryUpdate controller");
				action = new CategoryDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (command.equals("/ProductInsert.ma")) {
				forward = new ActionForward();
				forward.setPath("/manager/manager_product_insert.jsp");
			}

			// CategoryInsertAction 클래스로 이동
			else if (command.equals("/ProductInsertPro.ma")) {
				action = new ProductInsertAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ProductDetailAction
			else if (command.equals("/ProductDetail.ma")) {
				action = new ProductDetailAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 카테고리 리스트
			else if (command.equals("/CategoryList.ma")) {
				action = new CategoryListAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 상품 삭제
			else if (command.equals("/DeleteProduct.ma")) {
				action = new DeleteProductAction();
//				System.out.println("DeleteProduct.ma");
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 상품 리스트
			else if (command.equals("/ProductList.ma")) {
				action = new ProductListAction();
//				System.out.println("ProductList.ma");
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (command.equals("/ReservManagement.ma")) {
				action = new ReservManagementAction();

				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

//				forward = new ActionForward();
//				forward.setPath("/manager/manager_member.jsp");
			}

			else if (command.equals("/ReservDelete.ma")) {
				action = new ReservDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (command.equals("/ReservUpdate.ma")) {
				action = new ReservUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (command.equals("/IsReservUpdate.ma")) {
				action = new IsReservUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/ManagerImageCallback.ma")) {
				action = new ManagerImageCallbackAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ------------------ datachart ---------------------
			else if (command.equals("/ChangeAge.ma")) {
				action = new ChangeMostAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		// 관리자 권한이 필요없는 페이지
		// RegionSelectAction 클래스로 이동
		if (command.equals("/RegionSelect.ma")) {
			action = new RegionSelectAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// CitySelectAction 클래스로 이동
		else if (command.equals("/CitySelect.ma")) {
			action = new CitySelectAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ThemeCheckBoxAction 클래스로 이동
		else if (command.equals("/ThemeCheckBox.ma")) {
			action = new ThemeCheckBoxAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//side bar, footer에 뿌려줄 용도의 Action. Limit 10
		}else if (command.equals("/GetThemeListAjax.ma")) {
			action = new GetThemeListAjaxAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/ContactEmail.ma")) {
			action = new ReceiveEmailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
