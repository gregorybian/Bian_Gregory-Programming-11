import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        //This uses the school class to create a secondary school named after my name, Gregory. I assign it the fields within the constructor.
        School schoolMain = new School("Greg Secondary school", "123 Greg Street", "Private Secondary School");

        //These are the 10 students I have created for the school, They all are assigned fields according to the constructor.
        Student Bob = new Student("Bob", "Vlad", 10);
        Student Joe = new Student("Joe", "Holonski", 9);
        Student Fred = new Student("Fred", "Calmurn", 12);
        Student John = new Student("John", "Dome", 8);
        Student Will = new Student("Will", "Claster", 11);
        Student Micky = new Student("Micky", "Sasha", 12);
        Student Sophia = new Student("Sophia", "Macky", 9);
        Student Jenny = new Student("Jenny", "Blern", 10);
        Student Natasha = new Student("Natasha", "Trendova", 8);
        Student Matt = new Student("Matt", "Bradley", 11);

        //This uses the add student method we created in the school class to add the students to the student list
        schoolMain.addStudent(Bob);
        schoolMain.addStudent(Joe);
        schoolMain.addStudent(Fred);
        schoolMain.addStudent(John);
        schoolMain.addStudent(Will);
        schoolMain.addStudent(Micky);
        schoolMain.addStudent(Sophia);
        schoolMain.addStudent(Jenny);
        schoolMain.addStudent(Natasha);
        schoolMain.addStudent(Matt);

        //These are 3 teacher created for the school and have all been assigned the different fields in the constructor
        Teacher Allen = new Teacher("Allen", "Gasmi", "French");
        Teacher Ralph = new Teacher("Ralph", "Ralla", "English");
        Teacher Trent = new Teacher("Trent", "Prent", "Socials");

        //This uses the add teacher method in the school class to add the teachers to the teacher list in the teacher class.
        schoolMain.addTeacher(Allen);
        schoolMain.addTeacher(Ralph);
        schoolMain.addTeacher(Trent);

        //This shows all the students and teachers in the school using the showstudents and showteacher method we created in the school class
        System.out.println("\nHere is a list of the students");
        schoolMain.showStudents();
        System.out.println("\nHere is a list of the teachers");
        schoolMain.showTeachers();

        //This deletes 2 students and 1 teacher using the deletestudent and deleteteacher method we created in the school class.
        schoolMain.deleteStudent(Matt);
        schoolMain.deleteStudent(Natasha);
        schoolMain.deleteTeacher(Trent);

        //This prints out all the remaining students and teachers after deleting some of them
        System.out.println("___________________________________________________________________");
        System.out.println("\nHere is the new list of the students");
        schoolMain.showStudents();
        System.out.println("\nHere is the new list of the teachers");
        schoolMain.showTeachers();
    }
}
