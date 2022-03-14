package parse;

import model.*;
import java.util.*;
import java.io.*;

public class ParseData {
    public static void getuserdata(ArrayList<user> user_data) throws FileNotFoundException, IOException {
        String path = "/Users/shashwatchauhan/Documents/Assignment 01/New Folder With Items/user.data";
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringTokenizer token;
        String line;
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, "|");
            user temp = new user();
            temp.userid = Integer.parseInt(token.nextToken());
            temp.age = Integer.parseInt(token.nextToken());
            temp.gender = token.nextToken();
            temp.occupation = token.nextToken();
            temp.zip = token.nextToken();
            user_data.add(temp);
        }
    }

    public static void getratingdata(ArrayList<rating> rate_data) throws FileNotFoundException, IOException {
        File path = new File("/Users/shashwatchauhan/Documents/Assignment 01/New Folder With Items/rating.data");
        Scanner scan1 = new Scanner(path);
        while (scan1.hasNext()) {
            rating temp = new rating();
            temp.userid = Integer.parseInt(scan1.next());
            temp.movieid = Integer.parseInt(scan1.next());
            temp.rating = Integer.parseInt(scan1.next());
            temp.timestamp = Integer.parseInt(scan1.next());
            rate_data.add(temp);
        }
    }

    public static void getMoviedata(ArrayList<movie> movie_data) throws FileNotFoundException, IOException {
        String path = "/Users/shashwatchauhan/Documents/Assignment 01/New Folder With Items/movie.data";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split("\\|");
            movie temp = new movie();
            temp.movieid = Integer.parseInt(values[0]);
            temp.title = values[1];
            temp.release = values[2];
            temp.imdburl = values[4];
            for (int j = 0; j < 19; j++) {
                temp.genre.add(Integer.parseInt(values[5 + j]));
            }
            movie_data.add(temp);
        }
    }

    public static void getgenredata(genredata[] genre_data) throws FileNotFoundException, IOException {
        String path = "/Users/shashwatchauhan/Documents/Assignment 01/New Folder With Items/genre.data";
        BufferedReader br = new BufferedReader(new FileReader(path));

        StringTokenizer token;
        String line;
        int i = 0;
        int k = 0;
        while ((line = br.readLine()) != null && i < 19) {
            token = new StringTokenizer(line, "|");

            genre_data[i] = new genredata();
            genre_data[i].genre = token.nextToken();
            genre_data[i].genreid = Integer.parseInt(token.nextToken());

            i++;
        }
    }
}

