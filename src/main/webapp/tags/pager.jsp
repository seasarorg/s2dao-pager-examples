<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	// パラメータの取得
	String conditionName = request.getParameter("condition");
	String counter = request.getParameter("counter");
	String href = request.getParameter("href");
	
	// PagerViewHelperの作成
	org.seasar.dao.pager.PagerCondition condition =
		(org.seasar.dao.pager.PagerCondition)pageContext.findAttribute(conditionName);
	org.seasar.dao.pager.PagerViewHelper helperCondition = 
		new org.seasar.dao.pager.PagerViewHelper(condition);

	// 属性をセット
	pageContext.setAttribute("counter", counter);
	pageContext.setAttribute("href", href);
	pageContext.setAttribute("helperCondition", helperCondition);
%>


<%-- メイン --%>
<table border="0" cellpadding="0" cellspacing="0">
<tr>
<c:if test="${counter != 'false'}">
	<td width="200" align="left" valign="center">
		該当件数：<c:out value="${helperCondition.count}"/>件
		(<c:out value="${helperCondition.offset + 1}"/>～<c:out value="${helperCondition.currentLastOffset + 1}"/>)
	</td>
</c:if>

	<td align="right" valign="center">
<c:if test="${helperCondition.prev}">
		<a href="<c:out value="${href}"/>?offset=<c:out value="${helperCondition.prevOffset}"/>">&lt; 前の<c:out value="${helperCondition.limit}"/>件</a>
</c:if>
			&nbsp;
<c:forEach begin="0" end="${helperCondition.lastPageIndex}" step="1" varStatus="status">
	<c:if test="${status.index != helperCondition.pageIndex}">
			&nbsp;<a href="<c:out value="${href}"/>?offset=<c:out value="${status.index * helperCondition.limit}"/>"><c:out value="${status.count}"/></a>
	</c:if>
	<c:if test="${status.index == helperCondition.pageIndex}">
			&nbsp;[<c:out value="${status.count}"/>]
	</c:if>
</c:forEach>
			&nbsp;&nbsp;&nbsp;
<c:if test="${helperCondition.next}">
		<a href="<c:out value="${href}"/>?offset=<c:out value="${helperCondition.nextOffset}"/>">次の<c:out value="${helperCondition.limit}"/>件 &gt;</a></td>
</c:if>
<c:if test="${!helperCondition.next}">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</c:if>

</tr>
</table>