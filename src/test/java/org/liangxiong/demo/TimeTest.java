package org.liangxiong.demo;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangxiong
 * @version 1.0.0
 * @date 2021-03-03 22:30
 * @description
 **/
public class TimeTest {

    public static void main(String[] args) {
        /*Date startTime = DateUtil.parseDateTime("2021-08-06 12:20:00").toJdkDate();
        System.out.println("startTime: " + startTime);
        testBetween(startTime);
        testRestTime();*/
        System.out.println(DateUtil.hour(DateUtil.parseDateTime("2021-12-04 22:21:24"), true));
        test99();
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

    private static void testBetween(Date realEndDate) {
        long intervalHours = DateUtil.between(realEndDate, new Date(), DateUnit.HOUR);
        System.out.println("intervalHours: " + intervalHours);
        System.out.println("isSameDay: " + DateUtil.isSameDay(realEndDate, new Date()));
    }

    private static void testRestTime() {
        AtomicBoolean push = new AtomicBoolean(true);
        DateTime now = DateUtil.parseTime(DateUtil.format(new Date(), DatePattern.NORM_TIME_FORMAT.getPattern()));
        String trendNotDisturbStartTime = "21:00:00";
        String trendNotDisturbEndTime = "07:00:00";
        if (StrUtil.isNotEmpty(trendNotDisturbStartTime) && StrUtil.isNotEmpty(trendNotDisturbEndTime)) {
            DateTime limitStart = DateUtil.parseTime(trendNotDisturbStartTime);
            DateTime limitEnd = DateUtil.parseTime(trendNotDisturbEndTime);
            if (limitEnd.isBefore(limitStart)) {
                // 18:42:05 - 09:42:05
                int hourOfNow = now.hour(true);
                boolean noDisturb = (now.isAfterOrEquals(limitStart) && hourOfNow <= 24) || (hourOfNow >= 0 && now.isBeforeOrEquals(limitEnd));
                if (noDisturb) {
                    push.set(false);
                }
            } else if (now.isIn(limitStart, limitEnd)) {
                // 09:42:05 - 18:42:05
                push.set(false);
            }
        }
        System.out.println(push.get());
    }

    private static final int MORNING_START_TIME = 8;
    private static final int MORNING_END_TIME = 12;
    private static final int AFTERNOON_START_TIME = 14;
    private static final int AFTERNOON_END_TIME = 18;

    private static void test99() {
        List<String> times = ListUtil.toList("2021-12-04 00:01:26","2021-12-04 00:16:31","2021-12-04 00:32:33","2021-12-04 00:47:33","2021-12-04 03:42:07","2021-12-04 08:05:07","2021-12-04 08:10:44","2021-12-04 08:19:49","2021-12-04 08:20:52","2021-12-04 16:08:49","2021-12-04 16:56:27","2021-12-04 16:57:41","2021-12-04 16:58:46","2021-12-04 17:00:10","2021-12-04 17:01:11","2021-12-04 17:02:12","2021-12-04 17:03:14","2021-12-04 17:04:14","2021-12-04 17:05:16","2021-12-04 17:06:19","2021-12-04 17:07:21","2021-12-04 17:08:20","2021-12-04 17:09:22","2021-12-04 17:10:23","2021-12-04 17:11:24","2021-12-04 17:12:25","2021-12-04 17:13:27","2021-12-04 17:14:27","2021-12-04 17:15:29","2021-12-04 17:16:29","2021-12-04 17:17:30","2021-12-04 17:18:33","2021-12-04 17:19:33","2021-12-04 17:20:35","2021-12-04 17:21:38","2021-12-04 17:22:58","2021-12-04 17:24:10","2021-12-04 17:25:10","2021-12-04 17:26:14","2021-12-04 17:27:15","2021-12-04 17:28:15","2021-12-04 17:29:17","2021-12-04 17:31:09","2021-12-04 17:32:20","2021-12-04 17:33:20","2021-12-04 17:34:23","2021-12-04 17:35:23","2021-12-04 17:36:30","2021-12-04 17:37:46","2021-12-04 17:38:53","2021-12-04 17:40:13","2021-12-04 17:41:19","2021-12-04 17:43:24","2021-12-04 17:44:24","2021-12-04 17:45:28","2021-12-04 17:47:57","2021-12-04 17:50:04","2021-12-04 17:51:13","2021-12-04 17:52:25","2021-12-04 17:53:32","2021-12-04 17:54:43","2021-12-04 17:56:03","2021-12-04 17:57:06","2021-12-04 17:58:27","2021-12-04 17:59:33","2021-12-04 18:00:35","2021-12-04 18:01:44","2021-12-04 18:02:44","2021-12-04 18:04:05","2021-12-04 18:05:07","2021-12-04 18:06:15","2021-12-04 18:07:16","2021-12-04 18:08:19","2021-12-04 18:09:17","2021-12-04 18:10:32","2021-12-04 18:11:53","2021-12-04 18:13:19","2021-12-04 18:14:17","2021-12-04 18:15:17","2021-12-04 18:16:26","2021-12-04 18:17:35","2021-12-04 18:18:46","2021-12-04 18:19:50","2021-12-04 18:20:51","2021-12-04 18:21:56","2021-12-04 18:22:57","2021-12-04 18:24:02","2021-12-04 18:25:04","2021-12-04 18:26:15","2021-12-04 18:27:16","2021-12-04 18:28:16","2021-12-04 18:29:21","2021-12-04 18:30:32","2021-12-04 18:35:42","2021-12-04 19:38:05","2021-12-04 19:47:02","2021-12-04 20:33:31","2021-12-04 20:35:20","2021-12-04 20:38:16","2021-12-04 20:56:21","2021-12-04 20:58:29","2021-12-04 21:10:20","2021-12-04 21:29:02","2021-12-04 21:46:37","2021-12-04 21:59:13","2021-12-04 22:01:47","2021-12-04 22:21:24");
        AtomicInteger a = new AtomicInteger();
        times.forEach(timeStr -> {
            boolean status = checkWorkTime2(DateUtil.parseDateTime(timeStr).toJdkDate());
            if (status) {
                a.incrementAndGet();
                System.out.println(timeStr);
            }
        });
        System.out.println(a.get());

    }

    private static boolean checkWorkTime(Date date) {
        return (DateUtil.hour(date, true) >= MORNING_START_TIME && DateUtil.hour(date, true) < MORNING_END_TIME)
                || (DateUtil.hour(date, true) >= AFTERNOON_START_TIME && DateUtil.hour(date, true) < AFTERNOON_END_TIME);
    }

    private static boolean checkWorkTime2(Date date) {
        return DateUtil.hour(date, true) >= 8 && DateUtil.hour(date, true) < 22;
    }
}
