import parse.*;
import model.*;
import java.io.*;
import java.util.*;
import java.util.Map;

public class movie_recommendation{
    public static void main(String args[]) throws IOException{
        ParseData ParseObj = new ParseData();

        List<user> UserData = new ArrayList<>();
        ParseObj.getuserdata((ArrayList<user>) UserData);

        List<rating> RateData = new ArrayList<>();
        ParseObj.getratingdata((ArrayList<rating>) RateData);

        List<movie> MovieData = new ArrayList<>();
        ParseObj.getMoviedata((ArrayList<movie>) MovieData);

        genredata GenreData[] = new genredata[20];
        ParseObj.getgenredata(GenreData);

        Map<Integer, String> MovieMap = new HashMap<Integer, String>();          //creating a map storing movie ids and movie title
        for (int i=0; i<MovieData.size(); i++){
            MovieMap.put(MovieData.get(i).movieid, MovieData.get(i).title);
        }

        ArrayList<Integer> TopMovieByGenre = ParseObj.GetTopMovieByGenre(RateData, MovieData);
        for (int i=0; i<TopMovieByGenre.size()-1; i++){
            System.out.println("Top Movie of " + GenreData[i].genre + " " + MovieMap.get(TopMovieByGenre.get(i)));
        }

        int MaxFreqMovie = ParseObj.MostWatchedMovie(RateData);
        System.out.println("Most Watched Movie " + MovieMap.get(MaxFreqMovie));

        String MaxFreqGenre = ParseObj.MostWatchedGenre(RateData, MovieData, GenreData);
        System.out.println("Most watched Genre is " + MaxFreqGenre);
    }
}

