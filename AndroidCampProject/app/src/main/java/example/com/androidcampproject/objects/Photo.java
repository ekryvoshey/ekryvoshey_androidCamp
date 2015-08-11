package example.com.androidcampproject.objects;

/**
 * Created by Esmond on 21.07.2015.
 */
public class Photo {

    long pid;
    long aid;
    long owner_id;
    String src;
    String src_big;
    String src_small;
    String src_xbig;
    String src_xxbig;
    int width;
    int height;
    String text;
    long created;

    public long getPhotoId() {
        return pid;
    }

    public void setPhotoId(long photoId) {
        this.pid = photoId;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc_big() {
        return src_big;
    }

    public void setSrc_big(String src_big) {
        this.src_big = src_big;
    }

    public String getSrc_small() {
        return src_small;
    }

    public void setSrc_small(String src_small) {
        this.src_small = src_small;
    }

    public String getSrc_xbig() {
        return src_xbig;
    }

    public void setSrc_xbig(String src_xbig) {
        this.src_xbig = src_xbig;
    }

    public String getSrc_xxbig() {
        return src_xxbig;
    }

    public void setSrc_xxbig(String src_xxbig) {
        this.src_xxbig = src_xxbig;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
