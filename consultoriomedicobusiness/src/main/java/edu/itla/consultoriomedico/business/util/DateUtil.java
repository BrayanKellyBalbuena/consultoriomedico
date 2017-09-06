package edu.itla.consultoriomedico.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Brayan Kelly on 19/07/2017.
 */
public class DateUtil {

    public static LocalDate stringToDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(date, formatter);
    }
}

