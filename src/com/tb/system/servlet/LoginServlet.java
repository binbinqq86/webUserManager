package com.tb.system.servlet;

import com.tb.system.bean.User;
import com.tb.system.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author tb
 * @time 2018/8/15 下午4:46
 * @des 用户登录
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        resp.setContentType("text/html;charset=uft-8");
        //此处对应JSP中的标签名，必须是name属性
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("userName", userName);
        httpSession.setAttribute("password", password);
        List<User> list = UserDaoImpl.getInstance().read(userName);
        if (list != null && list.size() > 0 && password.equals(list.get(0).password)) {
            resp.sendRedirect("index.jsp");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
