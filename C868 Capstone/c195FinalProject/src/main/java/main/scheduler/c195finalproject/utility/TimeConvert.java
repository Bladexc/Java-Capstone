package main.scheduler.c195finalproject.utility;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TimeConvert class provides utility methods for converting time between different time zones.
 */
public abstract class TimeConvert {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static ZoneId localZoneId = ZoneId.systemDefault();
    private static ZoneId estZoneId = ZoneId.of("America/New_York");
    private static ZoneId utcZoneId = ZoneId.of("UTC");

    /**
     * Returns the formatter used for date and time conversion.
     *
     * @return the formatter
     */
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Sets the formatter used for date and time conversion.
     *
     * @param formatter the formatter to set
     */
    public static void setFormatter(DateTimeFormatter formatter) {
        TimeConvert.formatter = formatter;
    }

    /**
     * Returns the local time zone identifier.
     *
     * @return the local time zone identifier
     */
    public static ZoneId getLocalZoneId() {
        return localZoneId;
    }

    /**
     * Sets the local time zone identifier.
     *
     * @param localZoneId the local time zone identifier to set
     */
    public static void setLocalZoneId(ZoneId localZoneId) {
        TimeConvert.localZoneId = localZoneId;
    }

    /**
     * Returns the UTC time zone identifier.
     *
     * @return the UTC time zone identifier
     */
    public static ZoneId getUtcZoneId() {
        return utcZoneId;
    }

    /**
     * Sets the UTC time zone identifier.
     *
     * @param utcZoneId the UTC time zone identifier to set
     */
    public static void setUtcZoneId(ZoneId utcZoneId) {
        TimeConvert.utcZoneId = utcZoneId;
    }

    /**
     * Returns the Eastern time zone identifier.
     *
     * @return the Eastern time zone identifier
     */
    public static ZoneId getEstZoneId() {
        return estZoneId;
    }

    /**
     * Sets the Eastern time zone identifier.
     *
     * @param estZoneId the Eastern time zone identifier to set
     */
    public static void setEstZoneId(ZoneId estZoneId) {
        TimeConvert.estZoneId = estZoneId;
    }

    /**
     * Converts the given local date and time to UTC.
     *
     * @param localDateTime the local date and time to convert
     * @return the converted date and time in UTC
     */
    public static ZonedDateTime fromLocalToUTC(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(getLocalZoneId());

        ZonedDateTime utcTime = ZonedDateTime.ofInstant(zonedDateTime.toInstant(), getUtcZoneId());

        return utcTime;
    }

    /**
     * Converts the given UTC date and time to the local time zone.
     *
     * @param utcDateTime the UTC date and time to convert
     * @return the converted date and time in the local time zone
     */
    public static ZonedDateTime fromUTCToLocal(LocalDateTime utcDateTime) {
        ZonedDateTime zonedDateTime = utcDateTime.atZone(getUtcZoneId());

        ZonedDateTime localTime = ZonedDateTime.ofInstant(zonedDateTime.toInstant(), getLocalZoneId());

        return localTime;
    }

    /**
     * Converts the given local date and time to the Eastern time zone.
     *
     * @param localDateTime the local date and time to convert
     * @return the converted date and time in the Eastern time zone
     */
    public static ZonedDateTime fromLocalToEastern(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(getLocalZoneId());

        ZonedDateTime easternTime = ZonedDateTime.ofInstant(zonedDateTime.toInstant(), getEstZoneId());

        return easternTime;
    }

}
