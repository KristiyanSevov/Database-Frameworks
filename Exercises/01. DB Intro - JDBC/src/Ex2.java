import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;

public class Ex2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT v.name, COUNT(m_v.minion_id) AS count_minions FROM villains AS v\n" +
                        "JOIN minions_villains AS m_v ON v.id = m_v.villain_id\n" +
                        "GROUP BY v.name\n" +
                        "HAVING count_minions > 3\n" +
                        "ORDER BY count_minions DESC;");
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + ' ' + rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
