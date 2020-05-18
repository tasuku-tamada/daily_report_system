package controllers.attendances;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

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
 * Servlet implementation class AttendancesIndexServlet
 */
@WebServlet("/attendances/index")
public class AttendancesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendancesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Attendance> attendances;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try{
            date = sdf.parse(request.getParameter("date"));
            //翌日をのDateを取得する(=に変更したため不要)
            //Calendar calendar = Calendar.getInstance();
            //calendar.setTime(dateBegin);
            //calendar.add(Calendar.DAY_OF_MONTH, 1);
            //java.util.Date dateEnd = calendar.getTime();

            //指定した日付のレコードを取得
            attendances = em.createNamedQuery("getDateAttendances",Attendance.class)
                    .setParameter("dateBegin",date)
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();
        }
        catch(Exception e){
            attendances = em.createNamedQuery("getAllAttendances", Attendance.class)
                                      .setFirstResult(15 * (page - 1))
                                      .setMaxResults(15)
                                      .getResultList();
            date = null;
        }


        long attendances_count = (long)em.createNamedQuery("getAttendancesCount", Long.class)
                                     .getSingleResult();
        List<java.util.Date> dates = em.createNamedQuery("getDates",java.util.Date.class)
                .getResultList();

        em.close();
        request.setAttribute("select_date", date);
        request.setAttribute("dates",dates);
        request.setAttribute("attendances", attendances);
        request.setAttribute("attendances_count", attendances_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/index.jsp");
        rd.forward(request, response);   }

}
