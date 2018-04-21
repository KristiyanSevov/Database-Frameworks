import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Ex3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(reader.readLine());

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                PreparedStatement ps = conn.prepareStatement("SELECT m.name, m.age, v.name FROM minions AS m \n" +
                        "JOIN minions_villains AS m_v ON m.id = m_v.minion_id\n" +
                        "RIGHT JOIN villains AS v ON m_v.villain_id = v.id\n" +
                        "WHERE v.id = ? \n");
        ) {
            ps.setInt(1, villainId);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.first()){
                    System.out.println("No villain with ID " + villainId + " exists in the database.");
                } else if (rs.getString(1) == null){
                    System.out.println("Villain: " + rs.getString(3));
                } else{
                    int counter = 1;
                    System.out.println("Villain: " + rs.getString(3));
                    do {
                        System.out.printf("%d. %s %d%n", counter, rs.getString(1), rs.getInt(2));
                        counter++;
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
