<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${customer != null}">
                <h2>顧客　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr >
                            <th>id</th>
                            <td>
                                 <c:out value="${customer.id}　" />
                            </td>
                        </tr>
                        <tr >
                            <th>氏名</th>
                            <td>
                                 <c:out value="${customer.name}　" />
                            </td>
                        </tr>
                        <tr >
                            <th>
                                関連する日報
                            </th>
                            <td>
                                 <c:out value="${reports_count}" />件　
                                 <a href="<c:url value="/reports/customer_search?id=${customer.id}" />">一覧を表示</a>
                            </td>
                        </tr>
                      </tbody>
                </table>
                <p><a href="<c:url value="/customers/edit?id=${customer.id}" />">この顧客を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/customers/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>