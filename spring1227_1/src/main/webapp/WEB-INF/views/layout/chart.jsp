<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>chart</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		
		<script>
			$(function(){
				$("#cbtn").click(function(){
				 alert("차트 데이터를 가져옵니다.");
				 alert($("#cyear").val());
				 let cyear =$("#cyear").val();
				 
				 
				 // ★Chart객체 리셋해야 다시 차트를 그릴수 있음.
				 let chartStatus = Chart.getChart("myChart"); // <canvas> id
				 if (chartStatus != undefined) {
				 	chartStatus.destroy();
				 	console.log("mhChart리셋");
				 }//if
				 
				 
				 let chartLabels =[];
				 let cData =[];
				 
				 //DB연결 - 데이터를 가져옴.(ajax사용)
				 $.ajax({
					 url :"/layout/incomeSelect",
					 type :"post",
					 data :{"cyear":cyear},
					 dataType :"json",
					 success : function(data){
						 alert("성공");
						 console.log(data);
						 console.log(data.length);
						 console.log("6월 : "+data[5].cmonth);
						 console.log("6월 수입 : "+data[5].csale);
						 
						 //---for---
						 for(let i=0; i<data.length; i++){
							 chartLabels.push(data[i].cmonth);
							 cData.push(data[i].csale)
						 }//for
						
						 /*  <<단순for문>>
						 for(d:data){//d에 배열중에서 1번째 객체를 넣어줘
							 chartLabels.push(d.cmonth);
							 cData.push(d.csale)
						 		
						 }//for(단순for문) */
						 
						 
						 console.log(chartLabels);
						 console.log(cData);
						 
						 //----차트 그리기 시작----
						 const ctx =$("#myChart");

						  new Chart(ctx, {
						    type: 'bar',
						    data: {
						      labels: chartLabels,
						      datasets: [{
						        label: cyear+'매출액',
						        data: cData ,
						        backgroundColor: [
						            'rgba(255, 99, 132, 0.2)',
						            'rgba(255, 159, 64, 0.2)',
						            'rgba(255, 205, 86, 0.2)',
						            'rgba(75, 192, 192, 0.2)',
						            'rgba(54, 162, 235, 0.2)',
						            'rgba(153, 102, 255, 0.2)',
						            'rgba(201, 203, 207, 0.2)'
						          ],
						          borderColor: [
						            'rgb(255, 99, 132)',
						            'rgb(255, 159, 64)',
						            'rgb(255, 205, 86)',
						            'rgb(75, 192, 192)',
						            'rgb(54, 162, 235)',
						            'rgb(153, 102, 255)',
						            'rgb(201, 203, 207)'
						          ],
						        borderWidth: 3
						      }]
						    },
						    options: {
						      scales: {
						        y: {
						          beginAtZero: true
						        }
						      }
						    }
						  });// chart --차트 그리기 끝--
						 
					 },//success
					 error:function(){
						 alert("실패");
					 }//error
				 });//ajax
				 
				 
				});//#cbtn
			});//제이쿼리 최신
		</script>
		<style>
			.area{width: 50%; height:400px; margin: 20px auto; border: 3px solid black;}
		</style>
	</head>
	<body>
		<select id="cyear">
			<option value="2022년">2022년</option>
			<option value="2023년">2023년</option>
		</select>
		<button id="cbtn">차트데이터 가져오기</button>
		<br>
		<div class="area">
			<canvas id="myChart"></canvas>
		</div>
	
	</body>
</html>