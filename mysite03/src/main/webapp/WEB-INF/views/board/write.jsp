<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<!-- 답글일 때는 해당 메인 글의 번호와 그룹 넘버, 오더 넘버 및 여러 정보를 알 필요가 있으므로, 메인 글과 답글을 if로 분기 -->
				<c:if test="${c eq 0 }">
					<form class="board-form" method="post"
						action="${pageContext.request.contextPath }/board/write">
						<!-- 글쓰고 있는 유저의 번호를 hidden으로 넘김 -->
						<input type="hidden" name="userno" value="${authUser.no }">
						<input type="hidden" name="maxGroupNo" value="${maxGroupNo }">
						<input type="hidden" name="comment" value="${c }">
						
						<table class="tbl-ex">
							<tr>
								<th colspan="2">글쓰기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td><textarea id="content" name="contents"></textarea></td>
							</tr>
						</table>
						<div class="bottom">
							<a
								href="${pageContext.request.contextPath }/board/list?p=${param.p }&sec=${param.sec}">취소</a>
							<input type="submit" value="등록">
						</div>
					</form>
				</c:if>

				<!-- 메인과 달리 답글 달 때는 모든 객체의 정보를 갖고와서 처리해줄 필요가 있으므로, 분기 -->
				<c:if test="${c eq 1}">
					<form class="board-form" method="post"
						action="${pageContext.request.contextPath }/board/write">
						<!-- 글쓰고 있는 유저의 번호를 hidden으로 넘김 -->
						<input type="hidden" name="userno" value="${authUser.no }">
						<input type="hidden" name="comment" value="${c }">
						<input type="hidden" name="no" value="${no }">
						<input type="hidden" name="p" value="${param.p }">
						<input type="hidden" name="sec" value="${param.sec}">
						
						<table class="tbl-ex">
							<tr>
								<th colspan="2">답글달기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td><input type="text" name="title" value=""></td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td><textarea id="content" name="contents"></textarea></td>
							</tr>
						</table>
						<div class="bottom">
							<a
								href="${pageContext.request.contextPath }/board/list?p=${param.p }&sec=${param.sec}">취소</a>
							<input type="submit" value="등록">
						</div>
					</form>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>