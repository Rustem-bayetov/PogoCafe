package controllers.orders;

import classes.PogoServlet;
import models.orders.OrderModel;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders-list")
public class OrdersListServlet extends PogoServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        List<OrderModel> model = orderService.getOrderList();

        request.setAttribute("order_data", model);

        forward("/pages/orders/orderList.jsp", request, response);
    }
}
