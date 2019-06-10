<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>GinChicken's Admin</title>
<link rel="shortcut icon" type="image/png" href="./img/logo1.png" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<!-- Custom styles for this template -->
<c:if test="${nhanVien == null }">
	<link rel="stylesheet" href="./css/CSS.css">
</c:if>
<c:if test="${nhanVien != null }">
	<link rel="stylesheet" href="../css/CSS.css">
</c:if>
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div id="header-btn">


				<button type="button" class="btn btn-default btn-sm"
					data-toggle="modal" data-target="#myModalExit">
					<span class="glyphicon glyphicon-log-out"></span> Log out
				</button>

			</div>
			<div id="img">
				<c:if test="${nhanVien == null }">
					<img src="img/logo1.png" class="d-inline-block align-top"
						width="100" height="85">
				</c:if>
				<c:if test="${nhanVien != null }">
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
						<button type="button" class="btn btn-default" data-dismiss="modal">Không</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
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
					<h1>Quản lý nhân viên</h1>
				</div>
				<!--Tìm kiếm-->
				<!-- <div class="col-search">

					<div class="search">
						<h4>
							Tìm kiếm theo: <select class="selectSearch" name="browser">
								<option class="optionSearch">Mã nhân viên</option>
								<option class="optionSearch">Họ tên nhân viên</option>
								<option class="optionSearch">Địa chỉ</option>
							</select>
						</h4>

					</div>

					<div class="input-group formSearch">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default btnSearch" type="button">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>

					</div>
				</div> -->
				<div class="table-responsive">
					<table class="table table-bordered">

						<tr>
							<th>Mã NV</th>
							<th>Họ tên</th>
							<th>Giới tính</th>
							<th>Địa chỉ</th>
							<th>Email</th>
							<th>Số điện thoại</th>
							<th>Tên đăng nhập</th>
							<th>Lựa chọn</th>
						</tr>

						<c:forEach var="nv" items="${listNV}">
							<tr>
								<td><c:out value="${nv.getMaNV()}" /></td>
								<td><c:out value="${nv.getHoTen()}" /></td>
								<td><c:out value="${nv.getGioiTinh()} " /></td>
								<td><c:out value="${nv.getDiaChi()} " /></td>
								<td><c:out value="${nv.getEmail()} " /></td>
								<td><c:out value="${nv.getSdt()} " /></td>
								<td><c:out value="${nv.getTenDN()} " /></td>
								<td><a
									href="/NhanVienServlet/edit?maNV=<c:out value="${nv.getMaNV()}" />"
									class="edit"> <span class="glyphicon glyphicon-edit"></span></a>
									<a
									href="/NhanVienServlet/delete?maNV=<c:out value="${nv.getMaNV()}" />"
									class="delete"> <span
										class="glyphicon glyphicon-remove-sign"></span>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>



				<%-- <!-- PHAN TRANG -->


				<div class="pull-left">
					<div class="col-xs-12">
						<div class="shop-pagination pull-right">
							<ul id="" class="pagination-sm pagination">
								<li class="page-item first"><a
									href="/Version3/AdminQLNV?pages=1" class="page-link">First</a></li>
								<c:if test="${soTrangHienTai >=2 }">
									<li class="page-item prev"><a
										href="/Version3/AdminQLNV?pages=<c:out value='${soTrangHienTai-1}'/>"
										class="page-link">Previous</a></li>
								</c:if>
								<c:forEach var="i" begin="1" end="${soTrang}" step="1">
									<c:if test="${soTrangHienTai == i }">
										<li class="page-item active"><a
											href="/Version3/AdminQLNV?pages=<c:out value='${i}'/>"
											class="page-link"> <c:out value="${i}"></c:out></a></li>
									</c:if>
									<c:if test="${soTrangHienTai != i }">
										<li class="page-item  "><a
											href="/Version3/AdminQLNV?pages=<c:out value='${i}'/>"
											class="page-link"><c:out value="${i}"></c:out></a></li>
									</c:if>
								</c:forEach>
								<c:if test="${soTrangHienTai < soTrang }">
									<li class="page-item next"><a
										href="/Version3/AdminQLNV?pages=<c:out value='${soTrangHienTai+1}'/>"
										class="page-link">Next</a></li>
								</c:if>
								<li class="page-item last"><a
									href="/Version3/AdminQLNV?pages=<c:out value='${soTrang}'/>"
									class="page-link">Last</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- end phaan trang --> --%>

				<div class="container">
					<div class="btnThem">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#myModal">Thêm nhân viên</button>
					</div>
				</div>

				<c:if test="${nhanVien == null }">
					<form method="post" action="/NhanVienServlet/insert">
				</c:if>
				<c:if test="${nhanVien != null }">
					<form method="post" action="/NhanVienServlet/update">
				</c:if>
				<!--Themnhanvien-->
				<div class="modal fade" id="myModal">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<c:if test="${nhanVien == null }">
								<div class="modal-header">
									<h3 class="modal-title">Thêm nhân viên</h3>
								</div>
							</c:if>
							<c:if test="${nhanVien != null }">
								<div class="modal-header">
									<h3 class="modal-title">Sửa nhân viên</h3>
								</div>
							</c:if>

							<div class="modal-body">
								<div class="">
									<div class="form_row">
										<c:if test="${nhanVien != null }">
											<label>Mã nhân viên:</label>
											<input type="text" class="form_input" name="maNV"
												required="required" readonly="readonly"
												value="${nhanVien.getMaNV() }" />
										</c:if>
										<c:if test="${nhanVien == null }">
											<label>Mã nhân viên:</label>
											<input type="text" class="form_input" name="maNV"
												required="required" readonly="readonly" value="${maNVNew}" />
										</c:if>
									</div>

									<div class="form_row">
										<label>Tên nhân viên :</label> <input type="text"
											class="form_input" name="tenNV" required="required"
											value="${nhanVien.getHoTen() }" />
									</div>

									<div class="form_row">
										<label>Giới tính:</label>
										<form action="">
											<input type="radio" name="gioiTinh" value="Nam"> Nam<br>
											<input type="radio" name="gioiTinh" value="Nữ"> Nữ<br>

										</form>
									</div>
									<div class="form_row">
										<label>Địa chỉ :</label> <input type="text" class="form_input"
											name="diaChi" required="required"
											value="${nhanVien.getDiaChi() }" />
									</div>
									<div class="form_row">
										<label> Email :</label> <input type="text" class="form_input"
											name="email" required="required"
											value="${nhanVien.getEmail() }" />
									</div>
									<div class="form_row">
										<label> Số điện thoại :</label> <input type="text"
											class="form_input" name="sdt" required="required"
											value="${nhanVien.getSdt() }" />
									</div>
									<div class="form_row">
										<label> Tên đăng nhập :</label> <input type="text"
											class="form_input" name="tenDN" required="required"
											value="${nhanVien.getTenDN() }" />
									</div>
									<div class="form_row">
										<label>Mat khau:</label> <input type="text" class="form_input"
											name="mK" id="mK" />
									</div>

									<div class="form_row">
										<label>Quyen han:</label> <input type="radio" name="quyenHan"
											value="Admin" />Admin <br> <input type="radio"
											name="quyenHan" value="User" />User <br>
									</div>



								</div>
							</div>
							<!-- Modal footer -->
							<div class="modal-footer">
								<div class="pull-right">
									<c:if test="${nhanVien == null }">
										<input type="submit" class="btn btn-success" value="Thêm Mới"></input>
									</c:if>
									<c:if test="${nhanVien != null }">
										<input type="submit" class="btn btn-success"
											value="Sửa Nhân viên"></input>
									</c:if>

								</div>
								<button type="button" class="btn btn-secondary pull-left"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!--  -->
				</form>


				<!--Xoa nhan vien-->
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
										<p>Bạn có muốn xóa nhân viên này không?</p>
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

	<c:if test="${nhanVien != null }">
		<script type="text/javascript">
			jQuery.noConflict();
			$('#myModal').modal('show');
		</script>
	</c:if>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./JS/jquery.min.js"></script>
	<script src="./JS/bootstrap.min.js"></script>
	<script src="./JS/docs.min.js"></script>
	<script src="./JS/admin.js"></script>
</body>

</html>