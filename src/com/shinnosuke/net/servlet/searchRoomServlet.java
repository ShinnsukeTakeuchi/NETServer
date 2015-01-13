package com.shinnosuke.net.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinnosuke.net.daos.OneChatDao;

/**
 * Servlet implementation class searchRoomServlet
 */
@WebServlet("/SearchRoom")
public class searchRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		ServletOutputStream sos = response.getOutputStream();

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		response.setHeader("Access-Control-Max-Age", "-1");

		String value = request.getParameter("value");
		String userName = request.getParameter("userName");

		OneChatDao dao = new OneChatDao();

		try {
			if(userName != null && !userName.isEmpty()) {
				String roomId = dao.searchInRoom(userName);
				if(roomId != null && roomId.length()>=1){
					response.setStatus(HttpServletResponse.SC_OK);

					sos.println(roomId);
					sos.close();
//					out.println(roomId);
				}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				sos.println("Bad request!");
				sos.println("Not found user name");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
