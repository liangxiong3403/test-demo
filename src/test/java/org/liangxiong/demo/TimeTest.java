package org.liangxiong.demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-03 22:30
 * @description
 **/
public class TimeTest {

    public static void main(String[] args) {
        testLocalTime();
    }

    private static void testInstant() {
        System.out.println(Instant.now());
        System.out.println(Instant.now().toString());
        System.out.println(Instant.now().atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().minus(Duration.ofHours(1)));
    }

    private static void testLocalDate() {
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().plusDays(1));
        System.out.println(LocalDate.now().getYear());
        System.out.println(LocalDate.now().getDayOfYear());
        System.out.println(LocalDate.now().getMonth());
        System.out.println(LocalDate.now().getMonthValue());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println(LocalDate.now().getDayOfYear());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println(LocalDate.now().getDayOfWeek());
        System.out.println(LocalDate.of(2021, 3, 3));
        System.out.println(LocalDate.of(2021, 3, 3).equals(LocalDate.now()));
        System.out.println(MonthDay.of(LocalDate.now().getMonth(), 3));
    }

    private static void testLocalTime() {
        System.out.println(LocalTime.now());
        System.out.println(LocalTime.now(ZoneId.of("Asia/Shanghai")));
        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.MIN);
        System.out.println(LocalTime.MIDNIGHT);
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.CHINA)));
        System.out.println(LocalTime.now().plus(1, ChronoUnit.HOURS));
    }
}
