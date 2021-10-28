<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>삭제</th>
					</tr>
					
					<!-- 강사님은 map.list로 갖고오심 -->
					<c:forEach items="${list }"	var="vo" varStatus="status">			
						<tr>
							<td>${total-(10*(param.p-1) + status.index) }</td>
							<c:choose>
								<c:when test="${vo.depth eq 0 }">
									<c:if test="${vo.deleteBool eq 1 }">
										<td style="text-align: left; padding-left: 0px">
											<a href="${pageContext.servletContext.contextPath }/board/view/${vo.no }?&p=${param.p}&sec=${param.sec}">${vo.title }</a></td>
									</c:if>
									<c:if test="${vo.deleteBool eq 0 }">
										<td style="text-align: left; padding-left: 0px">삭제된 메시지입니다.</td>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${vo.deleteBool eq 1 }">
										<td style="text-align:left; padding-left:${20 * vo.depth }px"><img
											src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a
											href="${pageContext.servletContext.contextPath }/board/view/${vo.no }?p=${param.p}&sec=${param.sec}">${vo.title }</a></td>
									</c:if>
									<c:if test="${vo.deleteBool eq 0 }">
										<td style="text-align:left; padding-left:${20 * vo.depth }px">
										<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />삭제된 댓글입니다.</td>
									</c:if>

								</c:otherwise>
							</c:choose>

							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							
							<!-- delete -->
							<c:if
								test="${(vo.deleteBool eq 1) && (authUser.no eq vo.userNo)}">
								<td><a
									href="${pageContext.servletContext.contextPath }/board/delete/${vo.no }?p=${param.p }&sec=${param.sec}"
									class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				
				<c:if test="${total - (50*(param.sec-1)) >= 50}">
					<c:set var="endCount" value="${5*param.sec }" />
				</c:if>
				<c:if test="${total - (50*(param.sec-1)) < 50}">
					<c:set var="endCount" value="${(total-1)/10 + 1 }" />
				</c:if>
				
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${total eq 0 }">
							</c:when>
							<c:otherwise>
								<c:if test="${param.sec eq 1 }">
									<li>◀</li>
								</c:if>
								<c:if test="${param.sec ne 1 }">
									<li><a
										href="${pageContext.request.contextPath }/board?p=${(param.sec - 1) * 5}&sec=${param.sec - 1}">◀</a></li>
								</c:if>
								<c:forEach var='i' begin='${5*(param.sec-1) + 1 }'
									end='${endCount }'>
									<c:choose>
										<c:when test='${i eq param.p }'>
											<li class="selected">${i}</li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath }/board?p=${i}&sec=${param.sec}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${total - (50*(param.sec-1)) > 50}">
									<li><a
										href="${pageContext.request.contextPath }/board?p=${(param.sec * 5) + 1}&sec=${param.sec + 1}">▶</a></li>
								</c:if>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write/0?m=${maxGroupNo }" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
