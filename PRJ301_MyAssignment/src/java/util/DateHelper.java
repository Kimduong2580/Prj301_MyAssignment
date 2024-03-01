/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Kim Duong
 */
public class DateHelper {
    public static ArrayList<String> getDatesInWeek(LocalDate fromDate, LocalDate toDate) {
        ArrayList<String> datesInWeek = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (!fromDate.isAfter(toDate)) {
            String formattedDate = fromDate.format(formatter);
            datesInWeek.add(formattedDate); 
            fromDate = fromDate.plusDays(1);
        }

        return datesInWeek;
    }

    public static ArrayList<String> getNameDatesInWeek(LocalDate fromDate, LocalDate toDate) {
        ArrayList<String> datesInWeek = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E"); 
        while (!fromDate.isAfter(toDate)) {
            String formattedDate = fromDate.format(formatter);
            datesInWeek.add(formattedDate); 
            fromDate = fromDate.plusDays(1);
        }

        return datesInWeek;
    }
}
