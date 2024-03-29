package controllers.home;

import classes.PogoServlet;
import classes.Result;
import models.users.UserModel;
import services.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends PogoServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = getParamString("userName", request);
        String password = getParamString("password", request);
        HttpSession session = request.getSession();
        UserService service = new UserService();

        Result result = service.login(userName, password);

        if (result.isSuccess()) {
            session.setAttribute("userType", ((UserModel)result.getValue()).getRole().toString());
            session.setAttribute("loginStatus", "loggedin");

        }
        writeJson(result, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward("/pages/home/login.jsp", request, response);
    }
}
