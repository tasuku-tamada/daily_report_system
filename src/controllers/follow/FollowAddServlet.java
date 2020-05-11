package controllers.follow;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follower;
import utils.DBUtil;

/**
 * Servlet implementation class FollowAddServlet
 */
@WebServlet("/follow/add")
public class FollowAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowAddServlet() {
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

            Follower f = new Follower();

            Integer follower_id = Integer.parseInt(request.getParameter("follower_id"));
            Integer follow_id = Integer.parseInt(request.getParameter("follow_id"));

            Employee follower_employee = em.find(Employee.class, follower_id);
            Employee follow_employee = em.find(Employee.class, follow_id);

            f.setFollower_employee(follower_employee);
            f.setFollow_employee(follow_employee);


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            f.setCreate_at(currentTime);

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();
            response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("report_id"));
        }
    }

}
