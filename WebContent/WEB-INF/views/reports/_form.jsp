<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="report_date">日付<span class="require">[必須]</span></label><br />
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="title">タイトル<span class="require">[必須]</span></label><br />
<input type="text" name="title" value="${report.title}" />
<br /><br />

<label for="content">内容<span class="require">[必須]</span></label><br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<div id="sub">
<p><b><h3>商談状況</h3></b></p>
<label for="customer">顧客</label><br />
<select name="customer">
    <option value="0">未選択</option>
    <c:forEach var="customer" items="${customers}" varStatus="status">
        <option value="${customer.id}" <c:if test="${report.customer.id==status.count}">selected</c:if> >${customer.name}</option>
    </c:forEach>
</select>
<br /><br />

<label for="business_status">状況</label><br />
<textarea name="business_status" rows="3" cols="50">${report.business_status}</textarea>
<br />
</div>
<br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>