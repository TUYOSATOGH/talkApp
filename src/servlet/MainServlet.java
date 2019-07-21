package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.GetMutterListLogic;
import logic.Mutter;
import logic.PostMutterLogic;
import logic.User;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = (List<Mutter>) getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		HttpSession session = request.getSession();
		User loginUesr = (User) session.getAttribute("loginUser");
		if (loginUesr == null) {
			response.sendRedirect("/talkApp/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		if (text != null && text.length() != 0) {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
	        Calendar cTime = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			Mutter mutter = new Mutter(loginUser.getName(), text, sdf.format(cTime.getTime()));
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);
		} else {
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
