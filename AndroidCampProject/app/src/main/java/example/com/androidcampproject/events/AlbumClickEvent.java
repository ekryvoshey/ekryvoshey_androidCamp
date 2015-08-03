package example.com.androidcampproject.events;

/**
 * Created by Esmond on 03.08.2015.
 */
public class AlbumClickEvent {
    long albumId;
    long ownerId;
    public AlbumClickEvent(long ownerId, long albumId){
        this.albumId = albumId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
