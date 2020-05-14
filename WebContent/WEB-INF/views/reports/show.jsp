<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr >
                            <th>氏名</th>
                            <td>
                                <div style="display:inline-flex">
                                 <c:out value="${report.employee.name}　" />

                                 <c:if test="${sessionScope.login_employee.id != report.employee.id}">
                                     <c:choose>
                                         <c:when test="${followed}">
                                             <form method="POST" action="<c:url value="/follow/remove"/>">
                                                <input type="hidden" name="_token" value="${_token}" />
                                                <input type="hidden" name="report_id" value="${report.id}" />
                                                <input type="hidden" name="follower_id" value="${sessionScope.login_employee.id }" />
                                                <input type="hidden" name="follow_id" value="<c:out value="${report.employee.id}"/>">
                                                <input type="submit" class = "onbutton"value = "フォロー解除">
                                              </form>
                                         </c:when>
                                         <c:otherwise>
                                             <form method="POST" action="<c:url value="/follow/add" />">
                                                <input type="hidden" name="_token" value="${_token}" />
                                                <input type="hidden" name="report_id" value="${report.id}" />
                                                <input type="hidden" name="follower_id" value="${sessionScope.login_employee.id }" />
                                                <input type="hidden" name="follow_id" value="<c:out value="${report.employee.id}"/>">
                                                <input type="submit" class = "offbutton" value = "フォロー">
                                             </form>
                                          </c:otherwise>
                                      </c:choose>
                                </c:if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <c:if test="${report.customer != null}">
                            <tr>
                                <th>商談相手</th>
                                <td>
                                    <c:out value = "${report.customer.name}" />
                                </td>
                            </tr>
                            <tr>
                                <th>商談状況</th>
                                <td>
                                    <c:out value = "${report.business_status}" />
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr><tr>
                            <th>承認</th>
                            <td>
                                <c:choose>
                                     <c:when test="${report.approval_flag == 1}">
                                         済
                                     </c:when>
                                     <c:otherwise>
                                         <div style="display:inline-flex">
                                         未　
                                        <c:if test = "${login_employee.group.code == report.employee.group.code and login_employee.position.level > report.employee.position.level}">
                                                <form method="POST" action="<c:url value="/reports/approve"/>">
                                                <input type="hidden" name="_token" value="${_token}" />
                                                <input type="hidden" name="report_id" value="${report.id}" />
                                                <input type="submit" value = "承認">
                                              </form>
                                         </c:if>
                                         </div>
                                      </c:otherwise>
                                  </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <c:choose>
                     <c:when test ="${liked}">
                           <form method="POST" action="<c:url value="/reaction/remove"/>">
                                 <input type="hidden" name="_token" value="${_token}" />
                                 <input type="hidden" name="report_id" value="${report.id}" />
                                 <input type="hidden" name="type" value="0" />
                                 <input type="submit" value = "${'いいね :'+=good_count}" class = "onbutton">
                           </form>
                     </c:when>
                     <c:otherwise>
                        <form method="POST" action="<c:url value="/reaction/add"/>">
                             <input type="hidden" name="_token" value="${_token}" />
                             <input type="hidden" name="report_id" value="${report.id}" />
                             <input type="hidden" name="type" value="0" />
                             <input type="submit" value = "${'いいね :'+=good_count}" class = "offbutton">
                       </form>
                     </c:otherwise>
                  </c:choose>

                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p><a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>