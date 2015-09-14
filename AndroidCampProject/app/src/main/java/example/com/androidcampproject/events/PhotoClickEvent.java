package example.com.androidcampproject.events;

/**
 * Created by Esmond on 05.08.2015.
 */
public class PhotoClickEvent {
    private String src;
    private String text;
    private int position;

    public PhotoClickEvent(String src, String text, int position) {
        this.src = src;
        this.text = text;
        this.position = position;
    }

    public String getSrc() {
        return src;
    }

    public String getText() {
        return text;
    }

    public int getPosition() {
        return position;
    }
}
