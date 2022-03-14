import parse.*;
import model.*;
import java.io.*;
import java.util.*;

public class movie_recommendation{
    public static void main(String args[]) throws IOException{
        ArrayList<user> user_data = new ArrayList<>();
        ParseData obj1 = new ParseData();
        obj1.getuserdata(user_data);

        ArrayList<rating> rate_data = new ArrayList<>();
        ParseData obj2 = new ParseData();
        obj2.getratingdata(rate_data);

        ArrayList<movie> movie_data = new ArrayList<>();
        ParseData obj3 = new ParseData();
        obj3.getMoviedata(movie_data);

        genredata genre_data[] = new genredata[20];
        ParseData obj4 = new ParseData();
        obj4.getgenredata(genre_data);

    }
}

