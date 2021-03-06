import java.io.BufferedReader;
import java.sql.*;
import java.util.Arrays;

public class Data {

    private static final String SQL_QUERY = "INSERT INTO schools(x_coord, y_coord, fid, name, address, board) VALUES (?, ?, ?, ?, ?, ?)";

    public Data(BufferedReader reader) {

        try {
            // get connnection
            Connection con = Main.DB_CONNECTION.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_QUERY);

            String row;
            while ((row = reader.readLine()) != null) {

                // skip the header line
                if (row.contains("XCoord")) {
                    continue;
                }

                String[] data = row.split(",");

                // build the sql query
                ps.setFloat(1, Float.parseFloat(data[0]));
                ps.setFloat(2, Float.parseFloat(data[1]));
                ps.setInt(3, Integer.parseInt(data[2]));
                ps.setString(4, data[3]);
                ps.setString(5, data[4]);
                ps.setString(6, data[5]);

                ps.addBatch();
            }

            System.out.println(
                    "Inserted " + ps.executeBatch().length + " rows."
            );

            // closing the connections
            ps.close();
            Main.DB_CONNECTION.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
