package com.tb.system.servlet;

import com.tb.system.bean.User;
import com.tb.system.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tb
 * @time 2018/8/15 下午4:46
 * @des 删除用户
 */
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        String userName = req.getParameter("userName");
        List<Object> l=new ArrayList<>();
        l.add(userName);
        int[] i = UserDaoImpl.getInstance().delete(l);
        if (i != null && i.length ==l.size()) {
            System.out.println("删除成功==="+userName);
            req.getSession().setAttribute("userName", userName);
            resp.sendRedirect("index.jsp");
        } else {
            System.out.println("=========删除失败========");
            req.setAttribute("msg", userName + "删除失败");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
