package com.stepdevstep.servlets;

import com.stepdevstep.dbexam.dao.HibABookDAO;
import com.stepdevstep.dbexam.dao.SqlABookDAO;
import com.stepdevstep.dbexam.dao.ABookDAOi;
import com.stepdevstep.dbexam.entities.Audiobook;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet (name = "Main", urlPatterns = {"","/main"}, loadOnStartup = 1)
public class Main extends HttpServlet {
    private ABookDAOi dao = new HibABookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page_n=1;
        int maxPage_n;
        int from;
        int to;
        int booksPerPage=5;

        ResourceBundle rb = ResourceBundle.getBundle("global");
        if(rb.containsKey("booksPerPage"))
            try {
                booksPerPage = Integer.parseInt(rb.getString("booksPerPage"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


        req.setAttribute("genre", req.getParameter("genre")==null? "" : req.getParameter("genre"));
        req.setAttribute("author", req.getParameter("author")==null? "" : req.getParameter("author"));
        req.setAttribute("speaker", req.getParameter("speaker")==null? "" : req.getParameter("speaker"));
        req.setAttribute("name", req.getParameter("name")==null? "" : req.getParameter("name"));
        req.setAttribute("order", req.getParameter("order")==null? "" : req.getParameter("order"));

        List<Audiobook> books = dao.getBooks(
                (String)req.getAttribute("genre"),                               //Получаем из
                (String)req.getAttribute("author"),                              //bd
                (String)req.getAttribute("speaker"),                             //все
                (String)req.getAttribute("name"),                                //книги
                (String)req.getAttribute("order"));                              //соотв. критериям

        if(books.isEmpty()){
            String searchFail = "К сожалению по вашему запросу" +
                    " ничего не найдено, проверьте" +
                    " правильность введенных для поиска данных.";
            resp.sendRedirect("/main?searchfail="+ URLEncoder.encode(searchFail, StandardCharsets.UTF_8));
            return;
        }

        maxPage_n = (int)Math.ceil(books.size()/(double)booksPerPage);              //считаем
        if(req.getParameter("page_n")!=null)                                     //валидный
            page_n = Integer.parseInt(req.getParameter("page_n"));               //page_n
        if(page_n>maxPage_n)                                                        //и
            page_n = maxPage_n;                                                     //добавляем
        if (page_n<1)                                                               //его
            page_n=1;                                                               //в
        req.setAttribute("page_n", page_n);                                      //запрос

        from = (page_n-1)*booksPerPage;                                             //формируем
        to = (from + booksPerPage)<books.size()?from+booksPerPage : books.size();   //укороченный
        books = books.subList(from,to);                                             //список книг //для отображения

        for (Audiobook ab : books)                                                  //Формируем укороченные версии
            ab.setDescription(String.format("%220.220s", ab.getDescription()));     //описаний для отображения

        req.setAttribute("books",books);
        req.getRequestDispatcher("Main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
