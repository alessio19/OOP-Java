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
public class UpdateQuery extends Query {

    public UpdateQuery(DBConnection connection) throws SQLException {
        super(connection);
    }
    
    public boolean updateCustomer(String mail, String password, String column, String value) throws SQLException {
        String[] param = {value, mail, password};
        this.statement = this.connection.getConnection().prepareStatement("UPDATE customer SET " + column + " = ? WHERE mail = ? AND password = ?");
        return this.executeQuery(param);
    } 

    @Override
    protected boolean executeQuery(String[] param) throws SQLException {
        super.prepareStatement(param);
        return this.statement.executeUpdate() == 1;
    }

}
