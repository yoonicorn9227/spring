/**
 *  login.jsp / 로그인
 */
$(function() {
	$("#saveBtn").click(function() {
		alert("test");
		alert("아이디 : " + $("#id").val());
		if ($("#id").val().length < 1) {
			alert("아이디를 입력하세요.");
			$("#id").focus();
			return false;
		}//if(#id) 미입력
		
		if ($("#pw").val().length < 1) {
			alert("비밀번호를 입력하세요.");
			$("#pw").focus();
			return false;
		}//if(#pw) 미입력
		loginFrm.submit();
	});//#saveBtn
});//제이쿼리 최신