package programa;

import java.sql.*;
import java.util.ArrayList;

public class AirportDAO {
    final static String URL = "jdbc:mysql://localhost:3306/airports";
    public void add(Airport airport){
        String query = "insert into sb_airports (biz_name, address, city)" +
                "values (?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko iterpti nauja irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida kuriant nauja irasa");
            e.printStackTrace();
        }
    }
    public ArrayList<Airport> searchByName(String bizName){
        String query2 = "SELECT * FROM sb_airports WHERE biz_name LIKE '"+bizName+"'";
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            //preparedStatement.setString(1,  "%"+bizName+"%");
            ResultSet resultSet = preparedStatement.executeQuery(query2);
            while (resultSet.next()){
                int id = resultSet.getInt("biz_id");
                String bizName2 = resultSet.getString("biz_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                airports.add(new Airport(id, bizName2, address, city));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieska pagal oro uosto pavadinima");
            System.out.println(airports);
        } catch (SQLException e) {
            System.out.println("Ivyko klaida atliekant paieska pagal oro uosto pavadinima");
            e.printStackTrace();
        }
        return airports ;
    }
    public void editById(Airport airport){
        String query = "update sb_airports set biz_name=?, address=?, city=?" +
                " where biz_id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setInt(4, airport.getBizId());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko paredaguoti esama irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida redaguojant esama irasa");
            e.printStackTrace();
        }
    }
    public void deleteById(int bizId){
        String query = "delete from sb_airports where biz_id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);
            preparedStatement.executeUpdate();

            System.out.println("Pavyko istrinti esama irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida istrinant esama irasa");
            e.printStackTrace();
        }
    }

}
