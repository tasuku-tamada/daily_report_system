package controllers.attendances;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import models.validators.AttendanceValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesCreateServlet
 */
@WebServlet("/attendances/create")
public class AttendancesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Attendance a = new Attendance();

            Employee loginEmployee = (Employee)request.getSession().getAttribute("login_employee");

            a.setEmployee(loginEmployee);

            Date date = Date.valueOf(request.getParameter("date"));

            a.setDate(date);

            a.setAttendance_time(request.getParameter("attendance_time"));
            a.setLeave_time(request.getParameter("leave_time"));

            List<String> errors = AttendanceValidator.validate(a,loginEmployee);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("attendance", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/attendances/index");
            }
        }
    }
}