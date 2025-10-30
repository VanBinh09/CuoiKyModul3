<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Tạo mới thông tin thuê trọ</title>
  <link href="assets/bootstrap/bootstrap.min.css" rel="stylesheet">
  <script src="assets/bootstrap/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<div class="container py-4">
  <div class="card shadow-sm">
    <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
      <h5 class="mb-0">➕ Tạo mới thông tin thuê trọ</h5>
      <a href="rooms" class="btn btn-light btn-sm">⬅️ Quay lại</a>
    </div>
    <div class="card-body">

      <form action="rooms" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="insert">

        <!-- Tên người thuê -->
        <div class="mb-3">
          <label class="form-label fw-bold">Tên người thuê trọ <span class="text-danger">*</span></label>
          <input type="text" name="tenNguoiThue" id="tenNguoiThue" class="form-control"
                 placeholder="Nhập tên người thuê..." required>
        </div>

        <!-- Số điện thoại -->
        <div class="mb-3">
          <label class="form-label fw-bold">Số điện thoại <span class="text-danger">*</span></label>
          <input type="text" name="soDienThoai" id="soDienThoai" class="form-control"
                 placeholder="VD: 0901234567" required>
        </div>

        <!-- Ngày bắt đầu -->
        <div class="mb-3">
          <label class="form-label fw-bold">Ngày bắt đầu thuê <span class="text-danger">*</span></label>
          <input type="date" name="ngayBatDau" id="ngayBatDau" class="form-control" required>
        </div>

        <!-- Hình thức thanh toán -->
        <div class="mb-3">
          <label class="form-label fw-bold">Hình thức thanh toán <span class="text-danger">*</span></label>
          <select name="hinhThucThanhToanId" id="hinhThucThanhToanId" class="form-select" required>
            <option value="">-- Chọn hình thức --</option>
            <c:forEach var="p" items="${paymentTypes}">
              <option value="${p.id}">${p.tenHinhThuc}</option>
            </c:forEach>
          </select>
        </div>

        <!-- Ghi chú -->
        <div class="mb-3">
          <label class="form-label fw-bold">Ghi chú</label>
          <textarea name="ghiChu" id="ghiChu" class="form-control" rows="3"
                    maxlength="200" placeholder="Tối đa 200 ký tự..."></textarea>
        </div>

        <div class="d-flex justify-content-end">
          <button type="submit" class="btn btn-success me-2">💾 Tạo mới</button>
          <a href="rooms" class="btn btn-secondary">❌ Hủy</a>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  // 🧩 Validation phía client
  function validateForm() {
    const name = document.getElementById("tenNguoiThue").value.trim();
    const phone = document.getElementById("soDienThoai").value.trim();
    const date = document.getElementById("ngayBatDau").value;
    const today = new Date().toISOString().split("T")[0];

    const nameRegex = /^[A-Za-zÀ-ỹ\s]{5,50}$/;
    if (!nameRegex.test(name)) {
      alert("Tên người thuê không được chứa số hoặc ký tự đặc biệt, độ dài 5-50 ký tự!");
      return false;
    }

    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
      alert("Số điện thoại phải gồm 10 chữ số!");
      return false;
    }

    if (date < today) {
      alert("Ngày bắt đầu thuê không được là ngày quá khứ!");
      return false;
    }

    const note = document.getElementById("ghiChu").value;
    if (note.length > 200) {
      alert("Ghi chú không được vượt quá 200 ký tự!");
      return false;
    }

    return true;
  }
</script>

</body>
</html>
