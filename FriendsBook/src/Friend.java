public class Friend {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    private String favoriteSport;
    private String favoriteGame;

    //Constructor
    Friend(String firstName, String lastName, int age, double height, String favoriteSport, String favoriteGame){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.favoriteSport = favoriteSport;
        this.favoriteGame = favoriteGame;
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

    //Method toString
    public String toString(){
        return firstName + " " + lastName;
    }
}
