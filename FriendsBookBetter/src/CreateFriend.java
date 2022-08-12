import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreateFriend {
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friend> allFriends = new ArrayList<>();
    private static ArrayList<Friend> maleFriends = new ArrayList<>();
    private static ArrayList<Friend> femaleFriends = new ArrayList<>();
    private static ArrayList<Friend> bestFriends = new ArrayList<>();

    public static ArrayList createAllFriends(String fileName) throws IOException {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String friendString = "";
        while ((line = br.readLine()) != null) {
            if (!line.equals(";")) {
                friendString += line;
            } else {
                parseFriend(friendString, fileName);
                friendString = "";
            }
        }
        if (fileName.equals("FriendMale.txt")) {
            return maleFriends;
        } else if (fileName.equals("FriendFemale.txt")) {
            return femaleFriends;
        } else if (fileName.equals("FriendBest.txt")) {
            return bestFriends;
        } else {
            return allFriends;
        }
    }
    private static void parseFriend(String string, String fileName){
        int pos = 0;
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        int pos5 = 0;
        int pos6 = 0;
        String firstName = "";
        String lastName = "";
        int age = 0;
        double height = 0;
        String favoriteSport = "";
        String favoriteGame = "";
        String type = "";

        for (int i = 0; i<string.length(); i++){
            if(string.substring(i,i+1).equals(",")){
                pos = i;
                firstName = string.substring(0,pos);
            }else if(string.substring(i,i+1).equals("[")){
                pos2 = i;
                lastName = string.substring(pos + 1,pos2);
            }else if(string.substring(i,i+1).equals("]")) {
                pos3 = i;
                age = Integer.parseInt(string.substring(pos2 + 1, pos3)) ;
            }else if(string.substring(i,i+1).equals("{")) {
                pos4 = i;
                height = Double.parseDouble(string.substring(pos3 + 1, pos4));
            }else if(string.substring(i,i+1).equals("}")) {
                pos5 = i;
                favoriteSport = string.substring(pos4 + 1, pos5);
            }else if(string.substring(i,i+1).equals(":")) {
                pos6 = i;
                favoriteGame = string.substring(pos5 + 1, pos6);
            }
        }
        Friend friend = new Friend(firstName, lastName, age, height, favoriteSport, favoriteGame, type);

        boolean same=false;
        if(fileName.equals("FriendAll.txt")){
            for(Friend f : allFriends){
                if(f.compareFriends(friend)){
                    same=true;
                    break;
                }
            }
        }
        else if(fileName.equals("FriendBest.txt")) {
            for (Friend f : bestFriends) {
                if (f.compareFriends(friend)) {
                    same = true;
                    break;
                }
            }
        }
        else if(fileName.equals("FriendMale.txt")){
            for(Friend f : maleFriends){
                if(f.compareFriends(friend)){
                    same=true;
                    break;
                }
            }
        }
        else if(fileName.equals("FriendFemale.txt")){
            for(Friend f : femaleFriends){
                if(f.compareFriends(friend)){
                    same=true;
                    break;
                }
            }
        }
        if(!same){
            if(fileName.equals("FriendMale.txt")){
                maleFriends.add(friend);
            }
            else if(fileName.equals("FriendFemale.txt")){
                femaleFriends.add(friend);
            }
            else if(fileName.equals("FriendBest.txt")) {
                bestFriends.add(friend);
            }
            else {
                allFriends.add(friend);
            }
        }

    }

}
