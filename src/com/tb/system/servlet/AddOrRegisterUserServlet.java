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
 * @des 用户注册或添加
 */
public class AddOrRegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//写入数据库防止乱码
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");
        List<User> list = UserDaoImpl.getInstance().read(userName);
        boolean flag=false;
        if (list != null && list.size() > 0) {
            for (User u:list) {
                if(u.userName.equals(userName)){
                    flag=true;
                    break;
                }
            }
        } else {

        }
        if(flag){
            System.out.println("=========注册失败,用户名不能重复========");
            req.setAttribute("msg", "注册失败,用户名不能重复");
            req.getRequestDispatcher("/user/addOrRegister.jsp").forward(req, resp);
            return;
        }
        User user=new User();
        user.password=password;
        user.userName=userName;
        user.nickName=nickName;
        List<User> l=new ArrayList<>();
        l.add(user);
        int[] i=UserDaoImpl.getInstance().create(l);
        if(i!=null&&i.length==1){
            System.out.println("注册成功==="+user.toString());
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("welcome", userName);
            resp.sendRedirect("index.jsp");
        }else{
            System.out.println("=========注册失败========");
            req.setAttribute("msg", "注册失败");
            req.getRequestDispatcher("/user/addOrRegister.jsp").forward(req, resp);
        }
    }
}
