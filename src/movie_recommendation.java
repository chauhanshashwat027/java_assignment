import model.*;
import java.io.*;
import java.util.*;
public class movie_recommendation {
    public static void main(String[] args){

    }
}

class parseData {
    public static void fetchuserdata(ArrayList<user> nuser_data) throws FileNotFoundException, IOException {
        String path = "/Users/shashwatchauhan/Documents/Assignment 01/New Folder With Items";
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
            nuser_data.add(temp);
        }
    }
}

