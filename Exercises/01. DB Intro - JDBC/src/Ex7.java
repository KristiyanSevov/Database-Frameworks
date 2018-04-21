import com.sun.org.apache.bcel.internal.generic.LXOR;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Ex7 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM minions");
        ) {
            List<String> names = new ArrayList<String>();
            while (rs.next()) {
                names.add(rs.getString(1));
            }
            int i = 0;
            int j = names.size() - 1;
            while (i <= j) {
                System.out.println(names.get(i++));
                if (i < j) {
                    System.out.println(names.get(j--));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
