<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GinChicken's Admin</title>
<link rel="shortcut icon" type="image/png" href="./img/logo1.png"/>
    
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="./css/CSS.css">
    <!--  -->
    <script >
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {
	        theme: "light2",
	        animationEnabled: true,
	        title:{
		        text: "Doanh thu 10 tháng 2018"   
	        },
	    axisX: {
		interval: 1,
		intervalType: "month",
		valueFormatString: "MMM"
        },
        axisY:{
            
            valueFormatString: "#0 triệu"
        },
	    data: [{        
            type: "line",
            markerSize: 12,
            xValueFormatString: "MMM, YYYY",
            yValueFormatString: "VND###.#",
            dataPoints: [        
                { x: new Date(2018, 00, 1), y: 26 },
                { x: new Date(2018, 01, 1), y: 31 },
                { x: new Date(2018, 02, 1) , y: 31 },
                { x: new Date(2018, 03, 1) , y: 37 },
                { x: new Date(2018, 04, 1) , y: 32 },
                { x: new Date(2018, 05, 1) , y: 39 },
                { x: new Date(2018, 06, 1) , y: 35 },
                { x: new Date(2018, 07, 1) , y: 25 },
                { x: new Date(2018, 08, 1) , y: 34 },
                { x: new Date(2018, 09, 1) , y: 37}
                
            ]
        }]
    });
        chart.render();
        
     }
    </script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div id="header-btn">	
                <a href="Admin_Profile.jsp">
                <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-user"></span> Thông tin cá nhân 
                </button> </a>
                <a href="">
                <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-log-out"></span> Log out 
                </button></a>			 
            </div>
            <div id="img">
                <img src="img/logo.png"  class="d-inline-block align-top" width="100" height="85">		
            </div>
            <div id="header">
                <h3> WELCOME ADMIN</h3>
                <h4> GIN'S CHICKEN</h4>				
             </div>
         </div>		
    </div>
    
    
    <div class="trang">
    <div class="container-fluid"> <!--toàn trang-->
        <div class="row"> <!--toàn trang-->
            
            <div class="col-sm-3 col-md-2 sidebar"><!--left bar-->
                <ul class="nav nav-sidebar">
                    <li><a href="AdminThongKe.jsp">Thống Kê</a></li>
                    <li><a href="AdminQLKH.jsp">Khách hàng</a></li>
                    <li><a href="AdminQLNV.jsp">Nhân viên</a></li>
                    <li><a href="AdminQLSP.jsp">Sản phẩm</a></li>
                    
                    <li><a href="AdminQLHD.jsp">Đơn hàng</a></li>
                    <li><a href="AdminTinKM.jsp">Tin khuyến mãi</a></li>
                </ul>
            </div>
            
        
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div>
                    <div class="QLSP"><h1>Thống kê</h1> </div>
                    
                </div>
                <!-- chart start  -->
                <div id="chartContainer" style="height: 500px; width: 100%;"></div>
                
                <!-- end -->
                
                
            </div>
        </div>
    </div>
</div>
 
<!-- chartjs -->
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>

<script src="assets/chart-master/Chart.js"></script>
<!-- custom chart script for this page only-->
<script src="JS/chartjs-custom.js"></script>
</body>
</html>