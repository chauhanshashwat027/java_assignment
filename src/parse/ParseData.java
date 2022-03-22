package parse;
import model.*;

import java.lang.reflect.Array;
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

    public static ArrayList<Integer> GetTopMovieByGenre (List<rating> RateData, List<movie> MovieData){
        ArrayList<Integer> TopMovieByGenre = new ArrayList<Integer>(20);
        for (int i = 0; i < 20; i++) {
            TopMovieByGenre.add(0);
        }
        HashMap<Integer, Pair> AvgRating = new HashMap<Integer, Pair>();

        for(int i=0; i<RateData.size(); i++){
            Integer MovieId = RateData.get(i).movieid;
            if(!AvgRating.containsKey(MovieId)){
                Pair temp = new Pair();
                temp.first = 1;
                temp.second = RateData.get(i).rating;
                AvgRating.put(MovieId, temp);
            }
            AvgRating.get(MovieId).first++;
            AvgRating.get(MovieId).second += RateData.get(i).rating;
            AvgRating.get(MovieId).second /= AvgRating.get(MovieId).first;
        }

        for(int i=0; i<MovieData.size(); i++){
            int MovieId = MovieData.get(i).movieid;
            for(int j=0; j<MovieData.get(i).genre.size(); j++){
                if(MovieData.get(i).genre.get(j)==1){
                    if(AvgRating.get(MovieId).second > TopMovieByGenre.get(j)){
                        TopMovieByGenre.set(j, AvgRating.get(MovieId).second);
                    }
                }
            }
        }
        return TopMovieByGenre;
    }
    public static int MostWatchedMovie(List<rating> RateData){
        int MovieId;
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int i=0; i<RateData.size(); i++){
            MovieId = RateData.get(i).movieid;
            if(!freq.containsKey(MovieId)){
                freq.put(MovieId, 1);
            }
            freq.put(MovieId, freq.get(MovieId)+1);
        }
        int MaxFreq = 0;
        int MaxFreqMovie = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()){
            if (entry.getValue() > MaxFreq){
                MaxFreqMovie = entry.getKey();
                MaxFreq = entry.getValue();
            }
        }
        return MaxFreqMovie;
    }
    public static String MostWatchedGenre (List<rating> RateData, List<movie> MovieData, genredata GenreData[]) {
        int MovieId;
        Map<Integer, Integer> MovieFreq = new HashMap<Integer, Integer>();
        for (int i = 0; i < RateData.size(); i++) {
            MovieId = RateData.get(i).movieid;
            if (!MovieFreq.containsKey(MovieId)) {
                MovieFreq.put(MovieId, 1);
            }
            MovieFreq.put(MovieId, MovieFreq.get(MovieId) + 1);
        }
        Map<String, Integer> GenreFreq = new HashMap<String, Integer>();
        for (int i = 0; i < MovieData.size(); i++) {
            int movieId = MovieData.get(i).movieid;
            int movieFreq = MovieFreq.get(movieId);

            for (int j = 0; j < MovieData.get(i).genre.size(); j++) {
                if (MovieData.get(i).genre.get(j) == 1) {
                    if (!GenreFreq.containsKey(GenreData[j].genre)) {
                        GenreFreq.put(GenreData[j].genre, movieFreq);
                    }
                    GenreFreq.put(GenreData[j].genre, GenreFreq.get(GenreData[j].genre) + movieFreq);
                }
            }
        }
        int MaxFreq = 0;
        String MaxFreqGenre = "";
        for (Map.Entry<String, Integer> entry : GenreFreq.entrySet()) {
            if (entry.getValue() > MaxFreq) {
                MaxFreqGenre = entry.getKey();
                MaxFreq = entry.getValue();
            }
        }
        return MaxFreqGenre;
    }
    public static int MostActiveUser (List<rating> RateData){
        int UserId;
        Map<Integer, Integer> UserFreq = new HashMap<Integer, Integer>();
        for(int i=0; i<RateData.size(); i++){
            UserId = RateData.get(i).userid;
            if(!UserFreq.containsKey(UserId))
                UserFreq.put(UserId, 1);
            UserFreq.put(UserId, UserFreq.get(UserId)+1);
        }
        int MaxFreq = 0;
        int MaxFreqUser = 0;
        for (Map.Entry<Integer, Integer> entry : UserFreq.entrySet()){
            if(entry.getValue() > MaxFreq){
                MaxFreqUser = entry.getKey();
                MaxFreq = entry.getValue();
            }
        }
        return MaxFreqUser;
    }
    public static ArrayList<Integer> MovieRecommended (List<rating> RateData, List<movie> MovieData, int UserId) {
        ArrayList<Integer> WatchedMovie = new ArrayList<>();
        for (int i = 0; i < RateData.size(); i++) {
            if (RateData.get(i).userid == UserId)
                WatchedMovie.add(RateData.get(i).movieid);
        }
        Map<Integer, Pair> WatchedMovieRating = new HashMap<>();
        for (int i = 0; i < WatchedMovie.size(); i++) {
            int MovieId = WatchedMovie.get(i);
            for (int j = 0; j < RateData.size(); j++) {
                if (!WatchedMovieRating.containsKey(MovieId)) {
                    Pair temp = new Pair();
                    temp.first = 1;
                    temp.second = RateData.get(i).rating;
                    WatchedMovieRating.put(MovieId, temp);
                }
                WatchedMovieRating.get(MovieId).first++;
                WatchedMovieRating.get(MovieId).second += RateData.get(i).rating;
                WatchedMovieRating.get(MovieId).second /= WatchedMovieRating.get(MovieId).first;
            }
        }
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Pair> > list =
                new LinkedList<Map.Entry<Integer, Pair> >(WatchedMovieRating.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Pair> >() {
            public int compare(Map.Entry<Integer, Pair> o1,
                               Map.Entry<Integer, Pair> o2)
            {
                return (o1.getValue().second).compareTo(o2.getValue().second);
            }
        });

        // put data from sorted list to hashmap
        for (Map.Entry<Integer, Pair> aa : list) {
            WatchedMovieRating.put(aa.getKey(), aa.getValue());
        }
        int size = WatchedMovieRating.size();
        int loopVariable = 1;
        ArrayList<Integer> TopWatchedMovie = new ArrayList<>();
        for (Map.Entry<Integer, Pair> aa : WatchedMovieRating.entrySet()){
            if(loopVariable <= size/2){
                TopWatchedMovie.add(aa.getKey());
            }
        }
        Integer TopGenre[] = new Integer[19];
        for (int i=0; i<TopWatchedMovie.size(); i++){
            int MovieId = TopWatchedMovie.get(i);
            for(int j=0; j<MovieData.size(); i++){
                if(MovieData.get(j).movieid == MovieId){
                    for(int k=0; k<MovieData.get(j).genre.size(); k++){
                        if(MovieData.get(j).genre.get(k) == 1){
                            TopGenre[k] = 1;
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> NotWatchedMovies = new HashMap();
        for(int i=0; i<MovieData.size(); i++){
            int MovieId = MovieData.get(i).movieid;
            boolean flag = false;
            for (int j=0; j<RateData.size(); j++){
                if(RateData.get(i).movieid == MovieId){
                   continue;
                }
                flag = true;
            }
            if(!flag)
                NotWatchedMovies.put(MovieId, 1);
        }
        List<Integer> NotWatchedMovies2 = new ArrayList<>();
        for (Map.Entry<Integer, Integer> aa : NotWatchedMovies.entrySet()){
            NotWatchedMovies2.add(aa.getKey());
        }
        ArrayList<Integer> GenreMatchedMovies = new ArrayList<>();
        for(int i=0; i<NotWatchedMovies2.size(); i++){
            int MovieId = NotWatchedMovies2.get(i);
            for(int j=0; j<MovieData.size(); j++){
                if(MovieData.get(j).movieid == MovieId){
                    for(int k=0; k<MovieData.get(j).genre.size(); k++){
                        if(TopGenre[k]==1 && MovieData.get(j).genre.get(k)==1){
                            GenreMatchedMovies.add(MovieId);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> FinalMovieRecommendation = new ArrayList<>();
        int TotalMovies = 0;
        Map<Integer, Pair> TopGenreMovieRating = new HashMap<>();
        for (int i = 0; i < GenreMatchedMovies.size(); i++) {
            int MovieId = GenreMatchedMovies.get(i);
            for (int j = 0; j < RateData.size(); j++) {
                if (!TopGenreMovieRating.containsKey(MovieId)) {
                    Pair temp = new Pair();
                    temp.first = 1;
                    temp.second = RateData.get(i).rating;
                    TopGenreMovieRating.put(MovieId, temp);
                }
                TopGenreMovieRating.get(MovieId).first++;
                TopGenreMovieRating.get(MovieId).second += RateData.get(i).rating;
                TopGenreMovieRating.get(MovieId).second /= TopGenreMovieRating.get(MovieId).first;
            }
        }
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Pair> > list2 =
                new LinkedList<Map.Entry<Integer, Pair> >(TopGenreMovieRating.entrySet());

        // Sort the list
        Collections.sort(list2, new Comparator<Map.Entry<Integer, Pair> >() {
            public int compare(Map.Entry<Integer, Pair> o1,
                               Map.Entry<Integer, Pair> o2)
            {
                return (o1.getValue().second).compareTo(o2.getValue().second);
            }
        });

        // put data from sorted list to hashmap
        for (Map.Entry<Integer, Pair> aa : list2) {
            TopGenreMovieRating.put(aa.getKey(), aa.getValue());
        }
        for (Map.Entry<Integer, Pair> aa : TopGenreMovieRating.entrySet()){
            FinalMovieRecommendation.add(aa.getKey());
            TotalMovies++;
            if(TotalMovies >= 5) break;
        }
        return FinalMovieRecommendation;
    }
}

