package example.com.androidcampproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Esmond on 15.07.2015.
 */
public class Person {
    int photoId;
    String name;
    String city;

    Person(String name, String city, int photoId) {
        this.name = name;
        this.city = city;
        this.photoId = photoId;
    }
}

