package programa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("JDBC");
        Airport airport = new Airport( "pirmadienis", "Adresas", "Kaunas");
        Airport airport2 = new Airport(10, "antradienis", "Adresas", "Vilnius");
        AirportDAO airportDAO = new AirportDAO();
        //airportDAO.add(airport);
        airportDAO.searchByName("pirmadienis");
        airportDAO.editById(airport2);
        airportDAO.deleteById(10 );
    }
}
