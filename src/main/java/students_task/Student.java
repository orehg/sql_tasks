package students_task;

import java.util.Objects;

public class Student {
    private long id;
    private String name;
    private int age;
    private String major;

    public Student(long id, String name, int age, String major) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, major);
    }

    @Override
    public String toString() {
        return "Student" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", age =" + age +
                ", major ='" + major;
    }
}

