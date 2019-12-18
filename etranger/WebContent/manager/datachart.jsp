<%@page import="manager.vo.DatachartBean"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	DatachartBean totalSales = (DatachartBean) request.getAttribute("totalSales");
	ArrayList<ArrayList> regionReservationList = (ArrayList<ArrayList>) request.getAttribute("regionReservationList");
	ArrayList<ArrayList> totalPayCount = (ArrayList<ArrayList>) request.getAttribute("totalPayCount");
	ArrayList<ArrayList> genderPayment = (ArrayList<ArrayList>) request.getAttribute("genderPayment");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="js/jquery-3.4.1.js"></script>
<style type="text/css">
.totalSales{
	color: #0F4C81;
	font-size: 2em;
	font-weight: bold;
}

.totalSalesLabel{
	margin-top: 20px;
}

</style>
</head>

<body>
	<div class="row">
		<div class="col px-md-6 py-1 border rounded">
			<div align="center" class="align-self-center">
				<p class="align-middle totalSalesLabel" >총 결제건수</p>
				<span class="align-middle totalSales"><%=totalSales.getSales()%></span><span style="font-weight:bold; color: #0F4C81;"> 건</span>
			</div>
		</div>

		<div class="col px-md-6 py-1 border rounded">
			<div align="center" class="align-self-center">
				<p class="align-middle totalSalesLabel">총 결제금액</p>
				<span class="align-middle totalSales"><%=String.format("%,d", totalSales.getSalesVolume())%></span><span style="font-weight:bold; color: #0F4C81;"> 원</span>
			</div>
		</div>

		<canvas id="myChart1" style="height: 40vh; width: 80vw"></canvas>

	</div>
	<div class="row">
		<div class="col-6">
			<canvas id="myChart2"></canvas>
		</div>

		<div class="col-6">
			<canvas id="myChart3"></canvas>
		</div>
	</div>

	<div class="row">
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
        
        //  [혼합차트] 일별 결제건수(막대그래프), 금액(선그래프)
        var mixedChart = new Chart(ctx1, {
            type: 'bar',
            data: {
            	labels: <%=totalPayCount.get(0)%>,
                datasets: [{
                    label: '결제금액',
					   yAxisID: 'A',
                    data: <%=totalPayCount.get(1)%>,
                    type: 'line',
                    fill: false,	// 채우기 없음
                    borderColor:'rgba(250, 114, 104, 1)',
                    borderColor:'rgba(250, 114, 104, 1)',
                    borderWidth: 5,
                    order: 1
                },{
                    label: '결제건수',
					   yAxisID: 'B',
                    data: <%=totalPayCount.get(2)%>,
                    backgroundColor: 'rgba(15, 76, 129, 0.7)',
                    borderWidth: 1,
                    order: 2
                }]
            },
            options: {
            	tooltips: {
                    mode: 'index',
                    intersect: false,
                    callbacks: {
                        label: function(tooltipItem, data) {
                            var value = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index];
                            return value.toLocaleString();
                        }
                  }
                 },
                 hover: {
                    mode: 'index',
                    intersect: false
                 },
                maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
                scales: {
                    yAxes: [{
							id: 'A',
							type: 'linear',
              			position: 'right',
                        ticks: {
                            beginAtZero: true,
                            userCallback: function(value, index, values){
                            	return '￦' + value.toLocaleString();
                            }
                        }
                    },{
                    	id: 'B',
                        type: 'linear',
                        position: 'left',
                        ticks: {
                            beginAtZero: true,
                            stepSize: 10,
                            userCallback: function(value, index, values){
                            	return value.toLocaleString() + '건';
                            }
                        }
                    }]
                }
            }
        });
        
        // 원그래프1
        
        var bgcolor = [];
        var bcolor = [];
        
        for (var i=0; i < <%=regionReservationList.size() + 1%> ; i++){
        	var color1=Math.floor(Math.random() * 256);
            var color2=Math.floor(Math.random() * 256);
            var color3=Math.floor(Math.random() * 256);
        	bgcolor[i] = 'rgba('+color1+','+color2+','+color3+',0.5)';
        	bcolor[i] = 'rgba('+color1+','+color2+','+color3+',1)';
        }
        
        var myChart2 = new Chart(ctx2, {
        	plugins: [{
        		// 그래프 안에 제목표시
        		beforeDraw: function(chart) {
        		    var width = chart.chart.width,
        		        height = chart.chart.height,
        		        ctx = chart.chart.ctx,
        		        type = chart.config.type;
        		    	var label = '지역별 예약건수';
        					var oldFill = ctx.fillStyle;
        		      var fontSize = ((height - chart.chartArea.top) / 300).toFixed(2);
        		      ctx.restore();
        		      ctx.font = fontSize + "em sans-serif";
        		      ctx.textBaseline = "middle"
        		      var text = label,
        		          textX = Math.round((width - ctx.measureText(text).width) / 2),
        		          textY = (height + chart.chartArea.top) / 2;
        		      ctx.fillStyle = chart.config.data.datasets[0].backgroundColor[0];
        		      ctx.fillText(text, textX, textY);
        		      ctx.fillStyle = oldFill;
        		      ctx.save();
        		  },
        		  // 그래프에 값 표시
        		afterDatasetsDraw: function(chart) {
          			var ctx = chart.ctx;
          			chart.data.datasets.forEach(function(dataset, i) {
          			  var meta = chart.getDatasetMeta(i);
          			  if (!meta.hidden) {
          				meta.data.forEach(function(element, index) {
          				  // Draw the text in black, with the specified font
          				  ctx.fillStyle = 'rgb(255, 255, 255)';
          				  var fontSize = 15;
          				  var fontStyle = 'normal';
          				  var fontFamily = 'Helvetica Neue';
          				  ctx.font = Chart.helpers.fontString(fontSize, fontStyle, fontFamily);
          				  // Just naively convert to string for now
          				  var dataString = dataset.data[index].toString();
          				  // Make sure alignment settings are correct
          				  ctx.textAlign = 'center';
          				  ctx.textBaseline = 'middle';
          				  var padding = 5;
          				  var position = element.tooltipPosition();
          				  ctx.fillText(dataString, position.x, position.y - (fontSize / 2) - padding);
          				});
          			  }
          			});
          		  }
        	}],
            type: 'doughnut',
            data: {
            		    	labels: <%=regionReservationList.get(0)%>,
            		    datasets: [{
            		        data: <%=regionReservationList.get(1)%>,
            		        backgroundColor: bgcolor,
            		        bodergroundColor: bcolor
            		    }],
            },
            options: {
            	maintainAspectRatio: true
            }
        });
        
     // 원그래프2
        var myChart3 = new Chart(ctx3, {
        	plugins: [{
        		// 그래프에 값 표시
        		afterDatasetsDraw: function(chart) {
          			var ctx = chart.ctx;
          			chart.data.datasets.forEach(function(dataset, i) {
          			  var meta = chart.getDatasetMeta(i);
          			  if (!meta.hidden) {
          				meta.data.forEach(function(element, index) {
          				  // Draw the text in black, with the specified font
          				  ctx.fillStyle = 'rgb(255, 255, 255)';
          				  var fontSize = 15;
          				  var fontStyle = 'normal';
          				  var fontFamily = 'Helvetica Neue';
          				  ctx.font = Chart.helpers.fontString(fontSize, fontStyle, fontFamily);
          				  // Just naively convert to string for now
          				  var dataString = dataset.data[index].toLocaleString();
          				  // Make sure alignment settings are correct
          				  ctx.textAlign = 'center';
          				  ctx.textBaseline = 'middle';
          				  var padding = 5;
          				  var position = element.tooltipPosition();
          				  ctx.fillText(dataString, position.x, position.y - (fontSize / 2) - padding);
          				});
          			  }
          			});
          		  }
        	}],
            type: 'pie',
            data: {
            		    	labels: ['여', '남'] ,
            		    datasets: [{
            		        data: <%=genderPayment.get(0)%>,
            		        backgroundColor: ['rgb(247, 202, 201, 1)', 'rgb(145, 168, 208, 1)'],
//             		        bodergroundColor: bcolor
            		    }],
            },
            options: {
            	maintainAspectRatio: true,
            	tooltips: {
                    mode: 'index',
                    intersect: false,
                    callbacks: {
                        label: function(tooltipItem, data) {
                            var value = data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index];
                            return value.toLocaleString();
                        }
                  }
                 }
            }
        });
        
		// 막대그래프
//         var myChart3 = new Chart(ctx3, {
//             type: 'bar',	// 그래프 타입
//             data: {
<%--                 labels: <%=regionReservationList2.get(0)%>, --%>
//                 datasets: [{
//                     label: '# 지역별 상품 예약 수',			// 그래프 이름
<%--                     data: <%=regionReservationList2.get(1)%>, --%>
//                     backgroundColor: [				// 그래프 배경색
//                         'rgba(255, 99, 132, 0.2)',
//                         'rgba(255, 99, 132, 0.2)',
//                     ],
//                     borderColor: [					// 그래프 선 색
//                         'rgba(255,99,132,1)',
//                         'rgba(255,99,132,1)',
//                     ],
//                     borderWidth: 1	// 그래프 선 굵기
//                 }]
//             },
//             options: {
//                 maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
//                 scales: {
//                     yAxes: [{
//                         ticks: {
//                             beginAtZero: true
//                         }
//                     }]
//                 }
//             }
//         });

        
    </script>
	
</body>
</html>