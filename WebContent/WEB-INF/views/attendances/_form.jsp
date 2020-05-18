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
<label for="date">日付<span class="require">[必須]</span></label><br />
<input type="date" name="date" value="<fmt:formatDate value='${attendance.date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="attendance_time">出勤時刻<span class="require">[必須]</span></label><br />
<input type="time" name="attendance_time" value="<c:out value='${attendance.attendance_time}' />" />
<br /><br />

<label for="leave_time">出勤時刻<span class="require">[必須]</span></label><br />
<input type="time" name= "leave_time" value="<c:out value='${attendance.leave_time}'  />" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>