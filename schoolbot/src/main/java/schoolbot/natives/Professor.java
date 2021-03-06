package schoolbot.natives;

import java.io.Serializable;
import java.util.HashMap;

import net.dv8tion.jda.internal.entities.GuildImpl;
import schoolbot.natives.util.Majors;

public class Professor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3024391926657713863L;
    private HashMap<String, Classroom> professorsClasses;
    private HashMap<Student, Classroom> studentsInClasses;
    private HashMap<String, Assignment> assignments;

    private String email;
    private String emailPrefix;
    private transient GuildImpl guild;
    private String firstName;
    private String lastName;
    private int age;
    private String officeHours;
    private Majors department;
    private School professorsSchool;

    public Professor() {
        professorsClasses = new HashMap<>();
        studentsInClasses = new HashMap<>();
        assignments = new HashMap<>();
    }

    public Professor(GuildImpl guild, String firstName, String lastName, String email, School professorsSchool) {
        this.email = email;
        this.emailPrefix = email;
        this.guild = guild;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professorsSchool = professorsSchool;
        professorsClasses = new HashMap<>();
        studentsInClasses = new HashMap<>();
        assignments = new HashMap<>();

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstNameme) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGuild(GuildImpl guild) {
        this.guild = guild;
    }

    public void setProfessorsClasses(HashMap<String, Classroom> professorsClasses) {
        this.professorsClasses = professorsClasses;
    }

    public void setStudentsInClasses(HashMap<Student, Classroom> studentsInClasses) {
        this.studentsInClasses = studentsInClasses;
    }

    public String getEmailPrefix() {
        return email;
    }

    public String getEmail() {
        return email + professorsSchool.getEmailSuffix();
    }

    public String getfirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GuildImpl getGuild() {
        return guild;
    }

    public HashMap<String, Classroom> getProfessorsClasses() {
        return professorsClasses;
    }

    public HashMap<Student, Classroom> getStudentsInClasses() {
        return studentsInClasses;
    }

    public void addClass(Classroom clazz) {
        professorsClasses.putIfAbsent(clazz.getClassNum(), clazz);
    }

    public void addStudent(Student student, Classroom clazz) {
        studentsInClasses.putIfAbsent(student, clazz);
    }

    public boolean removeClass(Classroom clazz) {
        if (professorsClasses.containsKey(clazz.getClassID())) {
            professorsClasses.remove(clazz.getClassID());
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if (studentsInClasses.containsKey(student.getRealName())) {
            studentsInClasses.remove(student.getRealName());
            return true;
        }
        return false;
    }

    public void addAssignment(Assignment assignemnt) {
        assignments.putIfAbsent(assignemnt.getAssignmentRef(), assignemnt);
    }

    public boolean removeAssignment(Assignment assignemnt) {
        if (assignments.containsKey(assignemnt.getAssignmentRef())) {
            assignments.remove(assignemnt.getAssignmentRef());
            return true;
        }
        return false;
    }

    /**
     * @return String return the name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param name the name to set
     */
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return String return the officeHours
     */
    public String getOfficeHours() {
        return officeHours;
    }

    /**
     * @param officeHours the officeHours to set
     */
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public void setEmailPrefix(String emailPrefix) {
        this.emailPrefix = emailPrefix;
    }

    /**
     * @return School return the professorsSchool
     */
    public School getProfessorsSchool() {
        return professorsSchool;
    }

    public HashMap<String, Assignment> getAssignments() {
        return assignments;
    }

    /**
     * @param professorsSchool the professorsSchool to set
     */
    public void setProfessorsSchool(School professorsSchool) {
        this.professorsSchool = professorsSchool;
    }

    @Override
    public String toString() {
        return lastName + "'s Current University Employer: " + getProfessorsSchool().getSchoolName() + "\n" + lastName
                + "'s Last Name: " + getLastName() + "\n" + lastName + "'s First Name: " + getFirstName() + "\n"
                + lastName + "'s Email: " + getEmail() + "\n" + lastName + "'s Office Hours: " + getOfficeHours() + "\n " + "Amount of classes: " + professorsClasses.size() + "\n" +
                "-------------------------------------------------------------\n";
    }

}
