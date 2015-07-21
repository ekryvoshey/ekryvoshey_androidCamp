package example.com.androidcampproject;

/**
 * Created by Esmond on 21.07.2015.
 */
public class Photo {
    int id;
    int album_id;
    int owner_id;
    int user_id;
    String photo_75;
    String photo_130;
    String photo_604;
    String photo_807;
    String photo_1280;
    String photo_2560;
    int width;
    int height;
    String text;
    int date;

    Photo(int id, int album_id, int owner_id, int user_id,
          String photo_75, String photo_130, String photo_604,
          String photo_807, String photo_1280, String photo_2560,
          int width, int height, String text, int date) {
        this.id = id;
        this.album_id = album_id;
        this.owner_id = owner_id;
        this.user_id = user_id;
        this.photo_75 = photo_75;
        this.photo_130 = photo_130;
        this.photo_604 = photo_604;
        this.photo_807 = photo_807;
        this.photo_1280 = photo_1280;
        this.photo_2560 = photo_2560;
        this.width = width;
        this.height = height;
        this.text = text;
        this.date = date;
    }
}
