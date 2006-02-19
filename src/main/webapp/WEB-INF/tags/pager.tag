<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 基本情報 --%>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag description="ページャーを表示します。" %>

<%-- 属性と変数の定義 --%>
<%@ attribute 
	name="condition" 
	required="true" 
	type="org.seasar.dao.pager.PagerCondition" 
	rtexprvalue="true" 
	description="PagerConditionオブジェクト" %>
<%@ attribute 
	name="counter" 
	rtexprvalue="true" 
	description="総数を表示するかどうかのフラグ(true/false)" %>
<%@ attribute 
	name="href" 
	rtexprvalue="true" 
	description="Aタグのhref" %>
<%-- 初期化 --%>
<%
	org.seasar.dao.pager.PagerCondition condition =
		(org.seasar.dao.pager.PagerCondition)jspContext.getAttribute("condition");
	org.seasar.dao.pager.PagerViewHelper helperCondition = 
		new org.seasar.dao.pager.PagerViewHelper(condition);
	jspContext.setAttribute("helperCondition", helperCondition);
%>


<%-- メイン --%>
<table border="0" cellpadding="0" cellspacing="0">
<tr>
<c:if test="${counter != 'false'}">
	<td width="200" align="left" valign="center">
		該当件数：${helperCondition.count}件
	</td>
</c:if>

	<td align="right" valign="center">
<c:if test="${helperCondition.prev}">
		<a href="${href}?offset=${helperCondition.prevOffset}">&lt; 前の${helperCondition.limit}件</a>
</c:if>
			&nbsp;
<c:forEach begin="0" end="${helperCondition.lastPageIndex}" step="1" varStatus="status">
	<c:if test="${status.index != helperCondition.pageIndex}">
			&nbsp;<a href="${href}?offset=${status.index * helperCondition.limit}">${status.count}</a>
	</c:if>
	<c:if test="${status.index == helperCondition.pageIndex}">
			&nbsp;[${status.count}]
	</c:if>
</c:forEach>
			&nbsp;&nbsp;&nbsp;
<c:if test="${helperCondition.next}">
		<a href="${href}?offset=${helperCondition.nextOffset}">次の${helperCondition.limit}件 &gt;</a></td>
</c:if>
<c:if test="${!helperCondition.next}">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</c:if>

</tr>
</table>
