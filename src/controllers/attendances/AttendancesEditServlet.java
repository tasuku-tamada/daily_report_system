package controllers.attendances;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesEditServlet
 */
@WebServlet("/attendances/edit")
public class AttendancesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Attendance a = em.find(Attendance.class, Integer.parseInt(request.getParameter("id")));

        em.close();
        if(a != null) {
            request.setAttribute("attendance", a);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("attendance_id", a.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/edit.jsp");
        rd.forward(request, response);
    }

}
