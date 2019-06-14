<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
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

<c:if test="${tinKhuyenMai == null }">
	<link rel="stylesheet" href="./css/CSS.css">
</c:if>
<c:if test="${tinKhuyenMai != null }">
	<link rel="stylesheet" href="../css/CSS.css">
</c:if>

</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div id="header-btn">
				<div id="header-btn">


				<button type="button" class="btn btn-default btn-sm"
					data-toggle="modal" data-target="#myModalExit">
					<span class="glyphicon glyphicon-log-out"></span> Log out
				</button>

			</div>
			</div>
			<div id="img">
				<c:if test="${tinKhuyenMai == null }">
					<img src="img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
				<c:if test="${tinKhuyenMai != null }">
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
						<button type=" button" class="btn btn-default"
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

				<div class="col-sm-3 col-md-2 sidebar">
					<!--left bar-->
					<ul class="nav nav-sidebar">
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
						<h1>Quản lý tin khuyến mãi</h1>
					</div>
					<!-- <nav class="collapse navbar-collapse navbar-ex1-collapse">
						<div class="breadcrumb">
							<div class="topnav-right">
								<div class="search-container">
									<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
										<form class="form-inline" action="/action_page.php">
											<input class="form-control mr-sm-2" type="text"
												placeholder="Search">
											<div class="searchbutton">
												<button type="submit">
													<i class="fa fa-search"></i>
												</button>
											</div>
										</form>
									</nav>
								</div>

							</div>

							<div class="btn-group">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#myModal">Thêm Tin
									khuyến mãi</button>
							</div>
						</div>
					</nav> -->


					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th width="100px">Mã tin KM</th>
									<th>Tiêu đề</th>
									<th>Nội dung</th>

									<th width="110px">Hình ảnh</th>
									<th width="100px">Lựa chọn</th>
								</tr>
							</thead>

							<c:forEach var="tinkm" items="${listKM}">
								<tr>
									<td><c:out value="${tinkm.maTKM}" /></td>
									<td><c:out value="${tinkm.tieuDe}" /></td>
									<td><c:out value="${tinkm.noiDung}" /></td>
									<td><img alt="Not found"
										src="https://nhom15fastfood2.herokuapp.com/LoadHinhTinKM?maTKM=${tinkm.getMaTKM() }"
										width="100px" height="100px"></td>


									<td width="75px"><a
										href="/TinKMServlet/edit?maTKM=<c:out value="${tinkm.getMaTKM()}" />"
										class="edit"> <span class="glyphicon glyphicon-edit"></span></a>
										
								
										
										<a
										href="/TinKMServlet/delete?maTKM=<c:out value="${tinkm.getMaTKM()}" />"
										class="delete"> <span
											class="glyphicon glyphicon-remove-sign"></span>
									</a></td>

								</tr>

							</c:forEach>



						</table>
					</div>

					<div class="container">
						<div class="">
							<button type="button" class="btn btn-primary" data-toggle="modal"
								data-target="#myModal">Thêm Tin Khuyen mai</button>
						</div>
					</div>


					<c:if test="${tinKhuyenMai == null }">
						<form enctype="multipart/form-data" method="post"
							action="/TinKMServlet/insert">
					</c:if>
					<c:if test="${tinKhuyenMai != null }">
						<form enctype="multipart/form-data" method="post"
							action="/TinKMServlet/update">
					</c:if>
					<!-- The Modal THEM-->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">

								<!-- Modal Header -->
								<c:if test="${tinKhuyenMai == null }">
									<div class="modal-header">
										<h3 class="modal-title">Thêm tin khuyến mãi</h3>
									</div>
								</c:if>
								<c:if test="${tinKhuyenMai != null }">
									<div class="modal-header">
										<h3 class="modal-title">Sửa tin khuyến mãi</h3>
									</div>
								</c:if>
								<!-- Modal body -->

								<div class="modal-body">
									<div class="">
										<div class="form_row">
											<c:if test="${tinKhuyenMai != null }">
												<label>Mã tin:</label>
												<input type="text" class="form_input" name="txtMaTKM"
													required="required" readonly="readonly"
													value="${tinKhuyenMai.getMaTKM() }" />
											</c:if>
											<c:if test="${tinKhuyenMai == null }">
												<label>Mã tin KM:</label>
												<input type="text" class="form_input" name="txtMaTKM"
													required="required" readonly="readonly" value="${maTKMNew}" />
											</c:if>
										</div>
										<div class="form_row">
											<label>Tiêu đề :</label> <input type="text"
												class="form_input" name="txtTieuDe" required="required"
												value="${tinKhuyenMai.getTieuDe() }" />
										</div>
										<div class="form_row">
											<label>Nội dung :</label> <input type="text"
												class="form_input" name="txtNoiDung" required="required"
												value="${tinKhuyenMai.getNoiDung() }" />
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
										<c:if test="${tinKhuyenMai == null }">
											<input type="submit" class="btn btn-success" value="Thêm Mới"></input>
										</c:if>
										<c:if test="${tinKhuyenMai != null }">
											<input type="submit" class="btn btn-success" value="Sửa "></input>
										</c:if>

									</div>

									<button type="button" class="btn btn-secondary pull-left"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>

					</div>
					</form>





				</div>
			</div>
		</div>
	</div>
	<%-- <c:if test="${tinKhuyenMai != null }">
		<script type="text/javascript">
			$('#myModal').modal('show');
		</script>
	</c:if> --%>

	<!-- <script>
		type = "text/javascript" > $.validator.setDefaults({
			submitHandler : function() {
				alert("Thành công!");
			}
		});

		$(document).ready(function() {
			$("#form_Them").validate({
				rules : {
					makm : {
						required : true,

					},
					tieude : {
						required : true,

					},
					noidung : {
						required : true,

					},
					ngaydau : {
						required : true,

					},
					ngayket : {
						required : true,

					},

					agree : "required"
				},
				messages : {
					makm : {
						required : "This field is required",
					},
					tieude : {
						required : "This field is required",
					},
					noidung : {
						required : "This field is required",
					},
					ngaydau : {
						required : "This field is required",
					},
					ngayket : {
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
		type = "text/javascript" > $.validator.setDefaults({
			submitHandler : function() {
				alert("Thành công!");
			}
		});

		$(document).ready(function() {
			$("#form_Sua").validate({
				rules : {
					makm : {
						required : true,
					},
					tieude : {
						required : true,
					},
					noidung : {
						required : true,
					},
					ngaydau : {
						required : true,
					},
					ngayket : {
						required : true,
					},
					agree : "required"
				},
				messages : {
					makm : {
						required : "This field is required",
					},
					tieude : {
						required : "This field is required",
					},
					noidung : {
						required : "This field is required",
					},
					ngaydau : {
						required : "This field is required",
					},
					ngayket : {
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


	<c:if test="${tinKhuyenMai != null }">
		<script type="text/javascript">
			$('#myModal').modal('show');
		</script>
	</c:if>
	
	<!-- <div class="modal-backdrop fade in"></div> -->
</body>

</html>