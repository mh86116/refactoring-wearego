window.onload = function(){
//문서당 window.onload는 한개만 존재하므로, 추가적으로 사용하지 못한다.
	
	/* 회원 가입 버튼 클릭 시 서블릿으로 이동 */
	if(document.getElementById("regist")){
		const $regist = document.getElementById("regist");
		//마우스 오버 시 손가락 모양 
		$regist.onmouseenter = function(){
			this.parentNode.style.cursor = "pointer";
		}
		//클릭 시 페이지 이동
		$regist.onclick = function(){
		
			location.href="/we/member/term";
		}
	}
	
	if(document.getElementById("logout")){
		const $logout = document.getElementById("logout");
		//마우스 오버 시 손가락 모양 
		$logout.onmouseenter = function(){
			this.parentNode.style.cursor = "pointer";
		}
		//클릭 시 페이지 이동
		$logout.onclick = function(){
		
			location.href="/we/member/logout";
		}
	}
	
	if(document.getElementById("reservationList")) {
		$reservationList.onclick = function() {
			location.href = "/we/travel/reservationList";
		}
	}
	
	if(document.getElementById("")){
		$("#Pwdreset").click(function(){
		   const $form =  $("<form></form>");
		   var Pwd = $("#memberPwd").val()
		   
		   
		  	$form.attr("action", "${ pageContext.servletContext.contextPath }/newPassword") ;
		  	$form.attr("method","POST");
		  	$form.append($('<input/>', {type: 'hidden', name: 'token', value: '${ requestScope.token }'}));
		  	
		 });
	}
	
	
	if(document.getElementById("#summernote1")){
	
		  $('#summernote1').summernote();
	}
	
	if(document.getElementById("writeThumbnail")) {
	 	const $writeThumbnail = document.getElementById("writeThumbnail");
	 	$writeThumbnail.onclick = function() {
	 			location.href = "/we/review/insert";
	 	}
 }	 		
	
	
}