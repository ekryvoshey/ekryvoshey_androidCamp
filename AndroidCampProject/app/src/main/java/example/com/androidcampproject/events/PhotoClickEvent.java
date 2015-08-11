package example.com.androidcampproject.events;

/**
 * Created by Esmond on 05.08.2015.
 */
public class PhotoClickEvent {
    String src;
    String text;
    public PhotoClickEvent(String src, String text){
        this.src = src;
        this.text = text;
    }

    public String getSrc() {
        return src;
    }

    public String getText() {
        return text;
    }
}
