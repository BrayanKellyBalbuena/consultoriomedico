package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.enums.PropertyEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionDB {

    private Connection connection;

    public void loadDriver(String driverName) throws ClassNotFoundException {
        Class.forName(driverName);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Map<PropertyEnum, String> map = PropertyDB.getProperties();
        loadDriver(map.get(PropertyEnum.DRIVER));
        connection = DriverManager.getConnection(map.get(PropertyEnum.URL),
                map.get(PropertyEnum.USER), map.get(PropertyEnum.PASSWORD));

        return connection;
    }
}
