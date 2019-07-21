package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.DeleteMutterLogic;
import logic.GetMutterListLogic;
import logic.Mutter;
import logic.User;

@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String radio = request.getParameter("radio");

		if (radio != null && radio.length() != 0) {
			String name = request.getParameter("name"+radio);
			String text = request.getParameter("text"+radio);
			String time = request.getParameter("time"+radio);
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if(!loginUser.getName().equals(name)) {
				request.setAttribute("errorMsg", "他の人のつぶやきです");
			} else {
				Mutter mutter = new Mutter(name, text, time);
				DeleteMutterLogic deleteMutterLogic = new DeleteMutterLogic();
				deleteMutterLogic.execute(mutter);
			}
		} else {
			request.setAttribute("errorMsg", "つぶやきが選択されていません");
		}

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}