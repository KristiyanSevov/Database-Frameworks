import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Ex9 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                Statement stmt = conn.createStatement();
                PreparedStatement psSelectMinion =
                        conn.prepareStatement("SELECT name, age FROM minions WHERE id = ?;");
                CallableStatement csIncreaseAge = conn.prepareCall("CALL usp_get_older(?);")
        ) {
            stmt.executeUpdate("DROP PROCEDURE IF EXISTS usp_get_older;"); //not the best solution
            stmt.executeUpdate("CREATE PROCEDURE usp_get_older(minion_id INT)\n" +
                    "BEGIN\n" +
                    "\tUPDATE minions\n" +
                    "\tSET age = age + 1\n" +
                    "\tWHERE minion_id = id;\n" +
                    "END;");
            csIncreaseAge.setInt(1, id);
            csIncreaseAge.execute();
            psSelectMinion.setInt(1, id);
            try (ResultSet rs = psSelectMinion.executeQuery()){
                rs.first();
                System.out.println(rs.getString(1) + " " + rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
