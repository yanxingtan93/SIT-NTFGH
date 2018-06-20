package Servlets;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import DBUtils.DBConn;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "contentServlet")
public class contentServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "data";
    private String uploded_directory="";

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
                        System.out.println(uploded_directory);
                        //After upload add to path to CONTENT table in the database
                        Connection conn= DBConn.getConnection();



                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "File Uploaded Successfully at "+uploded_directory);
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);

    }
}
