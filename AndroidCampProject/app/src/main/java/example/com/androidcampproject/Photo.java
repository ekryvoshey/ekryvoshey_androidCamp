package example.com.androidcampproject;

/**
 * Created by Esmond on 21.07.2015.
 */
public class Photo {

    long photoId;
    long albumId;
    long ownerId;
    String src;
    String srcBig;
    String srcSmall;
    String srcXBig;
    String srcXXBig;
    int width;
    int height;
    String text;
    long created;

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcBig() {
        return srcBig;
    }

    public void setSrcBig(String srcBig) {
        this.srcBig = srcBig;
    }

    public String getSrcSmall() {
        return srcSmall;
    }

    public void setSrcSmall(String srcSmall) {
        this.srcSmall = srcSmall;
    }

    public String getSrcXBig() {
        return srcXBig;
    }

    public void setSrcXBig(String srcXBig) {
        this.srcXBig = srcXBig;
    }

    public String getSrcXXBig() {
        return srcXXBig;
    }

    public void setSrcXXBig(String srcXXBig) {
        this.srcXXBig = srcXXBig;
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
