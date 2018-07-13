package DatabaseConnector;

import model.Content;

import java.util.ArrayList;
import java.util.List;

public interface ContentDao {
    public ArrayList<Content> getAllContent();
    public void addContent(Content content);
    public Content getContent(int contentId);
    public void updateContent(Content content);
    public void deleteContent(int contentId);
    public void deleteAllContent();

}
