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
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesShowServlet
 */
@WebServlet("/attendances/show")
public class AttendancesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee login_employee =  (Employee)request.getSession().getAttribute("login_employee");

        EntityManager em = DBUtil.createEntityManager();
        Attendance a = em.find(Attendance.class, Integer.parseInt(request.getParameter("id")));
        em.close();

        request.setAttribute("attendance", a);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("login_employee", login_employee);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/show.jsp");
        rd.forward(request, response);
      }
}
