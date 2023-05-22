package org.java.nations.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	//	------BONUS------
			System.out.println("Inserisci id: ");
			String selectId = sc.nextLine();
			sql = "SELECT c.name ,l.`language`,cs.`year` ,cs.population ,cs.gdp"
					+ " FROM countries c"
					+ " JOIN country_stats cs ON c.country_id = cs.country_id"
					+ " JOIN country_languages cl ON c.country_id = cl.country_id "
					+ " JOIN languages l ON cl.language_id = l.language_id"
					+ " WHERE c.country_id = ? AND cs.`year` = (SELECT  MAX(cs2.`year`) FROM country_stats cs2)";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1,selectId);
				try (ResultSet rs = ps.executeQuery()) {
					String name_country = null;
					String year = null;
					String population = null;
					String gdp = null;
					String languages =" ";
					while(rs.next()) {
						name_country = rs.getString(1);
						languages += rs.getString(2) + " ";
						year = rs.getString(3);
						population = rs.getString(4);
						gdp = rs.getString(5);
					}
					System.out.println("Details Country: " + name_country
										+ "\nLanguages: " + languages
										+ "\nMost recent stats"
										+ "\nYear: " + year
										+ "\nPopulation: " + population
										+ "\nGDP: " + gdp);
					
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