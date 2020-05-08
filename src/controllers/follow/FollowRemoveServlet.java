package controllers.follow;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follower;
import utils.DBUtil;

/**
 * Servlet implementation class FollowRemoveServlet
 */
@WebServlet("/follow/remove")
public class FollowRemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowRemoveServlet() {
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

            Integer follow_id = Integer.parseInt(request.getParameter("follow_id"));
            Integer follower_id = Integer.parseInt(request.getParameter("follower_id"));

            //ログイン中のユーザーがフォロワーのフォローIDを取得
            List<Follower> follows = em.createNamedQuery("getFollowerRecord",Follower.class)
                    .setParameter("follow_id",follow_id )
                    .setParameter("follower_id",follower_id )
                    .getResultList();

            Follower f;
            if(follows.size() == 1){
                f = follows.get(0);
                em.getTransaction().begin();
                em.remove(f);
                em.getTransaction().commit();
            }
            em.close();
            response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("report_id"));
        }
    }
}
