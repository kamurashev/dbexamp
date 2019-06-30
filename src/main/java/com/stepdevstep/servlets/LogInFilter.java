package com.stepdevstep.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LogInFilter", urlPatterns = {"/add-update", "/delete"})
public class LogInFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        if (request.getSession().getAttribute("supervisor")==null)
            response.sendRedirect(request.getContextPath()+"/administration");
        else
        chain.doFilter(req, resp);
    }
}
