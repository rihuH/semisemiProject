<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>

<style>
	table {
		border-collapse: collapse;
		border:1px solid black;
	}
	table th,td{
	border-collapse: collapse;
		border:1px solid black;
	}
	
	#availPlaceDiv {
		overflow : scroll;
		width: 800px;       /* 가로 길이 설정 */
   		height: 600px;
	}
</style>
</head>
<body>
	시험장소 등록
	
	<%--  --%>
	
	<table>
	<thead>
		<tr>
			<th>응시시험</th>
			<th>접수기간</th>
		</tr>
	</thead>
	<tbody>
	<%-- 원서접수 : 검색해야해서, selectExam과 같이 'pro' 문자를 같이 보내줄 것. 
		tr 안에 있는 구성이 2025년 xxx x회 x차 같은 형식--%>
		<c:forEach items="${ proList }" var="c">
			<tr data-examno="${c.examNo }" onclick="selectExam(this, 'pro');">
				<td>${c.examStartDate.substring(0, c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.profesionalQualification.qualificationName } 
				${ c.round }회  
					<c:choose>
						<c:when test="${ c.qualificationExam.qualificationRank eq 1}">
						1차
						</c:when>
						<c:otherwise>
						2차
						</c:otherwise>
					</c:choose>
				</td>
				<td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
			</tr>
		</c:forEach>
		<c:forEach items="${ techList }" var="c">
			<tr data-examno="${c.examNo }" onclick="selectExam(this, 'tech');">
				<td>${c.examStartDate.substring(0,c.examStartDate.indexOf('-'))}년 ${ c.qualificationExam.technicalQualification.qualificationName }
				${ c.round }회
					<c:choose>
						<c:when test="${ c.qualificationExam.qualificationRank eq 1 }">
						필기
						</c:when>
						<c:otherwise>
						실기
						</c:otherwise>
					</c:choose>
				</td>
				<td>${ c.receptionStartDate } 10:00 ~ ${ c.receptionEndDate } 18:00</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<br><br>
	
	<div>
		<div> 등록된 시험장소</div>
		<table>
			<thead>
				<tr>
					<th>시</th>
					<th>구</th>
					<th>장소명</th>
					<th>정원</th>
				</tr>
			</thead>
			<tbody id="placeAdd">
			</tbody>
		</table>
	</div>

	
	<div>
		<input id="searchInput" data-examno="none" placeholder="검색으로 찾기">
		<button type="reset">x</button>
		<button id="searchBtn" type="submit">검색</button>
	</div>
	
	<form action="/quali/taken_quali_exam/insert_place" method="post">
		<button type="submit">시험장소 등록</button>
	<div id="availPlaceDiv">
	
	<div>
		<input type="hidden" name="examNo" value="input"/>
		
		
		<div> 등록 가능한 시험장소</div>
		<table>
			<thead>
				<tr>
					<th>순번</th>
					<th>선택</th>
					<th>시</th>
					<th>구</th>
					<th>장소명</th>
					<th>정원</th>
				</tr>
			</thead>
			<tbody id="availPlaceAdd">
			</tbody>
		</table>
	</div>
	</div>
	</form>

<script>
	function selectExam(e, s){
		console.log(e);
		//var firstTd = $('e  td');
		var firstTd = $(e).children().eq(0)[0].innerText; // \$('e')로 선택하면 선택이 안됨
		var secondTd = $(e).children().eq(1)[0].innerText;
		var examno = e.dataset.examno; // 'data-examNo' 값 가져오기
		$('#searchInput').get(0).dataset.examno = examno;
		$.ajax({
			url : "/quali/taken_quali_exam/selectPlace",
			type : "get",
			data : {
				exam : firstTd,
				receptionDate : secondTd, 
				type : s
			},
			success: function(map){
				$('.addedClass').remove();
				$('input[type=hidden]').val(`\${map.data.takenQualiExam.examNo}`);
				var inputValue = $('input[type=hidden]').val();
			    var i = 0;
			    const placeTbody = $('#placeAdd'); // tbody로 직접 접근
			    const placesOfExam = map.data.placesOfExam; // map.data 안에 placesOfExam이 있으므로 올바르게 접근

			    if (Array.isArray(placesOfExam) && placesOfExam.length > 0) {
			        while (placesOfExam[i]) {
			            const place = placesOfExam[i].place; // 각 시험 장소의 정보를 가져옵니다.
			            const district = place.district; // district 정보 가져오기

			            // 새로운 행을 동적으로 생성하여 추가
			            var row = $('<tr class="addedClass">');
			            row.append(`<td>\${district.cityName}</td>`);
			            row.append(`<td>\${district.district}</td>`);
			            row.append(`<td>\${place.locationName}</td>`);
			            row.append(`<td>\${place.maximumOccupancy}</td>`);
			            row.append(`</tr>`);

			            // 테이블에 행 추가
			            placeTbody.append(row);
			            i = i + 1;
			        }
			    } else {
			        console.error("placesOfExam 배열이 비어 있거나 정의되지 않았습니다.");
			    }
			    
			    i = 0;
			    const placeTbody2 = $('#availPlaceAdd'); // tbody로 직접 접근
			    const availablePlaces = map.data.availablePlaces; // map.data 안에 placesOfExam이 있으므로 올바르게 접근

			    if (Array.isArray(availablePlaces) && availablePlaces.length > 0) {
			        while (availablePlaces[i]) {
			            // 새로운 행을 동적으로 생성하여 추가
			            var row = $('<tr class="addedClass">');
			            row.append(`<td>\${i + 1}</td>`);
			            row.append(`<td><input type="checkbox" name="insertPlaceNo" value="\${availablePlaces[i].locationNo}"></td><label>`);
			            row.append(`<td>\${availablePlaces[i].district.cityName}</td>`);
			            row.append(`<td>\${availablePlaces[i].district.district}</td>`);
			            row.append(`<td>\${availablePlaces[i].locationName}</td>`);
			            row.append(`<td>\${availablePlaces[i].maximumOccupancy}</td>`);
			            row.append(`</tr></label>`);

			            // 테이블에 행 추가
			            placeTbody2.append(row);
			            i = i + 1;
			        }
			    } 
			}

		});
	}
		
		$(function(){
		    $('#searchBtn').click(function(){
				let searched = $('#searchInput').val();
				let examno = $('#searchInput').get(0).dataset.examno;
				console.log(examno);
				if(examno != 'none'){
					if( searched){
						$.ajax({
							url : "/quali/taken_quali_exam/placeSearch",
							type : "get",
							data : {
								searched : searched,
								examno : examno
							},
							success : function(response){
								//$('.addedClass').remove();
								console.log('성공');
								
								
				            }
				        });
						
					} else{
						$('.addedProTr').remove();
						$('.addedTechTr').remove();
					}
			    };
						
				})
		});
		
		
		
		
	
</script>


</body>
</html>