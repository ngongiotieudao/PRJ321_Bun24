

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Add New Product - Coffee Shop</title>
        <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
        <link rel="icon" href="assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {families: ["Public Sans:300,400,500,600,700"]},
                custom: {
                    families: [
                        "Font Awesome 5 Solid",
                        "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
                        "simple-line-icons",
                    ],
                    urls: ["assets/css/fonts.min.css"],
                },
                active: function () {
                    sessionStorage.fonts = true;
                },
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/css/plugins.min.css" />
        <link rel="stylesheet" href="assets/css/kaiadmin.min.css" />

        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link rel="stylesheet" href="assets/css/demo.css" />
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <jsp:include page="component/Sidebar.jsp"></jsp:include>
                <!-- End Sidebar -->

                <div class="main-panel">
                    <div class="container">
                        <div class="page-inner">
                            <div class="page-header">
                                <h3 class="fw-bold mb-3">Update Product</h3>
                                <ul class="breadcrumbs mb-3">
                                    <li class="nav-home">
                                        <a href="#">
                                            <i class="icon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="">Dashboard</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="manageProduct">Products Management</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Update Product</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div>
                                            <h4 style="color: red" >${msg}</h4>
                                    </div>
                                    <div class="card-header">
                                        <h4 class="card-title">Product Details</h4>
                                    </div>
                                    <div class="card-body">
                                        <form action="updateProduct" method="post" enctype="multipart/form-data">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <input type="text" name="idProduct" value="${product.productId}" style="display: none"/>
                                                        <label for="productName">Product Name</label>
                                                        <input type="text" class="form-control" value="${product.name}" id="productName" name="name" placeholder="Enter product name" required>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="productImage">Upload Image</label>
                                                        <input type="file" class="form-control" id="productImage" name="imageFile" accept="image/*">
                                                    </div>
                                                </div>              
                                            </div>


                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">                   
                                                        <img id="imagePreview" src="${product.imageUrl}" alt="Image Preview" style="max-width: 200px; max-height: 200px; " />
                                                    </div>
                                                </div>
                                            </div>



                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="productPrice">Price</label>
                                                        <input type="number" class="form-control" value="${product.price}" id="productPrice" name="price" placeholder="Enter price" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="stock">Stock</label>
                                                        <input type="number" class="form-control" value="${product.stock}" id="productStock" name="stock" placeholder="Enter stock" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="productDescription">Description</label>
                                                <textarea class="form-control" id="productDescription" name="description" rows="4" placeholder="Enter product description" required>${product.description}</textarea>
                                            </div>

                                            <div class="form-group">
                                                <label for="productCategory">Category</label>
                                                <select class="form-select" id="productCategory" name="categoryId" required>
                                                    <c:forEach items="${categories}" var="categories">
                                                        <option  ${product.category.categoryId == categories.categoryId ? 'selected' : ""} value="${categories.categoryId}">${categories.categoryName}</option>
                                                    </c:forEach>

                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label for="productStatus">Status</label>
                                                <select class="form-select" id="productStatus" name="status" required>
                                                    <option ${product.status eq 'AVAILABLE' ? 'selected' : ""} value="AVAILABLE">AVAILABLE</option>
                                                    <option  ${product.status eq 'AUCTION' ? 'selected' : ""} value="AUCTION">AUCTION</option>
                                                    <option  ${product.status eq 'SOLD_OUT' ? 'selected' : ""} value="SOLD_OUT">SOLD_OUT</option>
                                                </select>
                                            </div>

                                            <button type="submit"  class="btn btn-primary">Update Product</button>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <footer class="footer">
                    <div class="container-fluid d-flex justify-content-between">
                        <nav class="pull-left">
                            <ul class="nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="http://www.themekita.com">ThemeKita</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Help</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Licenses</a>
                                </li>
                            </ul>
                        </nav>
                        <div class="copyright">
                            2024, made with <i class="fa fa-heart heart text-danger"></i> by
                            <a href="http://www.themekita.com">ThemeKita</a>.
                        </div>
                        <div>
                            Distributed by
                            <a target="_blank" href="https://themewagon.com/">ThemeWagon</a>.
                        </div>
                    </div>
                </footer>
            </div>

        </div>

        <!-- JS Files -->
        <script src="assets/js/core/jquery-3.7.1.min.js"></script>
        <script src="assets/js/core/popper.min.js"></script>
        <script src="assets/js/core/bootstrap.min.js"></script>
    </body>
</html>

