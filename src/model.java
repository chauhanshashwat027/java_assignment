package model;
import java.util.*;

public class genredata {
    public String genre;
    public int genreid;
}

public class rating {
    public int userid;
    public int movieid;
    public int rating;
    public int timestamp;
}

public class movie {
    public int movieid;
    public String title;
    public String release;
    public String imdburl;

    public ArrayList<int> genre = new ArrayList<int>();
}

public class user {
    public int userid;
    public int age;
    public String gender;
    public String occupation;
    public String zip;
}
