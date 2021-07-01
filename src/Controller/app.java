/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.DataAccessModule.DBConnection;
import Controller.DataUpdateModule.DeleteQuery;
import Controller.DataUpdateModule.InsertQuery;
import Controller.DataUpdateModule.UpdateQuery;
import Controller.InformationSearchModule.SelectQuery;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public class app {
    public static void main(String[] args) throws SQLException {
        DBConnection con = new DBConnection("root", "root");
        //try to register
        //InsertQuery iq = new InsertQuery(con);
        //iq.insertCustomer("test@test.com", "password", "name", "lastName", "regular");
        
        //delete
        //DeleteQuery dq = new DeleteQuery(con);
        //dq.deleteCustomer("test@test.com", "password");
        
        //update
        //UpdateQuery uq = new UpdateQuery(con);
        //uq.updateCustomer("test@test.com", "password", "memberType", "senior");
        
        //get customers
        SelectQuery sq = new SelectQuery(con);
        app.displayResult(sq.getCustomer("test@test.com", "password"));
    }
    
    public static void displayResult(ResultSet rs) throws SQLException {
        while (rs.next())
            System.out.println(rs.getString("mail") + " " 
                    + rs.getString("password") + " "
                    + rs.getString("name") + " "
                    + rs.getString("lastName") + " "
                    + rs.getString("memberType"));
    }
}
