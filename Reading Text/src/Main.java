import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    //This is a method to search through the text and find a specific word then return which line/index position in the arraylist of the text that said word is on
    static ArrayList<Integer> findWords(ArrayList<String> text, String word){
        //This creates a arraylist to store all of the indexpositions
        ArrayList <Integer> indexPositions = new ArrayList<>();
        //This is a for loop that searches through each line in the text and if the line has the word we are searching for, it adds indexposition of the line to the arraylist we created.
        for (String line: text){;
            if (line.contains(word)){
                indexPositions.add(text.indexOf(line));
            }
        }
        return indexPositions;
    }

    public static void main(String[] args) throws IOException {
        //These following lines of code copied all the lines of the txt file into an arraylist. It uses some build in methods like FileReader and BufferedReader to see the lines in the text.
        ArrayList<String> text = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            text.add(line);
        }
        br.close();
    }
}
