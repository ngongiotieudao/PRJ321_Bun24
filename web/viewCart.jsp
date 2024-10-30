<%-- 
    Document   : viewCart
    Created on : Oct 27, 2024, 6:37:47 PM
    Author     : datkh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <!--
                CSS
                ============================================= -->
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
            <!--================Cart Area =================-->
            <section class="cart_area">
                <div class="container">
                    <div class="cart_inner">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Product</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>
                                        <th scope="col">Action</th> <!-- Added Action column -->
                                    </tr>
                                </thead>
                                <tbody>
                                <c:set var="totalOrder" value="0"/>
                                <c:forEach items="${orderDetailsMap}" var="items">
                                    <tr>
                                        <td>
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img style="width: 300px; height: 300px;" src="${items.value.products.imageUrl}" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <p>${items.value.products.name}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <h5>${items.value.products.price}</h5>
                                        </td>
                                        <td>
                                            <div class="product_count">
                                                <input type="text" name="qty" id="sst" maxlength="12" value="${items.value.quantity}" title="Quantity:" class="input-text qty">
                                                <button onclick="var result = document.getElementById('sst');
                                                        var sst = result.value;
                                                        if (!isNaN(sst))
                                                            result.value++;
                                                        return false;" class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>
                                                <button onclick="var result = document.getElementById('sst');
                                                        var sst = result.value;
                                                        if (!isNaN(sst) && sst > 0)
                                                            result.value--;
                                                        return false;" class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>
                                            </div>
                                        </td>
                                        <td>
                                            <h5>${items.value.price}</h5>
                                        </td>
                                        <td>
                                            <a href="deleteCart?productId=${items.value.products.productId}" class="btn btn-danger btn-sm">
                                                <i class="fa fa-trash"></i> Delete
                                            </a>
                                        </td>
                                    </tr>
                                    <!-- Calculate total order by accumulating each item's total price -->
                                    <c:set var="totalOrder" value="${totalOrder + (items.value.products.price * items.value.quantity)}"/>
                                </c:forEach>
                            </tbody>

                        </table>                          
                        <!-- Action Buttons -->
                        <div class="d-flex justify-content-between align-items-center mt-4">
                            <h4>Total Order: <span id="totalOrder">${totalOrder}</span></h4>
                            
                            <h4 style="color: red"><span > ${ msg}</span></h4>
                        </div>

                        <div class="d-flex justify-content-between align-items-center mt-4">
                            <form action="updateCart" method="post" onsubmit="updateArrayQuantity()">
                                <input style="display: none" type="text" name="arrayQuantity" value="" />
                                <button type="submit" class="gray_btn">Update Cart</button>
                            </form>
                            <a class="gray_btn" href="home">Continue Shopping</a>
                            <div>
                                <a class="primary-btn" href="checkOut">Proceed to checkout</a>
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

        <script src="js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <!--gmaps Js-->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
        <script src="js/gmaps.min.js"></script>
        <script src="js/main.js"></script>
        <script>
                                function updateArrayQuantity() {
                                    // Get all elements with class "qty"
                                    const quantities = document.querySelectorAll('input.qty');

                                    // Validate that we have quantities
                                    if (!quantities || quantities.length === 0) {
                                        alert('No quantities found to update');
                                        return false;
                                    }

                                    // Map through each input, get its value, and validate
                                    const quantityValues = Array.from(quantities).map(input => {
                                        const value = parseInt(input.value);
                                        if (isNaN(value) || value < 0) {
                                            alert('Please enter valid quantities');
                                            return false;
                                        }
                                        return value;
                                    }).join(',');

                                    // If we have valid quantities, set the value
                                    if (quantityValues) {
                                        document.querySelector('input[name="arrayQuantity"]').value = quantityValues;
                                        return true;
                                    }

                                    return false;
                                }

// Add event listeners for quantity changes to update total
                                document.querySelectorAll('.qty').forEach(input => {
                                    input.addEventListener('change', function () {
                                        updateTotal();
                                    });
                                });

                                function updateTotal() {
                                    let total = 0;
                                    document.querySelectorAll('.qty').forEach(input => {
                                        const quantity = parseInt(input.value) || 0;
                                        const price = parseFloat(input.closest('tr').querySelector('td:nth-child(2) h5').textContent);
                                        const itemTotal = quantity * price;
                                        input.closest('tr').querySelector('td:nth-child(4) h5').textContent = itemTotal.toFixed(2);
                                        total += itemTotal;
                                    });
                                    document.getElementById('totalOrder').textContent = total.toFixed(2);
                                }
        </script>


    </body>

</html>