package yazlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class baglanti {		
	static Connection cn=null;
	static String dataname="yazlab1";
	static String url ="jdbc:mysql://localhost:3306/"+ dataname+"?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
	static String username="root";
	static String password="1234";
	static PreparedStatement ps;
	static Statement St;
	static  ResultSet yap () {
	
	ResultSet myRs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			cn=DriverManager.getConnection(url, username, password);
//			PreparedStatement ps=cn.prepareStatement("SELECT * FROM deneme.denemee");
			ps=cn.prepareStatement("SELECT isletmeler.isletmeID,isletmeler.ÝsletmeAdi,\r\n" + 
					" fis.tarih, fis.fisNo,fis.toplamFiyat,\r\n" + 
					"  urunler.urunler, urunler.kdv, urunler.fiyat\r\n" + 
					"FROM ((isletmeler\r\n" + 
					"INNER JOIN fis ON isletmeler.isletmeID = fis.isletmeID)\r\n" + 
					"INNER JOIN urunler ON fis.FisNoID = urunler.FisNoID);");
			 		
			
	
			
			 myRs=ps.executeQuery();
			//ata=ps.executeQuery(sql);
			//java.sql.Statement myStart= (java.sql.Statement) baglanti.createStatement();
			//myRs= myStart.executeQuery("select * from deneme.denemee");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hata:"+e.getMessage());
		}
		return myRs;
	}
	
	static ResultSet sorgula(String sql_sorgu)
	{
		ResultSet myRs=null;
		try {
			myRs=ps.executeQuery(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myRs;
	}
	static void ekle (String sql_sorgu)
	{
		try {
			ps.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
