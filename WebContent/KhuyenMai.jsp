<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="Model.ACCOUNT"%>


<%@page import="Model.Cart"%>
<%@page import="java.util.ArrayList"%>
<!doctype html>
<html lang="en">
<head>
<title>Gin's Chicken</title>
<link rel="shortcut icon" type="image/png" href="../img/logo1.png" />
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="../css/style1.css" media="screen">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


</head>
<body>




	<section class="body_content">

		<div class="mynav">

			<nav class="navbar navbar-expand-md navbar-light nav_edit">

				<a class="navbar-brand" href="#"> <img src="../img/logo1.png"
					class="d-inline-block align-top" alt="">
					<h1>Gin's Chicken</h1>
				</a>

				<button class="navbar-toggler hidden-lg-up " type="button"
					data-toggle="collapse" data-target="#collapsibleNavId"
					aria-controls="collapsibleNavId" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse nav_edit1 "
					id="collapsibleNavId"
					style="margin-top: 80px; margin-right: -15px;">
					<ul class="navbar-nav mr-auto mt-2 mt-lg-0 ">
						<li class="nav-item"><a class="nav-link" href="../TrangChu.jsp">Trang
								chủ</a></li>


						<li class="nav-item"><a class="nav-link"
							href="/ThucDon">Thực đơn </a></li>
						<li class="nav-item main_page"><a class="nav-link"
							href="TinKMServlet/showKM">Khuyến mãi </a></li>
						<li class="nav-item"><a class="nav-link" href="../TinTuc.jsp">Tin
								tức </a></li>
					</ul>

					<!-- <form class="form-inline" action="/action_page.php" style="margin-right: 20px;">
                            <input class="form-control mr-sm-2" type="text" placeholder="Tên món ăn">
                            <button class="btn btn-success" type="submit">Tìm kiếm</button>
                        </form> -->
				</div>
			</nav>
		</div>


		<div class="checkdki-dn ml-auto">

			<c:if test="${tenDN!=null}">
				<p>
					Hello <strong><%=session.getAttribute("tenDN")%></strong> | <a
						href="/LogoutServlet"><i class="fa fa-pencil"></i>
						Đăng Xuất</a>
				</p>
			</c:if>
			<c:if test="${tenDN==null}">

				<a href="../Login.jsp" id="dangNhap"><i class="fa fa-user"></i>
					Đăng Nhập</a> | <a href="#" data-toggle="modal" data-target="#myModal1"
					id="dangKy"><i class="fa fa-pencil"></i> Đăng Ký</a>
				</p>
			</c:if>
		</div>


		<div class="hotline">
			<i class="fa fa-phone-square"> 1800-XXXX</i>
		</div>

		<%
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
		%>
		<div class="check_giohang">

			<a href="../KtraGioHang.jsp"><i class="fa fa-shopping-basket"></i> <%=cart.countItem()%>  GIỎ HÀNG</a>
		</div>

		<!-- form DangNhap DangKy -->
		<!-- <div class="container">

       
                <div class="modal fade" id="myModal">
            
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                
                                 
                                  <div class="modal-header">
                                    <h4 class="modal-title">Đăng Nhập</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                  </div>
                                  
                                  
                                  <div class="modal-body">                                     
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-user"></i></span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="Tên đăng nhập" id="tenDN" name="username" required >
                                    
                                        
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-lock"></i></span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="Mật khẩu" id="mk" name="password" required>
                                     
                                    </div>
                                    </div>
              
                              
                                <div class="modal-footer">                                     
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-arrow-right"></i></button>
                                </div>

                            </div>
                        </div>
                
                            
        </div>
        
        <div class="modal fade" id="myModal1">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
              
                
                <div class="modal-header">
                  <h4 class="modal-title">Đăng Ký</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                
                <div class="modal-body">
                        <div class="form-group">
                                <input id="hoTen" class="form-control" placeholder="Họ và tên" require="">
                        </div> 
                        
                        <div class="form-check-inline" style="margin-right:50px;margin-bottom:15px;">
                            <label class="form-check-label">
                                <input type="radio" style="height:13px; width:13px;" class="form-check-input" name="GioiTinh">Nam
                            </label>
                        </div>
                        <div class="form-check-inline" >
                            <label class="form-check-label" >
                                <input type="radio" style="height:13px; width:13px;" class="form-check-input" name="GioiTinh">Nữ
                            </label>
                        </div>
                        



                        <div class="form-group">
                                <input id="sDt" class="form-control" placeholder="SĐT" require="">
                        </div> 
                        <div class="form-group">
                                <input id="DChi" class="form-control" placeholder="Địa chỉ" require="">
                        </div> 
                        <div class="form-group">
                                <input id="eMail" class="form-control" placeholder="Email" require="">
                        </div> 
                        <div class="form-group">
                                <input id="tenDN" class="form-control" placeholder="Tên đăng nhập" require="">
                        </div> 
                        <div class="form-group">
                                <input id="MK" class="form-control" placeholder="Mật khẩu" required="">
                        </div>
                        <div class="form-group">
                                <input id="MKnhaplai" class="form-control" placeholder="Nhập lại Mật khẩu" required="">
                        </div>
                        
                        
                        
                </div>
                
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-arrow-right"></i></button>
                </div>
                
              </div>
            </div>
        </div>   
           
        



        
        
        
      </div>       -->

		<div class="container">


			<div class="modal fade" id="myModal">

				<div class="modal-dialog modal-dialog-centered ">
					<div class="modal-content">


						<div class="modal-header">
							<h4 class="modal-title">Đăng Nhập</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>


						<div class="modal-body">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user"></i></span>
								</div>
								<input type="text" class="form-control"
									placeholder="Tên đăng nhập" id="tenDN" name="username" required>


							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-lock"></i></span>
								</div>
								<input type="text" class="form-control" placeholder="Mật khẩu"
									id="mk" name="password" required>

							</div>
						</div>


						<div class="modal-footer">
							<a href="#" data-toggle="modal" data-target="#myModalQuenMK"
								data-dismiss="modal" id="quenMK" style="margin-right: 310px;">Quên
								mật khẩu</a>
							<button type="button" class="btn btn-secondary"
								onclick="validateFormDNhap()">
								<i class="fa fa-arrow-right"></i>
							</button>
						</div>

					</div>
				</div>


			</div>

			<div class="modal fade" id="myModal1">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">


						<div class="modal-header">
							<h4 class="modal-title">Đăng Ký</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>


						<div class="modal-body">
							<div class="form-group">
								<input id="hoTen" class="form-control" placeholder="Họ và tên"
									require="">
							</div>



							<div class="form-group">
								<input id="sDt" class="form-control" placeholder="SĐT"
									require="">
							</div>
							<div class="form-group">
								<input id="DChi" class="form-control" placeholder="Địa chỉ"
									require="">
							</div>
							<div class="form-group">
								<input id="eMail" class="form-control" placeholder="Email"
									require="">
							</div>
							<div class="form-group">
								<input id="tenDn" class="form-control"
									placeholder="Tên đăng nhập" require="">
							</div>
							<div class="form-group">
								<input id="MK" class="form-control" placeholder="Mật khẩu"
									required="">
							</div>
							<div class="form-group">
								<input id="MKnhaplai" class="form-control"
									placeholder="Nhập lại Mật khẩu" required="">
							</div>



						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								onclick="validateFormDKy()">
								<i class="fa fa-arrow-right"></i>
							</button>
						</div>

					</div>
				</div>
			</div>


			<div class="modal fade" id="myModalQuenMK">

				<div class="modal-dialog modal-dialog-centered ">
					<div class="modal-content">


						<div class="modal-header">
							<h4 class="modal-title">Quên mật khẩu</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>


						<div class="modal-body">

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fa fa-envelope-o"></i></span>
								</div>
								<input type="text" class="form-control" placeholder="Nhập email"
									id="email" name="email" required>

							</div>


							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fa fa-pencil-square-o"></i></span>
								</div>
								<input type="text" class="form-control" placeholder="Mã code"
									id="code" name="code" required>


							</div>
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-lock"></i></span>
								</div>
								<input type="text" class="form-control"
									placeholder="Mật khẩu mới" id="newpassword" name="newpassword"
									required>

							</div>

							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-lock"></i></span>
								</div>
								<input type="text" class="form-control"
									placeholder="Nhập lại mật khẩu mới" id="newpassword2"
									name="newpassword2" required>

							</div>

						</div>


						<div class="modal-footer">

							<button type="button" class="btn btn-secondary" id="btnQuenMK"
								onclick="validateQuenMK()">
								<i class="fa fa-arrow-right"></i>
							</button>
						</div>

					</div>
				</div>


			</div>

		</div>


		<h2>~ HOT DEAL ~</h2>

		<!-- tin khuyen mai -->
		<div class="container">
			<section class="ShowKM">

				<div class="row">
					<c:forEach var="tinkm" items="${listKM}">
						<div class="col-md-6 ">
							<div class="card" style="margin-bottom: 20px; height: 670px;">

								<img alt="Not found"
									src="https://nhom15fastfood.herokuapp.com/LoadHinhTinKM?maTKM=${tinkm.getMaTKM() }"
									width="489px" height="300px">
								<h4>
									<c:out value="${tinkm.tieuDe}" />
								</h4>

								<p align="justify">
									<c:out value="${tinkm.noiDung}" />
								</p>
								

								<%-- <a style="margin-left: -20px;"
									href="/FastFood/TinKMServlet/showChiTietKM?maTKM=<c:out value="${tinkm.getMaTKM()}" />">
									<button type="button" class="btn btn-warning">Xem ngay</button>
								</a> --%>

							</div>
						</div>
					</c:forEach>


				</div>

			</section>
		</div>




		<!-- form show tin km -->

		<c:if test="${tinKhuyenMai != null }">
			<form method="post" action="/TinKMServlet/showKM">
		</c:if>
		<div class="container">
			<div class="viewmoreKM">
				<div class="modal fade" id="myModalKM1">

					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">


							<div class="modal-header">


								<c:if test="${tinKhuyenMai != null }">
									<h2 class="modal-title" name="MaTKM"
									value="${tinKhuyenMai.getTieuDe()}"></h2>
								</c:if>

								
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>


							<div class="modal-body">
								<p align="justify">
									Gin's Chicken đã trở lại với ưu đãi cực hot!!! <br> Cùng
									tận hưởng COMBO 6 MIẾNG GÀ GIÒN KHÔNG CAY chỉ với 149.000VNĐ <br>
									Chương trình bắt đầu từ 20/10/2018 đến 24/12/2018 với tất cả
									các ngày trong tuần. Không giới hạn số combo cho 1 hóa đơn <br>Còn
									chần chờ gì nữa, mau order để cùng hưởng ưu đãi nào các bạn!! <br>---------
									<br>Điều kiện áp dụng: <br>- Áp dụng đặt hàng online
									qua website Gin's Chicken, Hotline 1800-XXXX <br>- Từ
									20/10 - 24/12/2018
								</p>
								<img src="./img/km149k.png">
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
							</div>

						</div>
					</div>


				</div>



			</div>

		</div>
		</form>



		<footer class="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-4 footCol1">
						<h6>ĐẶT HÀNG</h6>
						<hr>
						<img src="../img/giaohang.png">
					</div>

					<div class="col-md-4 footCol2">
						<h6>GIỚI THIỆU</h6>
						<hr>
						<p style="font-size: 13px;">
							Công ty TNHH GIN'S CHICKEN <br>Địa chỉ: 484 Lê Văn Việt,
							Tăng Nhơn Phú A, quận 9, TP HCM
						</p>
					</div>

					<div class="col-md-4 footCol3">
						<h6>LIÊN HỆ</h6>
						<hr />
						<a href="#"><i class="fa fa-facebook-square"
							style="margin-left: 30px;"></i></a> <a href="#"><i
							class="fa fa-envelope"></i></a> <a href="#"><i
							class="fa fa-google-plus-square"></i></a>
					</div>
				</div>
			</div>

		</footer>
	</section>








	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"
		integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em"
		crossorigin="anonymous"></script>

	<script src="JS/jquery.min.js"></script>
	<script type="text/javascript" src="JS/dki-dnhap.js"></script>
	<script type="text/javascript" src="JS/main.js"></script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</body>
</html>