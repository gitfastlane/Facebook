
package com.facebook.www.command.fb_board;

import com.facebook.www.command.Command;
import com.facebook.www.dao.Fb_boardDAO;
import com.facebook.www.dao.Fb_memberDAO;
import com.facebook.www.dto.Fb_boardDTO;
import com.facebook.www.dto.Fb_memberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class WritePostOKCommand implements Command
{
    public void execute(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
    	HttpSession session = request.getSession();
        String folderName = "userFile";
        String path = request.getSession().getServletContext().getRealPath(folderName);
        int size = 1024*1024*10;
        try
        {
            MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
            String fileName = multi.getFilesystemName("b_image");
            String savePath;
            if(fileName==null) {
            	savePath = null;
            }else {
            	savePath = folderName+"/"+fileName;
            }
            System.out.println("WritePostOKCommand path: "+path);
            System.out.println("savePath: "+savePath);
            String b_content = multi.getParameter("b_content");
            String b_image = savePath;
            String t_hashTag = multi.getParameter("t_hashTag");
            
            String b_id_fk = (String)session.getAttribute("userID");
            Fb_boardDTO dto = new Fb_boardDTO();
            dto.setB_content(b_content);
            dto.setB_image(b_image);
            dto.setB_id_fk(b_id_fk);
            Fb_boardDAO dao = Fb_boardDAO.getFb_boardDAO();
            dao.writePostOK(dto, t_hashTag);
        }
        catch(Exception e)
        {
            System.out.println("WritePostOKCommand Exception 발생");
            e.printStackTrace();
        }

    }
}
