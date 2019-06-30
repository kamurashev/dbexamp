package com.stepdevstep.servlets;

import com.stepdevstep.dbexam.dao.HibABookDAO;
import com.stepdevstep.dbexam.dao.ABookDAOi;
import com.stepdevstep.dbexam.entities.Audiobook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FullBook", urlPatterns = {"/showfull"})
public class FullBook extends HttpServlet {
    private ABookDAOi dao = new HibABookDAO();
    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int book_id = Integer.parseInt(req.getParameter("id"));
        Audiobook book = dao.getBook(book_id);

        if(book==null) {
            resp.sendRedirect("/main");
            return;
        }

        req.setAttribute("book", book);
        ArrayList<Integer> views = (ArrayList<Integer>)req.getSession().getAttribute("views");

        if (views==null) {
            book.setViews(book.getViews()+1);
            req.getSession().setAttribute("views", new ArrayList<>(List.of(book_id)));
            dao.updateBook(book);
        } else if (!views.contains(book_id)) {
            book.setViews(book.getViews() + 1);
            dao.updateBook(book);
            ((ArrayList<Integer>) req.getSession().getAttribute("views")).add(book_id);
        }

        req.getRequestDispatcher("FullBook.jsp").forward(req,resp);
    }
}
