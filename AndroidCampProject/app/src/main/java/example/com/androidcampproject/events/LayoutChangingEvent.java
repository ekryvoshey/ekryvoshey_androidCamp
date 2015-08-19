package example.com.androidcampproject.events;

/**
 * Created by Esmond on 19.08.2015.
 */
public class LayoutChangingEvent {
    public static String layoutId;

    public LayoutChangingEvent(String layoutId){
        this.layoutId = layoutId;
    }

    public static String getLayoutId() {
        return layoutId;
    }
}
