package students_task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, age, major) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getMajor());
            statement.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name = ?, age = ?, major = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getMajor());
            statement.setLong(4, student.getId());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(int studentId) throws SQLException {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String major = resultSet.getString("major");
                students.add(new Student(id, name, age, major));
            }
        }
        return students;
    }
}
