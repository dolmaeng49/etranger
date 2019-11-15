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
import manager.action.CategoryListAction;
import manager.action.CityInsertAction;
import manager.action.CitySelectAction;
import manager.action.CategoryInsertAction;
import manager.action.ThemeInsertAction;
import manager.action.RegionInsertAction;
import manager.action.RegionSelectAction;
import manager.action.ThemeCheckBoxAction;
import manager.action.ThemeListAction;

@WebServlet("*.ma")
public class ManagerFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getServletPath();

		Action action = null;
		ActionForward forward = null;

		System.out.println(command);

		// manager_main.jsp 페이지 이동
		if (command.equals("/ManagerMain.ma")) {
			action = new CategoryListAction();

			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ManagerInsertProAction 클래스로 이동
		else if (command.equals("/RegionInsert.ma")) {
			action = new RegionInsertAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// RegionSelectAction 클래스로 이동
		else if (command.equals("/RegionSelect.ma")) {
			action = new RegionSelectAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// CityInsertAction 클래스로 이동
		else if (command.equals("/CityInsert.ma")) {
			action = new CityInsertAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// CitySelectAction 클래스로 이동
		else if (command.equals("/CitySelect.ma")) {
			action = new CitySelectAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ManagerThemeInsertAction 클래스로 이동
		else if (command.equals("/ThemeInsert.ma")) {
			action = new ThemeInsertAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ThemeCheckBoxAction 클래스로 이동
		else if (command.equals("/ThemeCheckBox.ma")) {
			action = new ThemeCheckBoxAction();
			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/CategoryInsert.ma")) {
			action = new CategoryInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
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
