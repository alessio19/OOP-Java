package Model.dataUpdateModule;

import Model.dataAccessModule.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Alessio
 * @author Adam
 */
public abstract class Query {
	
    protected DBConnection connection;  
    protected PreparedStatement statement;
	
    public Query(DBConnection connection) throws SQLException {
        this.connection = connection;
    }
	
    public void prepareStatement(String[] param) throws SQLException {
        for (int i = 1; i <= param.length; ++i)
            this.statement.setString(i, param[i-1]);
    }
	
    protected abstract boolean executeQuery(String[] param) throws SQLException;

}