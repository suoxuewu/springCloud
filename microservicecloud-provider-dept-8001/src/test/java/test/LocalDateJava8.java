package test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateJava8 {
    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(current);
        System.out.println(timestamp);
//        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//        String format = isoLocalDateTime.format(LocalDateTime.now());
//        System.out.println(format);
//        ZonedDateTime now = ZonedDateTime.now();
//        System.out.println(now);
//        LocalDateTime now1 = LocalDateTime.now();
//        System.out.println(now1);
//        LocalTime now = LocalTime.now();
//        System.out.println(now);
//        LocalDate now = LocalDate.now();
//        LocalDate localDate = now.plusYears(3);
//        System.out.println("3ye="+localDate);
//        LocalDate localDate1 = now.minusYears(3);
//        System.out.println("minu="+localDate1);

    }
}
