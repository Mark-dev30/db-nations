package org.java.nations.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/db-nations";
		String user = "root";
		String password = "code";
		try(Connection con = DriverManager.getConnection(url, user, password)) {
//		------MILESTONE 1------
			
//			String sql = "SELECT c.name, c.country_id , r.name, c2.name"
//					+ " FROM countries c"
//					+ " JOIN regions r ON c.region_id = r.region_id"
//					+ " JOIN continents c2 ON c2.continent_id = r.continent_id"
//					+ " ORDER BY c.name";
			
//      ------MILESTONE 2------
			
//			try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
//				while(rs.next()) {
//					final String name_country = rs.getString(1);
//					final int id = rs.getInt(2);
//					final String name_region = rs.getString(3);
//					final String name_continent = rs.getString(4);
//					System.out.println(name_country + " - " + id + " - " + name_region + " - " + name_continent);
//					
//				}
//				
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
			
//		------MILESTONE 3------
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Inserisci un parametro di ricerca: ");
			String word = sc.nextLine();
			
			String sql = "SELECT c.name, c.country_id , r.name, c2.name"
					+ " FROM countries c"
					+ " JOIN regions r ON c.region_id = r.region_id"
					+ " JOIN continents c2 ON c2.continent_id = r.continent_id"
					+ " WHERE c.name LIKE ?"
					+ " ORDER BY c.name";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, "%" + word + "%");
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
						final String name_country = rs.getString(1);
						final int id = rs.getInt(2);
						final String name_region = rs.getString(3);
						final String name_continent = rs.getString(4);
						System.out.println(name_country + " - " + id + " - " + name_region + " - " + name_continent);
					}
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
	
		} catch (SQLException ex) {
			
			ex.printStackTrace();
		}
	}
}