import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Ex6 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                PreparedStatement psRemoveRelationships =
                        conn.prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?;");
                PreparedStatement psDeleteVillain = conn.prepareStatement("DELETE FROM villains WHERE id = ?;");
                PreparedStatement psFindVillain = conn.prepareStatement("SELECT name FROM villains WHERE id = ?;")
        ) {
            conn.setAutoCommit(false);
            psFindVillain.setInt(1, id);
            try (ResultSet rs = psFindVillain.executeQuery()){
                if (!rs.first()){
                    System.out.println("No such villain was found");
                } else {
                    String name = rs.getString(1);
                    rs.close();
                    psRemoveRelationships.setInt(1, id);
                    int removedMinions = psRemoveRelationships.executeUpdate();
                    psDeleteVillain.setInt(1, id);
                    psDeleteVillain.executeUpdate();
                    conn.commit();
                    System.out.printf("%s was deleted%n", name);
                    System.out.printf("%d minions released%n", removedMinions);
                }
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
