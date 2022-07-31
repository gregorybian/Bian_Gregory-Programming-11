import java.lang.reflect.Array;
import java.util.ArrayList;

public class School {

    //These are the fields of the class and they describe certain parts of the school like it's name, location, type, etc
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList courses = new ArrayList<>();
    private String schoolName;
    private String schoolLocation;
    private String schoolType;

    //This is the constructor, it is a method that we use to create different schools and set different parameters for each different school
    School(String schoolName, String schoolLocation, String schoolType){
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
        this.schoolType = schoolType;
    }
    //These are the getters which are needed since we used private fields so the only way to access those fields are with these getters
    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public String getSchoolType() {
        return schoolType;
    }

    //These are the setters which are need because the fields are private so the variables can't be modified but these setters allow us to do that
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    //These following methods use the arraylists that we created as one of our fields in this school class to either add or delete a teacher/student from the arraylist
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void deleteTeacher(Teacher teacher){
        teachers.remove(teacher);
    }
    public void deleteStudent(Student student){
        students.remove(student);
    }
    //This prints out all of the teachers in the teacher arraylist that we created in the fields of this class
    public void showTeachers(){
        for(int i = 0; i<teachers.size(); i++){
            System.out.println(teachers.get(i).getTeacherInfo());
        }
    }

    //This prints out all of the students in the student arraylist that we created in thefields of this class
    public void showStudents(){
        for(int i = 0; i<students.size(); i++){
            System.out.println(students.get(i).getStudentInfo());
        }
    }

}
