import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Ex4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        String minionName = inputs[1];
        int minionAge = Integer.parseInt(inputs[2]);
        String minionTown = inputs[3];
        inputs = reader.readLine().split(" ");
        String villainName = inputs[1];

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");
        try (
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/MinionsDB", props);
                PreparedStatement psFindTown = conn.prepareStatement("SELECT id FROM towns WHERE name = ?;");
                PreparedStatement psFindVillain = conn.prepareStatement("SELECT id FROM villains WHERE name = ?;");
                PreparedStatement psFindMinion = conn.prepareStatement("SELECT id FROM minions WHERE name = ?;");
                PreparedStatement psInsertTown = conn.prepareStatement("INSERT INTO towns (name) VALUES (?);");
                PreparedStatement psInsertVillain =
                        conn.prepareStatement("INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil');");
                PreparedStatement psInsertMinion =
                        conn.prepareStatement("INSERT INTO minions (name, age, town) VALUES (?, ?, ?);");
                PreparedStatement psAddMinionToVillain =
                        conn.prepareStatement("INSERT INTO minions_villains VALUES (?, ?);")

        ) {
            conn.setAutoCommit(false);
            psFindTown.setString(1, minionTown);
            psFindVillain.setString(1, villainName);
            try (ResultSet rsTown = psFindTown.executeQuery();
                 ResultSet rsVillain = psFindVillain.executeQuery()){
                if (!rsTown.first()){
                    psInsertTown.setString(1, minionTown);
                    psInsertTown.executeUpdate();
                    System.out.println("Town " + minionTown + " was added to the database.");
                }
                rsTown.close();
                if (!rsVillain.first()){
                    psInsertVillain.setString(1, villainName);
                    psInsertVillain.executeUpdate();
                    System.out.println("Villain " + villainName + " was added to the database.");
                }
                rsVillain.close();

                int townId = 0;
                int villainId = 0;
                try (ResultSet rsTownId = psFindTown.executeQuery();
                     ResultSet rsVillainId = psFindVillain.executeQuery()){
                    rsTownId.first();
                    townId = rsTownId.getInt(1);
                    rsVillainId.first();
                    villainId = rsVillainId.getInt(1);
                }

                psInsertMinion.setString(1, minionName);
                psInsertMinion.setInt(2, minionAge);
                psInsertMinion.setInt(3, townId);
                psInsertMinion.executeUpdate();

                psFindMinion.setString(1, minionName);
                try (ResultSet rsMinion = psFindMinion.executeQuery()) {
                    rsMinion.first();
                    int minionId = rsMinion.getInt(1);
                    rsMinion.close();
                    psAddMinionToVillain.setInt(1, minionId);
                    psAddMinionToVillain.setInt(2, villainId);
                    psAddMinionToVillain.executeUpdate();
                    System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
                }
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
