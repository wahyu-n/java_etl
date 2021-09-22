import java.sql.*;
import java.io.*;

public class Main {

    static String userName = "username";
    static String password = "password";
    static String url = "jdbc:postgresql://127.0.0.1:5432/etl_db?user="+userName+"&password="+password;
    static String path = "../java_etl/ddk_tingkat_pendidikan.csv";


    public static void main(String[] args) {
        String select = "SELECT * FROM data_pendidikan";
        String createTable = "CREATE TABLE IF NOT EXISTS data_pendidikan (kode_provinsi INTEGER, " +
                "nama_provinsi VARCHAR(255), " +
                "tingkat_pendidikan VARCHAR(255), " +
                "jenis_kelamin VARCHAR(255), " +
                "jumlah_individu INTEGER)";
        try {
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Connection Success");
            Statement statement = connection.createStatement();

            System.out.println("Creating Table...");
            statement.executeUpdate(createTable);
            System.out.println("Table Created");

            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String read;
            reader.readLine();
            while ((read = reader.readLine()) != null) {
                String[] data = read.split(",");
                int kode_provinsi = Integer.parseInt(data[0]);
                String nama_provinsi = data[1];
                String tingkat_pendidikan = data[2];
                String jenis_kelamin = data[3];
                int jumlah_individu = Integer.parseInt(data[4]);

                String insert = "INSERT INTO data_pendidikan VALUES ("+kode_provinsi+", " +
                        "'"+nama_provinsi+"', " +
                        "'"+tingkat_pendidikan+"', " +
                        "'"+jenis_kelamin+"', " +
                        ""+jumlah_individu+" )";
                System.out.println("Reading File and Inserting to Database...");
                statement.executeUpdate(insert);
            }
            System.out.println(select);
            System.out.println("Reading Data from Database...");
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                System.out.println("------------------------------");
                System.out.println("Kode Provinsi : " + resultSet.getInt("kode_provinsi"));
                System.out.println("Nama Provinsi : " + resultSet.getString("nama_provinsi"));
                System.out.println("Tingkat Pendidikan : " + resultSet.getString("tingkat_pendidikan"));
                System.out.println("Jenis Kelamin : " + resultSet.getString("jenis_kelamin"));
                System.out.println("Jumlah Individu : " + resultSet.getInt("jumlah_individu"));
                System.out.println("------------------------------");
            }
            statement.close();
            connection.close();
            System.out.println("Connection Close");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}