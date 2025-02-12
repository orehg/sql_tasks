package students_task;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseHelper {

    public static FileInputStream fileInputStream;
    public static ResultSet resultSet;
    public static Statement statement;
    public static Connection connection;
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";


    public static void main(String[] args) throws IOException {
        fileInputStream = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");

        try { // 1. Зарегистрируйте драйвер JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Откройте соединение
            connection = DriverManager.getConnection(url, login, password);
            // 3. Создайте заявление
            statement = connection.createStatement();
            // 4. Выполните запрос
            resultSet = statement.executeQuery(SELECT_ALL_STUDENTS);
            // 5. Обработайте результат
            while (resultSet.next()) {
                // Получите данные по колонкам
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String major = resultSet.getString("major");
                // Выведите данные
                System.out.println("cityId: " + id + ", Name: " + name + ", Population: " + age + ", Location: " + major);
            }
        } catch (SQLException se) { // Обработка ошибок для JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Обработка ошибок дляClass.forName
            e.printStackTrace();
        } finally { // Закрытие ресурсов
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}

