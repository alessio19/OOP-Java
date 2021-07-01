/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.DataUpdateModule;

import Controller.DataAccessModule.DBConnection;
import java.sql.SQLException;

/**
 *
 * @author Alessio
 */
public class DeleteQuery extends Query {

    public DeleteQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean deleteCustomer(String mail, String password) throws SQLException {
        String[] param = {mail, password};
        this.statement = this.connection.getConnection().prepareStatement("DELETE FROM customer WHERE mail = ? AND password = ?");
        super.prepareStatement(param);
        System.out.println(this.statement.toString());
        return this.executeQuery();
    } 

    @Override
    protected boolean executeQuery() throws SQLException {
        return this.statement.execute();
    }

}

