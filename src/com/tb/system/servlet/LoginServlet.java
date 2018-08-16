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
//        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //必须注释掉，里面已经resp.sendError了
//        super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        //此处对应JSP中的标签名，必须是name属性
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        List<User> list = UserDaoImpl.getInstance().read(userName);
        if (list != null && list.size() > 0 && password.equals(list.get(0).password)) {
            System.out.println("登录成功==="+list.get(0).toString());
            //登录成功后，就将用户存储到session中
            //登陆成功，我是重定向到其它页面，重定向request作用域不能延伸
            //所以这里我要用session才可以把成功的信息传递给index.jsp
            req.getSession().setAttribute("user", list.get(0));
            req.getSession().setAttribute("welcome", userName);
            resp.sendRedirect("index.jsp");
        } else {
            System.out.println("=========登录失败========");
            //登陆失败，我用的是转发，转发request作用域是连续的，所以我这里可以用request传递失败的信息给jsp页面
            req.setAttribute("msg", userName + "登录失败");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        }
    }
}
