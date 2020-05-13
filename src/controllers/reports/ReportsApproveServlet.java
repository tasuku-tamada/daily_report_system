package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportApprove
 */
@WebServlet("/reports/approve")
public class ReportsApproveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsApproveServlet() {
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
            Report r = em.find(Report.class,report_id);
            r.setApproval_flag(1);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            response.sendRedirect(request.getContextPath()+"/reports/show?id="+ report_id);
        }
    }

}
