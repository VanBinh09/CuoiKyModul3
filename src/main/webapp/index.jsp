<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý thuê phòng trọ</title>
    <link href="assets/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="assets/bootstrap/bootstrap.bundle.js"></script>
</head>
<body class="bg-light">
<div class="container py-4">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="fw-bold text-primary"> Danh sách thuê phòng trọ</h3>
        <div>
            <form class="d-flex" action="rooms" method="get">
                <input type="hidden" name="action" value="search">
                <input class="form-control me-2" type="search" name="keyword" placeholder="Tìm kiếm...">
                <button class="btn btn-outline-primary" type="submit"></button>
            </form>
        </div>
    </div>

    <div class="mb-3">
        <a href="rooms?action=new" class="btn btn-success me-2"> Tạo mới</a>
        <button class="btn btn-danger" id="deleteSelectedBtn"> Xóa đã chọn</button>
    </div>

    <form id="deleteForm" action="rooms" method="get">
        <input type="hidden" name="action" value="delete">
        <table class="table table-striped table-hover align-middle bg-white shadow-sm">
            <thead class="table-primary">
            <tr>
                <th scope="col"><input type="checkbox" id="checkAll"></th>
                <th scope="col">Mã phòng</th>
                <th scope="col">Tên người thuê</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Ngày bắt đầu</th>
                <th scope="col">Hình thức thanh toán</th>
                <th scope="col">Ghi chú</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="room" items="${listRooms}">
                <tr>
                    <td><input type="checkbox" name="selectedIds" value="${room.id}"></td>
                    <td>PT-${room.id}</td>
                    <td>${room.tenNguoiThue}</td>
                    <td>${room.soDienThoai}</td>
                    <td>${room.ngayBatDau}</td>
                    <td>${room.hinhThucThanhToan}</td>
                    <td>${room.ghiChu}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

    <!-- Modal xác nhận xóa -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title">Xác nhận xóa</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body" id="confirmMessage">
                    Bạn có chắc chắn muốn xóa thông tin thuê trọ đã chọn?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Xóa</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    // Chọn tất cả checkbox
    document.getElementById("checkAll").addEventListener("change", function () {
        const checkboxes = document.querySelectorAll("input[name='selectedIds']");
        checkboxes.forEach(cb => cb.checked = this.checked);
    });

    // Hiển thị modal xác nhận xóa
    document.getElementById("deleteSelectedBtn").addEventListener("click", function () {
        const selected = Array.from(document.querySelectorAll("input[name='selectedIds']:checked"));
        if (selected.length === 0) {
            alert("Vui lòng chọn ít nhất một hàng để xóa!");
            return;
        }

        const ids = selected.map(cb => "PT-" + cb.value).join(", ");
        document.getElementById("confirmMessage").innerText =
            "Bạn có muốn xóa thông tin thuê trọ " + ids + " hay không?";
        const modal = new bootstrap.Modal(document.getElementById("confirmDeleteModal"));
        modal.show();

        // Khi người dùng nhấn "Xóa" trong modal
        document.getElementById("confirmDeleteBtn").onclick = () => {
            document.getElementById("deleteForm").submit();
        };
    });
</script>

</body>
</html>
