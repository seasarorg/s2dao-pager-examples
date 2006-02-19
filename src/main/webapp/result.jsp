<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<html>
<title>Pager Example</title>
<body>
Result
<form action="FindBookServlet" method="get">
	<select name="category">
		<option value=""></option>
		<option value="Java">Java</option>
		<option value="Ruby">Ruby</option>
	</select>
	<input type="submit" value="Find">
</form>
<font color="green">
検索条件:${categoryPagerCondition.category}
</font>

<h2>タグファイル(JSP2.0)版でのページャの表示版でのページャの表示サンプル</h2>

<!--ページャー(件数表示あり)-->
<tag:pager condition="${categoryPagerCondition}"/>

<table border="1">
	<tr>
		<th>
			タイトル
		</th>
		<th>
			カテゴリ
		</th>
	</tr>
<c:forEach var="book" items="${books}" varStatus="status">
	<tr>
		<td>
			<c:out value="${book.title}"/>
		</td>
		<td>
			<c:out value="${book.category}"/>
		</td>
	</tr>
</c:forEach>
</table>

<!--ページャー(件数表示なし)-->
<tag:pager condition="${categoryPagerCondition}" counter="false" href="FindBookServlet"/>


<h2>&lt;c:import&gt;を使った(JSP1.1 or JSP1.2)版でのページャの表示サンプル</h2>

<!--ページャー(件数表示あり)-->
<c:import url="tags/pager.jsp">
	<c:param name="condition" value="categoryPagerCondition"/>
</c:import>

<table border="1">
	<tr>
		<th>
			タイトル
		</th>
		<th>
			カテゴリ
		</th>
	</tr>
<c:forEach var="book" items="${books}" varStatus="status">
	<tr>
		<td>
			<c:out value="${book.title}"/>
		</td>
		<td>
			<c:out value="${book.category}"/>
		</td>
	</tr>
</c:forEach>
</table>

<!--ページャー(件数表示あり)-->
<c:import url="tags/pager.jsp">
	<c:param name="condition" value="categoryPagerCondition"/>
	<c:param name="counter" value="false"/>
	<c:param name="href" value="FindBookServlet"/>
</c:import>

</body>
