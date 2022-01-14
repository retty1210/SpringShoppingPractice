<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="bs5_url" value="/static/bs5" />
	<c:url var="jq_url" value="/static/jq" />
	<link type="text/css" rel="stylesheet" href="${bs5_url}/css/bootstrap.min.css">
	<script type="text/javascript" src="${bs5_url}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${bs5_url}/js/bootstrap.bundle.js"></script>

<div class="px-3 py-2 text-white" style="background-color:darkorange;">
      <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
            <li>
              <c:url var="homeURL" value="/" />
              <a href="${homeURL}" class="nav-link text-secondary">
			  <svg class="bi d-block mx-auto mb-1" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 24 24">
				  <path fill-rule="evenodd" d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
				  <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
			  </svg>
                Home
              </a>
            </li>
            <c:choose>
            	<c:when test="${sessionScope.logined eq true}">
            		<li>
		              <c:url var="logoutURL" value="/logout" />
		              <a href="${logoutURL }" class="nav-link text-white">
		                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-door-closed-fill" viewBox="0 0 24 24">
						  <path d="M12 1a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2a1 1 0 0 1 1-1h8zm-2 9a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
						</svg>
		                로그아웃
		              </a>
		            </li>
            		<c:if test="${sessionScope.usertype == 'admin' }">
            			<li>
			              <c:url var="adminjoinURL" value="/adminjoin" />
			              <a href="${adminjoinURL }" class="nav-link text-white">
			                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-bookmark-heart-fill" viewBox="0 0 24 24">
							  <path d="M2 15.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v13.5zM8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
							</svg>
			                관리자 회원가입
			              </a>
			            </li>
			            <li>
			              <c:url var="everyaccountURL" value="/everyaccount" />
			              <a href="${everyaccountURL }" class="nav-link text-white">
			                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-bookmark-heart-fill" viewBox="0 0 24 24">
							  <path d="M2 15.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v13.5zM8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
							</svg>
			                모든 회원 목록
			              </a>
			            </li>
            		</c:if>
            		<c:if test="${sessionScope.usertype == 'seller' }">
            		
            		</c:if>
		            <c:if test="${sessionScope.usertype == 'buyer' }">
            			<li>
			              <c:url var="wishlistURL" value="/wishlist" />
			              <a href="${wishlistURL }" class="nav-link text-white">
			                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-bookmark-heart-fill" viewBox="0 0 24 24">
							  <path d="M2 15.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2v13.5zM8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
							</svg>
			                즐겨찾기
			              </a>
			            </li>
            		</c:if>
		            
            	</c:when>
            	<c:otherwise>
            		<li>
		              <c:url var="loginURL" value="/login" />
		              <a href="${loginURL }" class="nav-link text-white">
		                <svg class="bi d-block mx-auto mb-1" xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 24 24">
						  <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
						</svg>
		                로그인
		              </a>
		            </li>
            		<li>
		              <c:url var="joinURL" value="/join" />
		              <a href="${joinURL }" class="nav-link text-white">
		                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-journal-check" viewBox="0 0 24 24">
						  <path fill-rule="evenodd" d="M10.854 6.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 8.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
						  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
						  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
						</svg>
		                회원가입
		              </a>
		            </li>
            	</c:otherwise>
            </c:choose>
            
            <li>
              <c:url var="cartURL" value="/cart" />
              <a href="${cartURL }" class="nav-link text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 24 24">
				  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
				</svg>
                장바구니
              </a>
            </li>
            
            <li>
              <c:url var="ordersURL" value="/orders" />
              <a href="${ordersURL }" class="nav-link text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24" fill="currentColor" class="bi bi-clipboard" viewBox="0 0 24 24">
				  <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
				  <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
				</svg>
                주문목록
              </a>
            </li>
            <li>
              <c:url var="itemlistURL" value="/itemlist" />
              <a href="${itemlistURL }" class="nav-link text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="bi d-block mx-auto mb-1" width="24" height="24"fill="currentColor" class="bi bi-shop" viewBox="0 0 24 24">
				  <path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
				</svg>
                ITEMLIST
              </a>
            </li>
          </ul>
          
          <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
            <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
          </form>
        </div>
      </div>
    </div>