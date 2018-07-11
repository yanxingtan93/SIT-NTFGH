package DatabaseConnector;

import model.Content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {
    private DBConn db;

    public ContentDaoImpl() {
        db = new DBConn();

    }

    @Override
    public List<Content> getAllContent() {
        List<Content> contentList = new ArrayList<Content>();
        String sql = "SELECT * FROM CONTENT";
        try {
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Content content = new Content(rs.getInt(1), rs.getString(2), rs.getString(3));
                contentList.add(content);

            }
            con.commit();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }

    @Override
    public void addContent(Content content) {
        String sql = "INSERT INTO CONTENT(content_title,content_document) VALUES(?,?)";

        try {
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, content.getContentTitle());
            ps.setString(2, content.getContentPath());
            ps.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Content getContent(int contentId) {
        Content theContent = new Content();
        String sql = "SELECT * FROM CONTENT WHERE content_ID = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, contentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                theContent.setContentTitle(rs.getString(2));
                theContent.setContentPath(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return theContent;
    }

    @Override
    public void updateContent(Content content) {
        String sql = "UPDATE CONTENT SET content_title = ?, content_document=? WHERE content_ID=?";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, content.getContentTitle());
            ps.setString(2, content.getContentPath());
            ps.setInt(3, content.getId());
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteContent(int contentId) {
        String sql = "DELETE FROM CONTENT WHERE content_ID = ?";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, contentId);
            ps.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void deleteAllContent(){
        String sql = "truncate table CONTENT";
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}




