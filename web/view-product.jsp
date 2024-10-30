<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
    <head>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon-->
        <link rel="shortcut icon" href="img/fav.png">
        <!-- Author Meta -->
        <meta name="author" content="CodePixar">
        <!-- Meta Description -->
        <meta name="description" content="">
        <!-- Meta Keyword -->
        <meta name="keywords" content="">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <!-- Site Title -->
        <title>Karma Shop</title>

        <!-- CSS -->
        <link rel="stylesheet" href="css/linearicons.css">
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/themify-icons.css">
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <!-- Start Header Area -->
        <jsp:include page="component/header.jsp"></jsp:include>
            <!-- End Header Area -->

            <!-- Start Banner Area -->
        <jsp:include page="component/banner.jsp"></jsp:include>
            <!-- End Banner Area -->

            <!--================Cart Area =================-->
           <section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr style="font-size: 18px;">
                            <th scope="col">Product</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Description</th>
                            <th scope="col">Stock</th>
                            <th scope="col">Category</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="${products.imageUrl}" alt="" width="150" height="150" />
                                    </div>
                                </div>
                            </td>
                            <td style="font-size: 18px;">${products.name}</td>
                            <td style="font-size: 18px;">${products.price}</td>
                            <td style="font-size: 18px;">${products.description}</td>
                            <td style="font-size: 18px;">${products.stock}</td>
                            <td style="font-size: 18px;">${products.category.categoryName}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- Action Buttons -->
            <div class="mt-4">
                <div class="d-flex justify-content-between">
                    <div>
                        <a href="home" class="gray_btn">Return To Home</a>
                    </div>
                    <div>
                        <a href="addToCart?productId=${products.productId}" class="primary-btn">Add to Cart</a>
                        <a href="checkOut" class="primary-btn ml-3">Proceed to Checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

        <!--================End Cart Area =================-->

        <!-- start footer Area -->
        <jsp:include page="component/footer.jsp"></jsp:include>
        <!-- End footer Area -->

        <!-- JS -->
        <script src="js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
        <script src="js/gmaps.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>