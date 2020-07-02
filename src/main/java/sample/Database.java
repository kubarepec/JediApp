package sample;

import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.sql.*;

public class Database {

    private static final String url= "jdbc:postgresql://localhost:5432/Jedi";
    private static final String user="postgres";
    private static final String password="kuba123";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Database() throws ClassNotFoundException, SQLException {



    }

    public static void importDB(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery("Select * FROM JEDI;");
            while(data.next()){
               Jedi j= new Jedi(data.getString("JNAME"), KolorMiecza.valueOf(data.getString("KOLORMIECZA")),data.getInt("MOC"),data.getBoolean("CZYJASNA"),data.getInt("ID_JEDI"));
               System.out.println(j);
            }
            data = statement.executeQuery("Select * FROM ZAKON;");
            while(data.next()){
                new Zakon(data.getString("ZNAME"),data.getInt("ID_ZAKON"));

            }
            data = statement.executeQuery("Select * FROM JEDI_ZAKON;");
            while(data.next()){
                int jedi_id=data.getInt("JEDI_ID");
                int zakon_id=data.getInt("ZAKON_ID");
                Jedi j=Jedi.listaJedi.get(jedi_id-1);
                Zakon z=Zakon.listaZakon.get(zakon_id-1);
                j.setZakon(z);
                z.dodajJedi(j);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static long JediInsertDB(Jedi j){
        String SQLinsert="INSERT INTO JEDI(JNAME,KOLORMIECZA,MOC,CZYJASNA)" + "VALUES(?,?,?,?)";
        long id=0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(SQLinsert,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,j.getNazwa());
            statement.setString(2,j.getKolor().name());
            statement.setInt(3,j.getMoc());
            statement.setBoolean(4,j.getCzyJasna());
            int affectedRows=statement.executeUpdate();
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public static long ZakonInsertDB(Zakon z){
        String SQLinsert="INSERT INTO ZAKON(ZNAME)" + "VALUES(?)";
        long id=0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement(SQLinsert,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,z.getNazwa());
            int affectedRows=statement.executeUpdate();
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public static long JediZakonInsertDB(Zakon z,Jedi j){
        String SQLinsert="INSERT INTO JEDI_ZAKON(JEDI_ID,ZAKON_ID)" + "VALUES(?,?)";
        long id=0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);

            PreparedStatement statement = connection.prepareStatement(SQLinsert,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,j.getIdJedi());
            statement.setInt(2,z.getIdZakon());
            int affectedRows=statement.executeUpdate();
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
