package example.com.androidcampproject.objects;

/**
 * Created by Esmond on 21.07.2015.
 */
public class Album {
    long aid;
    long thumb_id;
    long owner_id;
    String title;
    String description;
    String thumb_src;
    long created;
    long updated;
    int size;

    public long getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public long getThumb_id() {
        return thumb_id;
    }

    public void setThumb_id(int thumb_id) {
        this.thumb_id = thumb_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb_src() {
        return thumb_src;
    }

    public void setThumb_src(String thumb_src) {
        this.thumb_src = thumb_src;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
