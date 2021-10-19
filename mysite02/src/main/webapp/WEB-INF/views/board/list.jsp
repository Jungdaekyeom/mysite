<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<!-- int max = 0 과 같음 -->
					<c:set var='max' value='0' />

					<!-- 게시글의 max값 출력 -->
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<c:if test='${max < vo.groupNo }'>
							<c:set var='max' value='${vo.groupNo }' />
						</c:if>
					</c:forEach>
					<!-- param 페이지 값 가져옴 -->

					<!-- 리스트의 길이를 count 변수에 담겠다. -->
					<c:set var='count' value='${fn:length(list) }' />
					페이지 : ${param.p }P 게시글 그룹 아이디의 최댓값 : ${max }개 섹션페이지 : ${param.sec }
					전체 게시글 갯수 : ${count }
					
					<!-- ListAction에서 객체를 가지고 넘어온 list를 vo라는 이름으로 쓰겠다. -->
					<!-- status를 사용하여 인덱스 값을 추출 가능 -->
					<!-- count는 list의 전체 개수를 가리킨다. -->
					<!-- begin과 end는 시작 인덱스와 끝 인덱스 -->
					<c:forEach items='${list }' var='vo' varStatus='status'
						begin='${(param.p - 1) * 10 }' end='${(param.p - 1) * 10 + 9 }'>
						
						<tr>
							<td>${count-status.index }</td>
							<c:choose>
								<c:when test="${vo.depth eq 0 }">
									<td style="text-align: left; padding-left: 0px"><a
										href="${pageContext.servletContext.contextPath }/board?a=viewform&no=${vo.no }">${vo.title }</a></td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${20 * vo.depth }px"><img
										src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a
										href="">${vo.title }</a></td>
								</c:otherwise>
							</c:choose>
							<!-- 조인한 테이블에서 이름을 갖고옴 -->
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<!-- delete -->
							<td><a href="" class="del">삭제</a></td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<!-- end에는 페이지 길이가 들어가야 한다. -->
				<!-- 전체 길이를 10으로 나눈 후, 그 값에 1을 더하여 페이지 숫자를 결정한다. -->
				<!-- count가 0인 상황을 고려할 것  -->
				<!-- 게시글이 아무것도 없을 때, -->
				<!-- 페이징 넘버링들조차 표시되지 않음 -->
				<!-- begin에는 5로 쪼개진 페이지의 시작 번호가 -->
				<!-- end에는 리스트 전체 길이를 10으로 나눈 값 + 1이 들어가야한다. -->
				<c:if test="${count - (50*(param.sec-1)) >= 50}">
					<c:set var="endCount" value="${5*param.sec }" />
				</c:if>
				<c:if test="${count - (50*(param.sec-1)) < 50}">
					<c:set var="endCount" value="${(count-1)/10 + 1 }" />
				</c:if>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${count eq 0 }">
							</c:when>
							<c:otherwise>
								<c:if test="${param.sec eq 1 }">
									<li>◀</li>
								</c:if>
								<c:if test="${param.sec ne 1 }">
									<li><a
										href="${pageContext.request.contextPath }/board?a=list&p=${(param.sec - 1)*5}&sec=${param.sec - 1}">◀</a></li>
								</c:if>
								<c:forEach var='i' begin='${5*(param.sec-1) + 1 }'
									end='${endCount }'>
									<c:choose>
										<c:when test='${i eq param.p }'>
											<li class="selected">${i}</li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath }/board?a=list&p=${i}&sec=${param.sec}">${i}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${count - (50*(param.sec-1)) > 50}">
									<li><a
										href="${pageContext.request.contextPath }/board?a=list&p=${param.sec*5+1}&sec=${param.sec + 1}">▶</a></li>
								</c:if>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>