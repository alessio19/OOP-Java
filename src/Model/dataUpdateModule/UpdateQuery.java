package Model.dataUpdateModule;

import Model.dataAccessModule.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public class UpdateQuery extends Query {

    public UpdateQuery(Connection connection) throws SQLException {
        super(connection);
    }
    
    public boolean updateCustomer(String mail, String password, String column, String value) throws SQLException {
        String[] param = {value, mail, password};
        this.statement = this.connection.prepareStatement("UPDATE Customer SET " + column + " = ? WHERE mail = ? AND password = ?");
        return this.executeQuery(param);
    } 

    @Override
    protected boolean executeQuery(String[] param) throws SQLException {
        super.prepareStatement(param);
        return this.statement.executeUpdate() == 1;
    }

}
