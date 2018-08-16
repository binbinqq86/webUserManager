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
 * @des 修改用户
 */
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        String nickName = req.getParameter("nickName");
        String userName=req.getParameter("userName");

        boolean flag=false;
        List<User> list = UserDaoImpl.getInstance().read(userName);
        if (list != null && list.size() > 0 && userName.equals(list.get(0).userName)) {
            list.get(0).nickName=nickName;
            int[] i=UserDaoImpl.getInstance().update(list);
            if(i!=null&&i.length==1){
                flag=true;
            }
        }
        if(flag){
            System.out.println("修改成功==="+list.get(0).toString());
            req.getSession().setAttribute("user", list.get(0));
            req.getSession().setAttribute("welcome", userName);
            resp.sendRedirect("index.jsp");
        }else{
            System.out.println("=========修改失败========");
            req.setAttribute("msg", userName + "修改失败");
            req.getRequestDispatcher("/user/updateUser.jsp").forward(req, resp);
        }
    }
}
