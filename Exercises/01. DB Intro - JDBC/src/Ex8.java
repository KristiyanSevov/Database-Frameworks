import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class Ex8 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ids = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                PreparedStatement psSelectMinion = conn.prepareStatement("SELECT name FROM minions\n" +
                        "WHERE id = ? ");
                PreparedStatement psUpdateMinion = conn.prepareStatement("UPDATE minions\n" +
                        "SET age = age + 1, name = ? \n" +
                        "WHERE id = ? ");
                Statement selectAll = conn.createStatement();
        ) {
            for (int id : ids) {
                psSelectMinion.setInt(1, id);
                try (ResultSet rs = psSelectMinion.executeQuery()) {
                    rs.first();
                    String name = rs.getString(1);
                    name = capitalize(name);
                    psUpdateMinion.setString(1, name);
                    psUpdateMinion.setInt(2, id);
                    psUpdateMinion.executeUpdate();
                }
            }
            try (ResultSet rs = selectAll.executeQuery("SELECT name, age FROM minions")){
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String capitalize(String fullName) {
        String[] names = fullName.split(" ");
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
        }
        return String.join(" ", names);
    }
}
