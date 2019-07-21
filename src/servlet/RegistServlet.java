package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.RegistLogic;
import logic.User;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		if (name != null && name.length() != 0) {
			if (pass != null && pass.length() != 0) {
				User user = new User(name, pass);
				RegistLogic registLogic = new RegistLogic();
				boolean result = registLogic.execute(user);

				HttpSession session = request.getSession();
				session.setAttribute("result", result);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registResult.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMsg", "パスワードが入力されていません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("errorMsg", "ユーザー名が入力されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
			dispatcher.forward(request, response);
		}
	}
}
