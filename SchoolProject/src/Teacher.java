public class Teacher {
    private String firstName;
    private String lastName;
    private String subject;

    //This is the constructor, it is a method that we use to create different teachers and set different parameters for each different teacher
    Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    //These are the getters which are needed since we used private fields so the only way to access those fields are with these getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }

    //These are the setters which are need because the fields are private so the variables can't be modified but these setters allow us to do that
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //This is a method that prints the full name and subject of the teacher, this will be used in the school class to print out all of the information of the teachers using one method.
    public String getTeacherInfo(){
        return "Name: " + this.getFirstName() + " " + this.getLastName() + "\tSubject: " + this.getSubject();
    }
}
