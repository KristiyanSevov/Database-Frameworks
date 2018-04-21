import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Ex5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String country = reader.readLine();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                PreparedStatement psUpdate = conn.prepareStatement("UPDATE towns\n" +
                        "SET name = UPPER(name)\n" +
                        "WHERE country = ? ");
                PreparedStatement psSelect = conn.prepareStatement("SELECT name FROM towns\n" +
                        "WHERE country = ?")
        ) {
            psUpdate.setString(1, country);
            int rowsAffected = psUpdate.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No town names were affected.");
            } else {
                System.out.printf("%s town names were affected.%n", rowsAffected);
                psSelect.setString(1, country);
                try (ResultSet rs = psSelect.executeQuery()) {
                    List<String> names = new ArrayList<String>();
                    while (rs.next()){
                        names.add(rs.getString(1));
                    }
                    System.out.println(names);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
