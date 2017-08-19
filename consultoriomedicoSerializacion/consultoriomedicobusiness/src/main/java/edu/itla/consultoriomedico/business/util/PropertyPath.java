package edu.itla.consultoriomedico.business.util;

import edu.itla.consultoriomedico.business.enums.PathEnums;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyPath {

    private PropertyPath(){}

    public static Map<PathEnums,String> getProperties() {
        Properties prop = new Properties();
        InputStream inputStream = null;
        Map<PathEnums, String> mapProp = new HashMap<PathEnums, String>();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("config.properties");
        try {

            prop.load(input);

            mapProp.put(PathEnums.IMAGENES, prop.getProperty("consultorio.img"));
            mapProp.put(PathEnums.DATA_PACIENTES, prop.getProperty("consultorio.data.pacientes"));
            mapProp.put(PathEnums.DATA_MEDICOS, prop.getProperty("consultorio.data.medicos"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return mapProp;

    }
}
