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

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCustomerSearch
 */
@WebServlet("/reports/customer_search")
public class ReportsCustomerSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCustomerSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Integer customer_id = Integer.parseInt(request.getParameter("id"));
        List<Report> reports = em.createNamedQuery("getCustomerReports",Report.class)
                .setParameter("customer_id", customer_id)
                .getResultList();
        Long reports_count = em.createNamedQuery("getCustomerReportsCount",Long.class)
                .setParameter("customer_id", customer_id)
                .getSingleResult();
        em.close();

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
    }
}

