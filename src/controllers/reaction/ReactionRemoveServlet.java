package controllers.reaction;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Reaction;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReactionRemoveServlet
 */
@WebServlet("/reaction/remove")
public class ReactionRemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReactionRemoveServlet() {
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

            Integer report_id = Integer.parseInt(request.getParameter("report_id"));

            Report report = em.find(Report.class, report_id);
            Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");


            //ログイン中のユーザーがフォロワーのフォローIDを取得
            Reaction reaction = em.createNamedQuery("getReaction",Reaction.class)
                    .setParameter("employee",login_employee )
                    .setParameter("report",report )
                    .setParameter("type",0)
                    .getSingleResult();


            if(reaction != null){
                em.getTransaction().begin();
                em.remove(reaction);
                em.getTransaction().commit();
            }
            em.close();
            response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("report_id"));
        }
    }

}
