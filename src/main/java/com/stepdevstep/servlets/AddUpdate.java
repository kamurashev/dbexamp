package com.stepdevstep.servlets;

import com.stepdevstep.dbexam.dao.ABookDAOi;
import com.stepdevstep.dbexam.dao.HibABookDAO;
import com.stepdevstep.dbexam.dao.SqlABookDAO;
import com.stepdevstep.dbexam.entities.Audiobook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


@WebServlet(name = "AddUpdate", urlPatterns = "/add-update")
public class AddUpdate extends HttpServlet {
    private ABookDAOi dao = new HibABookDAO();
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Audiobook upBook;
        File path = null;
        String message = "Uploading status: ";
        ResourceBundle rb = ResourceBundle.getBundle("global");
        String upRoot = rb.getString("upRoot");
        String upContext = rb.getString("upContext");
        boolean update = request.getParameter("update")!=null;

        if(update) {
            int book_id = Integer.parseInt(request.getParameter("book_id"));
            upBook = dao.getBook(book_id);
            String contextFile = upBook.getCover().replace(upContext,upRoot);
            path = new File(contextFile.substring(0,contextFile.lastIndexOf("\\")));
        } else
            upBook=new Audiobook();


        if(ServletFileUpload.isMultipartContent(request)){

            String temp = getServletContext().getRealPath("/WEB-INF/temp");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(temp));
            factory.setSizeThreshold(10*1024*1024);

            try {
                List<FileItem> items = new ServletFileUpload(factory).parseRequest(request);
                for(FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()){
                            case "name":
                                upBook.setName(item.getString("UTF-8"));
                                break;
                            case "genre":
                                upBook.setGenre(item.getString("UTF-8"));
                                break;
                            case "author":
                                upBook.setAuthor(item.getString("UTF-8"));
                                break;
                            case "speaker":
                                upBook.setSpeaker(item.getString("UTF-8"));
                                break;
                            case "duration":
                                upBook.setDuration(item.getString("UTF-8"));
                                break;
                            case "description":
                                upBook.setDescription(item.getString("UTF-8"));
                                break;
                        }
                    }
                }
                for(FileItem item : items){
                    if(!item.isFormField()) {
                        if(path==null) {
                            String baseSuffix = File.separator + upBook.getName().replaceAll("\\p{Punct}", "");
                            StringBuilder uniqueSuffix = new StringBuilder(baseSuffix + "~");
                            path = new File(upRoot + uniqueSuffix);
                            while (path.exists()) {
                                uniqueSuffix.append((int) (Math.random() * 10));
                                path = new File(upRoot + uniqueSuffix);
                            }
                            path.mkdirs();
                        }
                        String uploadContextPath;
                        if (!item.getName().equals("")) {
                            String fileName = File.separator + item.getName();
                            File uploadedFile = new File(path + fileName);
                            item.write(uploadedFile);
                            uploadContextPath = uploadedFile.getPath().replace(upRoot, upContext);

                            switch (item.getFieldName()) {
                                case "cover":
                                    upBook.setCover(uploadContextPath);
                                    message += "cover file uploaded to server, ";
                                    break;
                                case "content":
                                    upBook.setContent(uploadContextPath);
                                    message += "content file uploaded to server, ";
                                    break;
                            }
                        } else message+=item.getFieldName()+" wasn't uploaded, ";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!upBook.isValid())
            message+="entered book data wasn't valid, DB is NOT modified.";
        else if (!update){
            int id = dao.addBook(upBook);
            message+="uploaded book was valid ";
            if(id>-1)
            message+="and successfully added to DB (id = "+id+").";
            else
                message+="adding to DB failed, DB is NOT modified.";
        } else {
            int affected = dao.updateBook(upBook);
            message+="updated book was valid, ";
            if(affected>0)
                message+="DB updated successfully (id = "+upBook.getBook_id()+").";
            else
                message+="DB update failed, DB is NOT modified.";

        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("Administration.jsp").forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(request.getParameter("update")!=null) {
                int book_id = Integer.parseInt(request.getParameter("book_id"));
                request.setAttribute("book", dao.getBook(book_id));
            }
            request.getRequestDispatcher("AddUpdate.jsp").forward(request,response);
    }
}
