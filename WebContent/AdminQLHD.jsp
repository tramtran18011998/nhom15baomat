<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

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
<link rel="stylesheet" href="./css/CSS.css">
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
				<img src="img/logo1.png" class="d-inline-block align-top"
					width="100" height="85">
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
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
					<h1>Danh sách hóa đơn</h1>

				</div>
				<!-- <div class="col-search">
					<div class="input-group formSearch">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div> -->
				<div class="table-responsive">

					<table class="table table-bordered">

						<tr>
							<th width="80px">Mã hóa đơn</th>
							<th>Mã khách hàng</th>
							<th>Mã Nhân Viên</th>
							<th>Ngày lập</th>
							<th>Tổng tiền</th>
							<th>Trạng thái</th>
							<th>Lựa chọn</th>
						</tr>
						<c:forEach var="hd" items="${listHD}">
							<tr>
								<td><c:out value="${hd.maHD}" /></td>
								<td><c:out value="${hd.maKH}" /></td>
								<td><c:out value="${hd.maNV}" /></td>
								<td><c:out value="${hd.ngayLap}" /></td>
								<td><c:out value="${hd.tongTien}" /></td>
								<td><c:out value="${hd.trangThai}" /></td>
								<td width="120px"><a
									<%-- href="/LoaiSPServlet/edit?maLoai=<c:out value="${loaisp.getMaLoai()}" />" --%>
										class="edit">
										<span class="glyphicon glyphicon-edit"></span>
								</a> <a
									href="/HoaDonServlet/delete?maHD=<c:out value="${hd.maHD}" />"
									class="delete"> <span
										class="glyphicon glyphicon-remove-sign"></span>
								</a></td>
								
								<%-- <td width="120px"><a
									href="/FastFood/LoaiSPServlet/edit?maLoai=<c:out value="${loaisp.getMaLoai()}" />"
										class="edit">
										<span class="glyphicon glyphicon-edit"></span>
								</a> <a									
									class="delete" data-toggle="modal" data-target="#myModalXoaHD"> <span
										class="glyphicon glyphicon-remove-sign"></span>
								</a></td> --%>
								
								<!-- <td width="120px"><a class="edit" data-toggle="modal"
										data-target="#ModalSua"> <span
											class="glyphicon glyphicon-edit"></span></a> 
											<a class="delete"
										data-toggle="modal" data-target="#myModalXoaHD"> <span
											class="glyphicon glyphicon-remove-sign"></span>
									</a></td> -->
							</tr>
						</c:forEach>

					</table>
				</div>

			</div>
			<!--show chi tiet hoa don-->
			<div class="container">
				<div class="modal fade" id="myModalHD1" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Chi tiết hóa đơn</h4>
							</div>
							<div class="modal-body">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>STT</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Đơn giá</th>
											<th>Thành tiền</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Hamburger</td>
											<td>2</td>
											<td>30.000 VND</td>
											<td>60.000 VND</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Đùi gà không cay</td>
											<td>2</td>
											<td>30.000 VND</td>
											<td>60.000 VND</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="myModalHD2" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Chi tiết hóa đơn</h4>
							</div>
							<div class="modal-body">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>STT</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Đơn giá</th>
											<th>Thành tiền</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Gà giòn cay</td>
											<td>3</td>
											<td>19.000 VND</td>
											<td>57.000 VND</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Hamburger bò</td>
											<td>1</td>
											<td>35.000 VND</td>
											<td>35.000 VND</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="myModalHD3" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Chi tiết hóa đơn</h4>
							</div>
							<div class="modal-body">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>STT</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Đơn giá</th>
											<th>Thành tiền</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Combo gà 4</td>
											<td>1</td>
											<td>120.000 VND</td>
											<td>120.000 VND</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Peppsi</td>
											<td>1</td>
											<td>15.000 VND</td>
											<td>15.000 VND</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="myModalHD4" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Chi tiết hóa đơn</h4>
							</div>
							<div class="modal-body">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>STT</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Đơn giá</th>
											<th>Thành tiền</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Gà giòn</td>
											<td>2</td>
											<td>30.000 VND</td>
											<td>60.000 VND</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Khoai tây chiên</td>
											<td>1</td>
											<td>30.000 VND</td>
											<td>30.000 VND</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Kem dâu</td>
											<td>2</td>
											<td>25.000 VND</td>
											<td>50.000 VND</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--Xoa hoa don-->
			<div class="container">
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
								<p>Bạn có muốn xóa hóa đơn này không?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									href="/HoaDonServlet/delete?maHD=<c:out value="${hoaDon.getMaHD()}" />">Có</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Không</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
            <script>
        function DongYXoa() {
            window.location.href = "./AdminQLHD.html"
        }
    </script>
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- 	<script src="./JS/jquery.min.js"></script>
	<script src="./JS/bootstrap.min.js"></script>
	<script src="./JS/docs.min.js"></script>
	<script src="./JS/admin.js"></script> -->

</body>

</html>