package com.ducterry.base.ducterrybase.utils;

import com.ducterry.base.ducterrybase.commons.constant.FieldConstants;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final SimpleDateFormat DATE_TIME_FORMAT_FULL = new SimpleDateFormat(FieldConstants.STR_FULL_FORMAT);
    public static final SimpleDateFormat DATE_TIME_FORMAT_SHORT = new SimpleDateFormat(FieldConstants.STR_SHORT_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(FieldConstants.STR_SHORT_FORMAT);

    public static String dateShort2String(Date dateShort) {
        return DATE_TIME_FORMAT_SHORT.format(dateShort);
    }

    public static String dateFull2String(Date dateFull) {
        return DATE_TIME_FORMAT_FULL.format(dateFull);
    }

    public static String zoneDate2String(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DATE_TIME_FORMAT);
    }

    public static Long zoneDateTimeUTC2Long() {
        ZoneId zoneId = ZoneOffset.UTC;
        return ZonedDateTime.now(zoneId).toInstant().toEpochMilli();
    }

    public static Long getDuration(ZonedDateTime d1, ZonedDateTime d2) {
        return d1.toInstant().toEpochMilli() - d2.toInstant().toEpochMilli();
    }

    public static Long zonedDateTime2Long(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static Long zoneDateTimeLocal2Long(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant().atZone(FieldConstants.ZONEID)
                .toInstant()
                .toEpochMilli();
    }

    public static ZonedDateTime now() {
        ZoneId zoneId = ZoneOffset.UTC;
        return ZonedDateTime.now(zoneId);
    }

    public static ZonedDateTime long2ZoneDateTimeUTC(long milli) {
        Instant instant = Instant.ofEpochMilli(milli);
        return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public static ZonedDateTime long2ZoneDateTimeLocal(long milli) {
        Instant instant = Instant.ofEpochMilli(milli);
        return ZonedDateTime.ofInstant(instant, FieldConstants.ZONEID);
    }

    public static ZonedDateTime getStartOfDay(ZonedDateTime zdt) {
        return zdt.toLocalDate()
                .atStartOfDay()
                .atZone(zdt.getZone())
                .withLaterOffsetAtOverlap();
    }

    public static ZonedDateTime getEndOfDay(ZonedDateTime zdt) {
        return zdt.toLocalDate()
                .atStartOfDay()
                .atZone(zdt.getZone())
                .withLaterOffsetAtOverlap().plusDays(1);
    }

    public static ZonedDateTime string2ZoneDateTime(String str) {
        return ZonedDateTime.parse(str);
    }


}
