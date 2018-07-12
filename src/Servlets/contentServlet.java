package Servlets;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DatabaseConnector.ContentDao;
import DatabaseConnector.ContentDaoImpl;
import DatabaseConnector.DrugDaoImpl;
import DatabaseConnector.DrugsDao;
import com.google.gson.Gson;
import model.Content;
import model.Medicine;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "contentServlet")
public class contentServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "data";
    private String uploded_directory="";
    private String documentTitle="";
    private String contentCategory ="";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                String uploadPath = getServletContext().getRealPath("")
                +UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }


                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(uploadPath + File.separator + name));
                        uploded_directory= uploadPath+ File.separator+name;

                        System.out.println(item);
                        //After upload add to path to CONTENT table in the database





                    }
                    else if(item.isFormField()){
                        System.out.println(item.getFieldName());
                        if(item.getFieldName().equals("content-title")) {
                            documentTitle = item.getString();
                            System.out.println(documentTitle);
                        }
                        if(item.getFieldName().equals("contentCategory")){
                            contentCategory = item.getString();
                            System.out.println(contentCategory);


                        }

                    }
                }
                Content addNewContent = new Content(documentTitle,uploded_directory,contentCategory);
                ContentDao contentDao = new ContentDaoImpl();
                contentDao.addContent(addNewContent);

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully at "+uploded_directory);
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        String URL= "/pharmacist/contentOverview.jsp";
        response.sendRedirect(URL);

    }
    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String route = request.getParameter("route");
        switch (route) {
            case "Delete":
                ContentDao contentDao= new ContentDaoImpl();
                String contentId = request.getParameter("contentid");
                contentDao.deleteContent(Integer.parseInt(contentId));
                String URL= "/pharmacist/contentOverview.jsp";
                response.sendRedirect(URL);

            case "all":
                ContentDao Dao = new ContentDaoImpl();
                ArrayList<Content> list = Dao.getAllContent();
                String json = new Gson().toJson(list);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;


        }


    }
}
