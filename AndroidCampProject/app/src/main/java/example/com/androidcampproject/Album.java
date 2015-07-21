package example.com.androidcampproject;

/**
 * Created by Esmond on 21.07.2015.
 */
public class Album {
    int id;
    int thumb_id;
    int owner_id;
    String title;
    String description;
    int created;
    int updated;
    int size;

    Album(int id, int thumb_id, int owner_id, String title, String description,
          int created, int updated, int size) {
        this.id = id;
        this.thumb_id = thumb_id;
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.size = size;
    }
}
