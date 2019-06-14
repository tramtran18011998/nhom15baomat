<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="Model.ACCOUNT"%>



<%@page import="Model.Cart"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html lang="en">
<head>
<title>Gin's Chicken</title>
<link rel="shortcut icon" type="image/png" href="./img/logo1.png" />
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./css/style1.css" media="screen">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<%-- <%
	ACCOUNT acc = (ACCOUNT) session.getAttribute("tenDN");
%>> --%>

</head>
<body>




	<section class="body_content">
		<div class="mynav">

			<nav class="navbar navbar-expand-md navbar-light nav_edit">

				<a class="navbar-brand" href="#"> <img src="img/logo1.png"
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
						<li class="nav-item main_page">
							<!-- <a class="nav-link"  href="TrangChu.html">Trang chủ <span class="sr-only">(current)</span></a>                                                       -->
							<a class="nav-link" href="TrangChu.jsp">Trang chủ</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="/ThucDon">Thực đơn </a></li>

						<!-- <li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdownId"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Thực
								đơn</a> <a class="nav-link"  href="ThucDon.html">Thực đơn</a>
							<div class="dropdown-menu" aria-labelledby="dropdownId">
								<a class="dropdown-item" href="/ThucDon">**</a> <a
									class="dropdown-item" href="Ga.html">Gà rán + Gà quay</a> <a
									class="dropdown-item" href="Hamburger.html">Hamburger</a> <a
									class="dropdown-item" href="DoAnNhe.html">Đồ ăn nhẹ</a> <a
									class="dropdown-item" href="TrangMieng_ThucUong.html">Tráng
									miệng + Thức uống</a>
							</div></li> -->
						<li class="nav-item"><a class="nav-link"
							href="/TinKMServlet/showKM">Khuyến mãi </a></li>
						<li class="nav-item"><a class="nav-link" href="TinTuc.jsp">Tin
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

				<a href="Login.jsp" id="dangNhap"><i class="fa fa-user"></i>
					Đăng Nhập</a> | <a href="#" data-toggle="modal" data-target="#myModal1"
					id="dangKy"><i class="fa fa-pencil"></i> Đăng Ký</a>
				</p>
			</c:if>
		</div>

		<div class="hotline">
			<i class="fa fa-phone-square"> 1800-XXXX</i>
		</div>

		<!-- <div class="check_giohang">
			<a href="KtraGioHang.html"><i class="fa fa-shopping-basket"></i>
				0 GIỎ HÀNG</a>
		</div> -->
		<%
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
		%>
		<div class="check_giohang">

			<a href="KtraGioHang.jsp"><i class="fa fa-shopping-basket"></i> <%=cart.countItem()%>
				GIỎ HÀNG </a>
		</div>



		<!-- form DangNhap DangKy -->



		<div class="container">

			<!-- <form action="/AccountServlet" method="post">
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
										placeholder="Tên đăng nhập" id="tenDN" name="tenDN" required>


								</div>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="fa fa-lock"></i></span>
									</div>
									<input type="password" class="form-control" placeholder="Mật khẩu"
										id="mK" name="mK" required>

								</div>
							</div>


							<div class="modal-footer">
								<a href="#" data-toggle="modal" data-target="#myModalQuenMK"
									data-dismiss="modal" id="quenMK" style="margin-right: 310px;">Quên
									mật khẩu</a> <input type="hidden" value="Login" name="login">
								<button type="submit" value ="Login" class="btn btn-secondary"
									onclick="validateFormDNhap()"> <i
									class="fa fa-arrow-right"></i> </button>
							</div>

						</div>
					</div>


				</div>

			</form> -->
			<!-- ddawng kys nek  -->

			<form action="/KhachHangServlet/insert" method="post">
				<div class="modal fade" id="myModal1">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">


							<div class="modal-header">
								<h4 class="modal-title">Đăng Ký</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>


							<div class="modal-body">

								<div class="form-group">
									<input id="hoTen" name="hoTen" class="form-control"
										placeholder="Họ và tên" required>
								</div>



								<div class="form-group">
									<input id="sDt" name="sdt" class="form-control"
										placeholder="SĐT" required>
								</div>
								<div class="form-group">
									<input id="DChi" name="diaChi" class="form-control"
										placeholder="Địa chỉ" required>
								</div>
								<div class="form-group">
									<input id="eMail" name="email" class="form-control"
										placeholder="Email" required>
								</div>
								<div class="form-group">
									<input id="tenDn" name="tenDN" class="form-control"
										placeholder="Tên đăng nhập" required>
								</div>
								<div class="form-group">
									<input id="MK" name="mK" class="form-control"
										placeholder="Mật khẩu" required>
								</div>
								<div class="form-group">
									<input id="MKnhaplai" name="mK" class="form-control"
									onchange="ktra()"	placeholder="Nhập lại Mật khẩu" required>
								</div>
								<script type="text/javascript">
									function ktra() {
										var MK = document
												.getElementById("MK").value;
										var MKnhaplai = document
												.getElementById("MKnhaplai").value;
										if(MK!=MKnhaplai)
											{alert('Mật khẩu không đúng');}
										
										var hovaten=document.getElementById('hoTen').value;
									    var sdt=document.getElementById('sDt').value;
									    var dchi=document.getElementById('DChi').value;
									    var mail=document.getElementById('eMail').value;									   
									    var username = document.getElementById('tenDn').value;
									
									    if(hovaten.length==0 || sdt.length==0 ||dchi.length==0 || mail.length==0 || username.length==0 ){
									        alert('Vui lòng nhập đầy đủ thông tin đăng ký');
									    }
									}
								</script>


							</div>


							<div class="modal-footer">
								<button type="submit" class="btn btn-secondary">
									<i class="fa fa-arrow-right"></i>
								</button>

								<!-- onclick="validateFormDKy()" -->
							</div>

						</div>
					</div>
				</div>
			</form>



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






		<!-- slide KhuyenMai -->
		<div class="cover-slide">
			<div id="carouselId" class="carousel slide" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselId" data-slide-to="0" class="active"></li>
					<li data-target="#carouselId" data-slide-to="1"></li>
					<li data-target="#carouselId" data-slide-to="2"></li>
				</ol>

				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img src="./img/km149k.png" alt="First slide">
					</div>
					<div class="carousel-item ">
						<img src="./img/hamChicken.png" alt="Second slide">
					</div>
					<div class="carousel-item">
						<img src="./img/squid.png" alt="Third slide">
					</div>
				</div>

				<a class="carousel-control-prev" href="#carouselId" role="button"
					data-slide="prev"> <!-- <span class="carousel-control-prev-icon" aria-hidden="true" ></span> -->
					<i class="fa fa-chevron-left"></i> <span class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselId" role="button"
					data-slide="next"> <!-- <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
					<i class="fa fa-chevron-right"></i> <span class="sr-only">Next</span>
				</a>
			</div>

		</div>



		<!-- sp noi bat -->
		<div class="container">
			<h3>SẢN PHẨM BÁN CHẠY</h3>
			<section class="ShowSP">

				<div class="row">



					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" src="./img/hamChicken.png"
								alt="Card image">
							<div class="card-body">
								<h4 class="card-title" style="margin-bottom: 40px;">Burger
									Gà</h4>
								<p class="card-text">Giá : 30.000đ</p>
								<a href="/ThucDon" class="btn btn-primary">MÓN KHÁC</a>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" src="./img/3chic.jpg" alt="Card image">
							<div class="card-body">
								<h4 class="card-title">Combo gà giòn cay(3 miếng)</h4>
								<p class="card-text">Giá : 85.000đ</p>
								<a href="/ThucDon" class="btn btn-primary">MÓN KHÁC</a>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" src="./img/familychicOrange.jpg"
								alt="Card image">
							<div class="card-body">
								<h4 class="card-title">Combo gà sốt cam (6 miếng)</h4>
								<p class="card-text">Giá : 160.000đ</p>
								<a href="/ThucDon" class="btn btn-primary">MÓN KHÁC</a>
							</div>
						</div>
					</div>
				</div>

			</section>
		</div>


		<footer class="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-4 footCol1">
						<h6>ĐẶT HÀNG</h6>
						<hr>
						<img src="./img/giaohang.png">
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

	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
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