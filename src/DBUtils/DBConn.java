package DBUtils;

import java.sql.*;


public class DBConn {
    public Connection dbConnection(){
        String hostName = "itpgroup1.database.windows.net";
        String dbName = "SIT-NGTFH";
        String user = "dbadmin@itpgroup1";
        String password = "dbPassword!";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



}
