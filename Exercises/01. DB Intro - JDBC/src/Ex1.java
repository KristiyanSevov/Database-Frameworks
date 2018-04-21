import java.sql.*;
import java.util.Properties;

public class Ex1 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/MinionsDB?createDatabaseIfNotExist=true", props);
             Statement stmt = conn.createStatement()
        ) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS towns(\n" +
                    "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(30),\n" +
                    "country VARCHAR(30));");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS minions(\n" +
                    "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(30),\n" +
                    "age INT,\n" +
                    "town INT,\n" +
                    "FOREIGN KEY (town) REFERENCES towns(id));");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS villains(\n" +
                    "id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "name VARCHAR(30),\n" +
                    "evilness_factor VARCHAR(30));");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS minions_villains(\n" +
                    "minion_id INT,\n" +
                    "villain_id INT,\n" +
                    "PRIMARY KEY (minion_id, villain_id),\n" +
                    "FOREIGN KEY (minion_id) REFERENCES minions(id),\n" +
                    "FOREIGN KEY (villain_id) REFERENCES villains(id));");

            stmt.executeUpdate("INSERT INTO towns (name, country)\n" +
                    "VALUES ('Sofia', 'Bulgaria'), ('London', 'England'), ('Plovdiv', 'Bulgaria'), \n" +
                    "('Paris', 'France'), ('Varna', 'Bulgaria');");

            stmt.executeUpdate("INSERT INTO minions (name, age, town)\n" +
                    "VALUES ('pesho', 22, 1), ('gosho', 25, 2), ('stamat', 30, 3), " +
                    "('prakash', 26, 2), ('ivan ivanov', 28, 1);");

            stmt.executeUpdate("INSERT INTO villains (name, evilness_factor)\n" +
                    "VALUES ('John', 'good'), ('Alan', 'bad'), ('Jack', 'evil'), ('Jim', 'super evil'),\n" +
                    "('Scott', 'good');");

            stmt.executeUpdate("INSERT INTO minions_villains\n" +
                    "VALUES (1, 2), (1, 3), (2, 2), (2, 3), (3, 2), (3, 4), (4, 1), (4, 2), (4, 3), (5, 2), (5, 3);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
