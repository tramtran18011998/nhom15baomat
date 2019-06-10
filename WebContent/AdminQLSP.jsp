<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="Model.ACCOUNT"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>GinChicken's Admin</title>
<link rel="shortcut icon" type="image/png" href="./img/logo1.png" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!--lấy mỗi cái nút search-->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>

<!-- Custom styles for this template -->
<c:if test="${sanPham == null }">
	<link rel="stylesheet" href="./css/CSS.css">
</c:if>
<c:if test="${sanPham != null }">
	<link rel="stylesheet" href="../css/CSS.css">
</c:if>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div id="header-btn">
				<!-- <a href="Admin_Profile.jsp">
					<button type="button" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-user"></span> Thông tin cá nhân
					</button>
				</a>  -->
				
				<button type="button" class="btn btn-default btn-sm"
					data-toggle="modal" data-target="#myModalExit">
					<span class="glyphicon glyphicon-log-out"></span> Log out
				</button>
			</div>
			<div id="img">
				<c:if test="${sanPham == null }">
					<img src="img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
				<c:if test="${sanPham != null }">
					<img src="../img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
			</div>
			<div id="header">
				<h3>WELCOME ADMIN</h3>
				<h4>GIN'S CHICKEN</h4>
			</div>
		</div>
	</div>
	
	<!--Thoat Admin-->
	<div class="container">
		<div id="myModalExit" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog-exit" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Thông báo</h4>
					</div>
					<div class="modal-body">
						<p>Bạn có muốn thoát không?</p>
					</div>
					<div class="modal-footer">
						<a href="TrangChu.jsp" class="btn btn-primary"> Có</a>
						<button type="button" class="btn btn-default"
							data-dismiss="modal">Không</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="trang">
		<div class="container-fluid">
			<!--toàn trang-->
			<div class="row">
				<!--toàn trang-->

				<div class="nav-collapse sidebar">
					<!--left bar-->
					<ul class="nav nav-sidebar">
						<!-- <li><a href="">Thống Kê</a></li> -->
						<li><a href="KhachHangServlet">Khách hàng</a></li>
						<li><a href="NhanVienServlet">Nhân viên</a></li>
						<li><a href="LoaiSPServlet">Loại sản phẩm</a></li>
						<li><a href="SanPhamServlet">Sản phẩm</a></li>

						<li><a href="HoaDonServlet">Đơn hàng</a></li>
						<li><a href="TinKMServlet">Tin khuyến mãi</a></li>
					</ul>
				</div>


				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

					<div class="QLSP">
						<h1>Quản lý sản phẩm</h1>
					</div>
					<form method="post" class="form-inline"
						action="/SanPhamServlet">
						<nav class="collapse navbar-collapse navbar-ex1-collapse">
							<div class="breadcrumb">
								<div class="topnav-right">
									<div class="search-container">
										<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

											<input class="form-control mr-sm-2" type="text"
												placeholder="Search" name="search" id="search"
												value="${search }">
											<div class="searchbutton">
												<input type="submit" value="Search">
												<!-- <i class="fa fa-search"></i> -->
											</div>
										</nav>
									</div>
								</div>
								<a class="btn btn-warning" href="LoaiSPServlet">Loại sản
									phẩm </a>
								<!--    -->
								<select class="btn btn-success dropdown-toggle" onchange="this.form.submit()"
									name="selectLoaiSP" id="selectLoaiSP">
									<c:forEach var="loaiSP" items="${listLoaiSP}">
										<option value="${loaiSP.getMaLoai()}">
											<c:out value="${loaiSP.getTenLoaiSP()}" /></option>
									</c:forEach>
									<option value="*" selected="selected">default</option>
								</select>
								<script type="text/javascript">
									document.getElementById("selectLoaiSP").value = "${selectLoaiSP}";
								</script>
								<select class="btn dropdown-toggle" onchange="this.form.submit()"
									name="sort" id="sort">
									<option value="AZ">A-Z</option>
									<option value="ZA">Z-A</option>
								</select>
								<script type="text/javascript">
									document.getElementById("sort").value = "${sort}";
								</script>
							</div>
						</nav>
					</form>
					<div class="table-responsive">
						<table class="table table-bordered">

							<tr>

								<th>Mã SP</th>
								<th>Tên SP</th>
								<th>Số lượng</th>
								<th>Giá ban đầu</th>
								<th>Giá bán</th>
								<th>Khuyến mãi</th>
								<th width="110px">Hình</th>
								<th>Mã loại</th>
								<th width="100px">Lựa chọn</th>
							</tr>

							<c:forEach var="SP" items="${listSP}">
								<tr>
									<td><c:out value="${SP.getMaSP()}" /></td>
									<td><c:out value="${SP.getTenSP()}" /></td>
									<td><c:out value="${SP.getSoLuong()}" /></td>
									<td><c:out value="${SP.getGiaBanDau()}" /></td>
									<td><c:out value="${SP.getGiaBan()}" /></td>
									<td><c:out value="${SP.getKhuyenMai()}" /></td>
									<td><img alt="Not found"
										src="https://nhom15fastfood.herokuapp.com/LoadHinh?maSP=${SP.getMaSP() }"
										width="100px" height="100px"></td>


									<td><c:forEach var="loaiSP" items="${listLoaiSP }">
											<c:if test="${SP.getMaLoai() == loaiSP.getMaLoai() }">
												<c:out value="${loaiSP.getTenLoaiSP()}" />
											</c:if>
										</c:forEach></td>
									<%-- <td><c:out value="${SP.getMaLoai()}" /></td> --%>




									<td width="75px"><a
										href="/SanPhamServlet/edit?maSP=<c:out value="${SP.getMaSP()}" />"
										class="edit"> <span class="glyphicon glyphicon-edit"></span></a>
										<a
										href="/SanPhamServlet/delete?maSP=<c:out value="${SP.getMaSP()}" />"
										class="delete"> <span
											class="glyphicon glyphicon-remove-sign"></span>
									</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!-- <ul class="pager">
						<li class="previous"><a href="#">Previous</a></li>
						<li class="next"><a href="#">Next</a></li>
					</ul> -->


					<div class="container">
						<div class="">
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#myModal">Thêm sản phẩm</button>
						</div>
					</div>



					<c:if test="${sanPham == null }">
						<form enctype="multipart/form-data" method="post"
							action="/SanPhamServlet/insert">
					</c:if>
					<c:if test="${sanPham != null }">
						<form enctype="multipart/form-data" method="post"
							action="/SanPhamServlet/update">
					</c:if>
					<!-- The Modal THEM-->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">

								<!-- Modal Header -->
								<c:if test="${sanPham == null }">
									<div class="modal-header">
										<h3 class="modal-title">Thêm sản phẩm</h3>
									</div>
								</c:if>
								<c:if test="${sanPham != null }">
									<div class="modal-header">
										<h3 class="modal-title">Sửa sản phẩm</h3>
									</div>
								</c:if>
								<!-- Modal body -->

								<div class="modal-body">
									<div class="">
										<div class="form_row">
											<c:if test="${sanPham != null }">
												<label>Mã sản phẩm:</label>
												<input type="text" class="form_input" name="txtMaSP"
													required="required" readonly="readonly"
													value="${sanPham.getMaSP() }" />
											</c:if>
											<c:if test="${sanPham == null }">
												<label>Mã sản phẩm:</label>
												<input type="text" class="form_input" name="txtMaSP"
													required="required" readonly="readonly" value="${maSPNew}" />
											</c:if>
										</div>
										<div class="form_row">
											<label>Tên sản phẩm :</label> <input type="text"
												class="form_input" name="txtTenSP" required="required"
												value="${sanPham.getTenSP() }" />
										</div>
										<div class="form_row">
											<label> Số lượng:</label> <input type="number" step="1"
												min="1" max="200" class="form_input" name="txtSoLuong"
												required="required" value="${sanPham.getSoLuong() }" />
										</div>
										<div class="form_row">
											<label> Giá ban đầu:</label> <input type="number" step="1000"
												onchange="tinhGiaBan()" min="1000" max="100000000"
												class="form_input" id="giaBanDau" name="txtGiaBanDau"
												required="required" value="${sanPham.getGiaBanDau() }" />
										</div>
										<div class="form_row">
											<label> Khuyến mãi:</label> <input type="number" step="1"
												onchange="tinhGiaBan()" min="0" max="99" class="form_input"
												name="txtKhuyenMai" id="khuyenMai" required="required"
												value="${sanPham.getKhuyenMai() }" />
										</div>
										<div class="form_row">
											<label> Giá bán ra:</label> <input type="number" step="1000"
												readonly="readonly" min="1000" max="1000000000"
												class="form_input" id="giaBan" name="txtGiaBan"
												required="required" value="${sanPham.getGiaBan() }" />
										</div>

										<script type="text/javascript">
											function tinhGiaBan() {
												var khuyenMai = document
														.getElementById("khuyenMai").value;
												var giaBanDau = document
														.getElementById("giaBanDau").value;
												document
														.getElementById("giaBan").value = giaBanDau
														- giaBanDau
														* (khuyenMai * 0.01);
											}
										</script>


										<div class="form_row">
											<label> Mã loại:</label> <select class="form_input"
												id="selectMaLoai" name="selectMaLoai"
												onchange="changeLoaiSP()">
												<c:forEach var="loaiSP" items="${listLoaiSP}">
													<option value="${loaiSP.getMaLoai()}">
														<c:out value="${loaiSP.getTenLoaiSP()}" /></option>
												</c:forEach>
											</select><br>
											<script type="text/javascript">
												function changeLoaiSP() {
													document
															.getElementById("txtMaLoai").value = document
															.getElementById("selectMaLoai").value
												}
											</script>
										</div>
										<div class="form_row">
											<label> Hình ảnh:</label> <input type="file" alt="accept"
												class="form_input" name="imageHinh" accept="image/*"
												value="${dauSachIU.getHinhBlob() }"
												enctype="multipart/form-data">
											<!---->
										</div>
									</div>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<div class="pull-right">
										<c:if test="${sanPham == null }">
											<input type="submit" class="btn btn-success" value="Thêm Mới"></input>
										</c:if>
										<c:if test="${sanPham != null }">
											<input type="submit" class="btn btn-success"
												value="Sửa Sản Phẩm"></input>
										</c:if>

									</div>

									<button type="button" class="btn btn-secondary pull-left"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>

					</div>
					</form>

					<!-- <div class="container">
						<div id="myModalXoaHD" class="modal fade" tabindex="-1"
							role="dialog">
							<div class="modal-dialog-delete" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">Thông báo</h4>
									</div>
									<div class="modal-body">
										<p>Bạn có muốn xóa sản phẩm này không?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="DongYXoa()">Có</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Không</button>
									</div>
								</div>
							</div>
						</div>
					</div> -->



				</div>
			</div>
		</div>
	</div>
	<c:if test="${sanPham != null }">
		<script type="text/javascript">
			$('#myModal').modal('show');
		</script>
	</c:if>
	<!-- 
	<script>
		/* type = "text/javascript" > $.validator.setDefaults({
			submitHandler : function() {
				alert("Thêm sản phẩm thành công!");
			}
		}); */

		$(document).ready(function() {
			$("#form_Them").validate({
				rules : {
					masp : {
						required : true,
					},
					tensp : {
						required : true,
					},
					soluong : {
						required : true,
					},
					giadau : {
						required : true,
					},
					khuyenmai : {
						required : true,
					},
					giaban : {
						required : true,
					},
					agree : "required"
				},
				messages : {
					masp : {
						required : "This field is required",
					},
					tensp : {
						required : "This field is required",
					},
					soluong : {
						required : "This field is required",
					},
					giadau : {
						required : "This field is required",
					},
					khuyenmai : {
						required : "This field is required",
					},
					giaban : {
						required : "This field is required",
					},
					agree : "Please accept our policy"
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");

					if (element.prop("type") === "checkbox") {
						error.insertAfter(element.parent("label"));
					} else {
						error.insertAfter(element);
					}
				},
			});
		});
	</script>
	validate sửa
	<script>
		/* type = "text/javascript" > $.validator.setDefaults({
			submitHandler : function() {
				alert("Thành công!");
			}
		}); */

		$(document).ready(function() {
			$("#form_Sua").validate({
				rules : {
					masp : {
						required : true,
					},
					tensp : {
						required : true,
					},
					soluong : {
						required : true,
					},
					giadau : {
						required : true,
					},
					khuyenmai : {
						required : true,
					},
					giaban : {
						required : true,
					},
					agree : "required"
				},
				messages : {
					masp : {
						required : "This field is required",
					},
					tensp : {
						required : "This field is required",
					},
					soluong : {
						required : "This field is required",
					},
					giadau : {
						required : "This field is required",
					},
					khuyenmai : {
						required : "This field is required",
					},
					giaban : {
						required : "This field is required",
					},
					agree : "Please accept our policy"
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");

					if (element.prop("type") === "checkbox") {
						error.insertAfter(element.parent("label"));
					} else {
						error.insertAfter(element);
					}
				},
			});
		});
	</script> -->
</body>

</html>