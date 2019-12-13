<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="../utils.js"></script>
</head>
<body>
<div class="row">
<div class="col-6">
<h1 style="text-align: center;">표1</h1>
    <canvas id="myChart1"></canvas>
</div>
<div class="col-6">
<h1 style="text-align: center;">표2</h1>
    <canvas id="myChart2"></canvas>
</div>
</div>

<div class="row">
<div class="col-6">
<h1 style="text-align: center;">표3</h1>
    <canvas id="myChart3"></canvas>
</div>
<div class="col-6">
<h1 style="text-align: center;">표4</h1>
    <canvas id="myChart4"></canvas>
</div>
</div>
<script>
// 컨텍스트 호출
var ctx1 = document.getElementById("myChart1").getContext('2d');
var ctx2 = document.getElementById("myChart2").getContext('2d');
var ctx3 = document.getElementById("myChart3").getContext('2d');
var ctx4 = document.getElementById("myChart4").getContext('2d');
/*
- Chart를 생성하면서, 
- ctx를 첫번째 argument로 넘겨주고, 
- 두번째 argument로 그림을 그릴때 필요한 요소들을 모두 넘겨줍니다. 
*/

// 혼합차트 
var mixedChart = new Chart(ctx1, {
    type: 'bar',
    data: {
        datasets: [{
            label: 'Bar Dataset',
            data: [10, 20, 30, 40],
            backgroundColor:[
            	'rgba(255, 99, 132, 0.2)',
            	'rgba(54, 162, 235, 0.2)',
            	'rgba(255, 206, 86, 0.2)',
            	'rgba(75, 192, 192, 0.2)',
            ],
            borderColor: [					// 그래프 선 색
          		'rgba(255,99,132,1)',
          		'rgba(54, 162, 235, 1)',
         		'rgba(255, 206, 86, 1)',
          		'rgba(75, 192, 192, 1)',
         ],
         borderWidth: 1,
            // this dataset is drawn below
            order: 1
        }, {
            label: 'Line Dataset',
            data: [10, 40, 10, 10],
            type: 'line',
            fill:false,
            borderColor: [					// 그래프 선 색
            	'rgba(54, 162, 235, 1)',
            	'rgba(54, 162, 235, 1)',
            	'rgba(54, 162, 235, 1)',
            	'rgba(54, 162, 235, 1)',
         ],
         borderWidth: 2,
            // this dataset is drawn on top
            order: 2
        }],
        labels: ['January', 'February', 'March', 'April']
    },
    options: {
     maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
     scales: {
         yAxes: [{
             ticks: {
                 beginAtZero:true
             }
         }]
     }
 }
});

var myChart = new Chart(ctx2, {
    type: 'bar',	// 그래프 타입
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [{
            label: '# of Votes',			// 그래프 이름
            data: [100, 19, 3, 5, 2, 3],	
            backgroundColor: [				// 그래프 배경색
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [					// 그래프 선 색
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1	// 그래프 선 굵기
        }]
    },
    options: {
        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

var myPieChart = new Chart(ctx3, {
    type: 'pie',
    data: data,
    options: options
});

</script>
</body>
</html>