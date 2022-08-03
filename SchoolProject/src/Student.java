public class Student {
    private String firstName;
    private String lastName;
    private int grade;
    private static int studentNumber = 1;
    int idNum;

    //This is the constructor, it is a method that we use to create different students and set different fields for each different student
    Student(String firstName, String lastName, int grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        idNum = studentNumber;
        studentNumber ++;
    }
    //These are the getters which are needed since we used private fields so the only way to access those fields are with these getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getGrade() {
        return grade;
    }

    public int getIdNum() {
        return idNum;
    }

    //These are the setters which are need because the fields are private so the variables can't be modified but these setters allow us to do that
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
    //This is a method that prints out the full name of the student and their grade.
    public String  getStudentInfo(){
        return "Name: " + this.getFirstName() + " " + this.getLastName() + "\tGrade: " + this.getGrade();
    }
    //This overides the already existing toString method so that when we print out the class, it doesn't print out the location in memory
    public String toString(){
        return "Name: " + this.getFirstName() + " " + this.getLastName() + "\tGrade: " + this.getGrade();
    }
}
