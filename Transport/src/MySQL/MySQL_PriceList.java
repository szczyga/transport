package MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class MySQL_PriceList extends MySQL {
	
//	  Vector<String[]> cache; // will hold String[] objects . . .
//	  int colCount;
//	  String[] headers;
//	  MySQL_Conf conn;
//	  Connection db;
//	  Statement statement;

	public void getPriceLists(){
		
		String query="SELECT * from pricelist";
		
		setQuery(query);
			
	}
	
	public DefaultComboBoxModel<String> getPriceList(){
		
		String query="SELECT `pricelist_id`,`nazwa` FROM `pricelist`";
		
		setQuery(query);
		
		DefaultComboBoxModel<String> priceLists = new DefaultComboBoxModel<String>();
		
		for(int i=0; i<cache.size();i++){
			priceLists.addElement(cache.get(i)[1]);
		}
		
		
		return priceLists;
	}
	
	public void setPriceList(String[] buttons){
		
		String query="INSERT INTO "
				+ "`transport`.`pricelist` ("
				+ "`pricelist_id`, "
				+ "`nazwa`, "
				+ "`price`, "
				+ "`price_inw`, "
				+ "`price_km`, "
				+ "`price_idle`) "
				+ "VALUES "
				+ "(NULL, "
				+ "'"+buttons[0]+"', "
				+ "'"+buttons[1]+"', "
				+ "'"+buttons[2]+"', "
				+ "'"+buttons[3]+"', "
				+ "'"+buttons[4]+"');";
		
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void editPriceList(Vector<String> row){
		

		String query="UPDATE "
				+ "`transport`.`pricelist` "
				+ "SET "
				+ "`nazwa` = '"+row.get(1)+"', "
				+ "`price` = '"+row.get(2)+"', "
				+ "`price_inw` = '"+row.get(3)+"', "
				+ "`price_km` = '"+row.get(4)+"', "
				+ "`price_idle` = '"+row.get(5)+"' "
				+ "WHERE "
				+ "`pricelist`.`pricelist_id` = "+row.get(0);
		
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void delPriceList(String id){
		 String query="DELETE FROM `transport`.`pricelist` WHERE `pricelist`.`pricelist_id` ="+id;
		 
			try {
				statement.executeUpdate(query);
				getPriceLists();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public String getPriceListId(String nazwa){
		
		String query="SELECT pricelist_id from pricelist where nazwa='"+nazwa+"'";
		
		setQuery(query);
		
		if(cache.size()>0)
		return cache.get(0)[0];
		else
		return null; 
	}

	public String getPriceListName(String id){
		
		String query="SELECT nazwa from pricelist where pricelist_id='"+id+"'";
		
		setQuery(query);
		
		if(cache.size()>0)
		return cache.get(0)[0];
		else
		return null;
	}
}
