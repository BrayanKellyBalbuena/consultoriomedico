package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.enums.PropertyEnum;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyDB {

    private PropertyDB() {
    }

    public static Map<PropertyEnum, String> getProperties() {
        Properties prop = new Properties();
        InputStream inputStream = null;
        Map<PropertyEnum, String> mapProp = new HashMap<>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("config.properties");
        try {

            prop.load(input);

            // mapProp.put(PropertyDB.IMAGENES, prop.getProperty("consultorio.img"));
            mapProp.put(PropertyEnum.DRIVER, prop.getProperty("jdbc.driver"));
            mapProp.put(PropertyEnum.URL, prop.getProperty("jdbc.url"));
            mapProp.put(PropertyEnum.USER, prop.getProperty("jdbc.username"));
            mapProp.put(PropertyEnum.PASSWORD, prop.getProperty("jdbc.password"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mapProp;

    }
}
