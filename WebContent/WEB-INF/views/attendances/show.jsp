<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${attendance != null}">
                <h2>出退勤　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr >
                            <th>氏名</th>
                            <td>
                                 <c:out value="${attendance.employee.name}　" />
                            </td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${attendance.date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>出勤時刻</th>
                            <td><c:out value="${attendance.attendance_time}" /></td>
                        </tr>
                        <tr>
                            <th>退勤時刻</th>
                            <td><c:out value="${attendance.leave_time}" /></td>
                        </tr>
                    </tbody>
                </table>


                <c:if test="${sessionScope.login_employee.id == attendance.employee.id}">
                    <p><a href="<c:url value="/attendances/edit?id=${attendance.id}" />">この記録を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/attendances/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>