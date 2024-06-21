package com.project.ebossy.util;

import java.time.LocalDate;
import java.time.Period;

public class UtilDate {
    public static int calculAge(LocalDate localDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localDate, currentDate);
        return period.getYears();
    }
}
