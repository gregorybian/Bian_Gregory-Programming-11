import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Friend {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    private String favoriteSport;
    private String favoriteGame;
    private String type;

    //Constructor
    Friend(String firstName, String lastName, int age, double height, String favoriteSport, String favoriteGame, String type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.favoriteSport = favoriteSport;
        this.favoriteGame = favoriteGame;
        this.type = type;
    }

    public void writeToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(firstName + ",\r");
        bw.write(lastName + "[\r");
        bw.write(Integer.toString(age) + "]\r");
        bw.write(Double.toString(height) + "{\r");
        bw.write(favoriteSport + "}\r");
        bw.write(favoriteGame + ":\r");
        bw.write(";\r");
        bw.close();
    }

    public boolean compareFriends(Friend f){
        if((this.firstName+this.lastName+this.age+this.height+this.favoriteSport+this.favoriteGame+this.type).equals(f.firstName+f.lastName+f.age+f.height+f.favoriteSport+f.favoriteGame+f.type)){
            return true;
        }
        else{
            return false;
        }
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public double getHeight() {
        return height;
    }
    public String getFavoriteSport() {
        return favoriteSport;
    }
    public String getFavoriteGame() {
        return favoriteGame;
    }
    public String getType() {
        return type;
    }

    //Method toString
    public String toString(){
        return firstName + " " + lastName;
    }
}
