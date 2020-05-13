package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        boolean followed = false;
        Employee login_employee =  (Employee)request.getSession().getAttribute("login_employee");

        //ログイン中のユーザーがフォロワーのフォローIDを取得
        List<Employee> follow_employees = em.createNamedQuery("getFollowEmployee",Employee.class)
                .setParameter("follower_id",login_employee.getId() )
                .getResultList();

        for(Employee follow_employee : follow_employees){
          //既にフォロー済みの場合
            if(r.getEmployee().getId() == follow_employee.getId()){
                followed =true;
                break;
            }
        }

        Long good_count = (Long)em.createNamedQuery("getReactionCount",Long.class)
                .setParameter("report", r)
                .setParameter("type", 0)
                .getSingleResult();
        Long my_good_count = (Long)em.createNamedQuery("getMyReaction",Long.class)
                .setParameter("employee", login_employee)
                .setParameter("report", r)
                .setParameter("type", 0)
                .getSingleResult();

        boolean liked = false;
        if(my_good_count > 0)
            liked = true;

        em.close();

        request.setAttribute("report", r);
        request.setAttribute("good_count", good_count);
        request.setAttribute("liked", liked);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("followed", followed);
        request.setAttribute("login_employee", login_employee);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
      }
}
