package com.stepdevstep.servlets;

import com.stepdevstep.dbexam.dao.HibABookDAO;
import com.stepdevstep.dbexam.dao.SqlABookDAO;
import com.stepdevstep.dbexam.dao.ABookDAOi;
import com.stepdevstep.dbexam.entities.Audiobook;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(name = "Delete", urlPatterns = "/delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ABookDAOi dao = new HibABookDAO();
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        Audiobook book = dao.getBook(book_id);
        ResourceBundle rb = ResourceBundle.getBundle("global");
        String upRoot = rb.getString("upRoot");
        String upContext = rb.getString("upContext");
        String message;

        int affected = dao.deleteBook(book_id);

        if (affected==0) {
            message = "Something gone wrong with deleting procedure, DB wasn't modified," +
                    " nothing was deleted from server";
            response.sendRedirect("/?message="+message);
            return;
        }
        else
            message = "DB entry for Book ID="+book_id+" deleted successfully, ";

        String path = book.getCover().replace(upContext,upRoot);
        path = path.substring(0,path.lastIndexOf("\\"));
        File bookDirectory = new File(path);
        System.out.println(path);

        try {
            FileUtils.deleteDirectory(bookDirectory);
        } catch (Exception e) {
            message+="files NOT deleted from server, see server console for details.";
            e.printStackTrace();
        }
        if (!bookDirectory.exists())
            message+="files deleted from server.";

        response.sendRedirect("/?message="+message);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.sendRedirect("/?message=You can do this through main page interface only!");
    }
}
