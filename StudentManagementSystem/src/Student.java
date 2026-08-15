public class Student {

    private String name;
    private String email;
    private String course;
    private int age;

    public Student(String name, String email, String course, int age) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }
}
