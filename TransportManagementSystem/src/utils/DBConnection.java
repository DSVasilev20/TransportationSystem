package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection instance = null;

    public Connection getInstance(){
        if(instance == null){
            try {
                String url = "jdbc:sqlserver://packagemanagementsysmemserver.database.windows.net:1433;database=PackageManagementSystemDB;user=serveradmin@packagemanagementsysmemserver;password=PackageManagement!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

                instance = DriverManager.getConnection(url);
                return instance;
            } catch (SQLException exception){
                throw new RuntimeException(exception);
            }
        } else {
            return instance;
        }
    }
}