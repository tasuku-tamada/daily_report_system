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
            Employee follower =  (Employee)request.getSession().getAttribute("login_employee");

            Integer follower_id = follower.getId();
            Integer follow_id = Integer.parseInt(request.getParameter("follow_id"));

            f.setFollower_id(follower_id);
            f.setFollow_id(follow_id);


            Timestamp follow_Time = new Timestamp(System.currentTimeMillis());

            f.setFollow_at(follow_Time);

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            em.close();
            response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("report_id"));
        }
    }

}
