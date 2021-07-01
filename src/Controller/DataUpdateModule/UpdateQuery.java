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

    public UpdateQuery(DBConnection connection, String query) throws SQLException {
        super(connection, query);
    }

    @Override
    public boolean executeQuery() throws SQLException {
        return this.statement.executeUpdate() == 1;
    }

}
