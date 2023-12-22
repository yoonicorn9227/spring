/**
 *  join02.jsp / 회원가입
 */
$(function() {
	let chkKeyUp = 0;
	$("#saveBtn").click(function() {
		if (chkKeyUp != 1) {
			alert("아이디 중복확인 하셔야 다음으로 진행이 가능합니다.");
			return false;
		}//if(아이디 중복체크 실행여부 확인)

		if ($("#name").val().length < 1) {
			alert("이름을 입력하세요. ※ 필수 입력사항");
			$("#name").focus();
			return false;
		}

		if ($("#id").val().length < 1) {
			alert("아이디를 입력하세요. ※ 필수 입력사항");
			$("#id").focus();
			return false;
		}

		if ($("#pw1").val().length < 1) {
			alert("비밀번호를 입력하세요. ※ 필수 입력사항");
			$("#pw1").focus();
			return false;
		}

		//♠ajax
		$.ajax({
			url: "/member/join02",
			type: "post", //get, post
			data: $("#agreeFrm").serialize(), //복수개의 데이터 보낼경우 : form에있는 모든 입력데이터를 한번에 전송 : $(form태크 id값).serialize()
			dataType: "text", //받는 파일형태 - text, json, xml
			//contentType:"json",//※필요시 사용 내가보내는 파일형태
			success: function(data) {
				alert("성공")
				console.log("data : " + data)
				if(data=="가입완료"){
					location.href="join03"
				}//if
			}, //success
			error: function() {
				alert("실패")
			}//error
		});//ajax

	});//#saveBtn(가입하기)

	//@아이디 확인 버튼 클릭 후 아이디가 수정되었는지 확인
	$("#id").keyup(function() {
		console.log("key Up 발생")
		$("#chkTxt").text("아이디 중복체크를 하셔야 합니다.");
		$("#chkTxt").css({ "color": "orange", "font-weight": 700 });
		chkKeyUp = 0;
	});//id.keyUp


	//아이디 중복체크
	$("#idCheckBtn").click(function() {
		alert("test");
		//♠ajax
		$.ajax({
			url: "/member/idCheck",
			type: "post", //get, post
			//data: { "id": $("#id").val() }, //데이터를 개별적으로 보냄
			data: $("#agreeFrm").serialize(), //복수개의 데이터 보낼경우 : form에있는 모든 입력데이터를 한번에 전송 : $(form태크 id값).serialize()
			dataType: "text", //받는 파일형태 - text, json, xml
			//contentType:"json",//※필요시 사용 내가보내는 파일형태
			success: function(data) {
				alert("성공")
				console.log("data : " + data)
				if (data == "사용가능") {
					$("#idChkTxt").text("아이디 사용가능");
					$("#idChkTxt").css({ "color": "blue", "font-weight": 700 });
					chkKeyUp = 1;
				} else {
					$("#idChkTxt").text("아이디 사용불가");
					$("#idChkTxt").css({ "color": "red", "font-weight": 700 });
				}//if-else
			}, //success
			error: function() {
				alert("실패")
			}//error
		});//ajax

	});//#idCheckBtn(아이디 중복 체크)

	$("#pw2").keyup(function() {
		if ($("#pw1").val() == $("#pw2").val()) {
			$("#pwChkTxt").text("비밀번호가 일치합니다.");
			$("#pwChkTxt").css({ "color": "blue", "font-weight": 700 });
		} else {
			$("#pwChkTxt").text("비밀번호가 일치하지 않습니다.");
			$("#pwChkTxt").css({ "color": "red", "font-weight": 700 });
		}//if-else

		if ($("#pw2").val() == "") {
			$("#pwChkTxt").text("비밀번호를 다시 한번 입력해 주세요.");
			$("#pwChkTxt").css({ "color": "#555555", "font-weight": 700 });
			$("#pw2").focus();
			return false;
		}//if : 비밀번호 미입력시

	});//pw2아이디 체크

	//다음API : 주소창
	$("#addressBtn").click(function() {
		new daum.Postcode({
			oncomplete: function(data) {
				$("#f_postal").val(data.zonecode);
				$("#address1").val(data.address);
			}//
		}).open();
	});//#addressBtn

});//제이쿼리 최신