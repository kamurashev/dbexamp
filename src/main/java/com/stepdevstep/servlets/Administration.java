package com.stepdevstep.servlets;

import com.stepdevstep.dbexam.dao.SqlSupervisorDAO;
import com.stepdevstep.dbexam.dao.SupervisorDAOi;
import com.stepdevstep.dbexam.entities.Supervisor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Administration", urlPatterns = "/administration")
public class Administration extends HttpServlet {
    private SupervisorDAOi dao = new SqlSupervisorDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("submit")!=null)
            login(request,response);
        else if(request.getParameter("logout")!=null)
            logout(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Administration.jsp").forward(request,response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Supervisor sup = dao.get(request.getParameter("login"), request.getParameter("pass"));
        if (sup!=null){
            request.getSession().setAttribute("supervisor",sup);
            doGet(request,response);
        }else {
            request.setAttribute("message","Login or password is incorrect!");
            doGet(request, response);
        }
    }
    private void logout(HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/administration");
    }
}
