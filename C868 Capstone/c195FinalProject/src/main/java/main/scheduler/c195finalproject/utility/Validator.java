package main.scheduler.c195finalproject.utility;

import java.time.*;

/**
 * The Validator class provides utility methods for validating various conditions.
 */
public abstract class Validator {

    /**
     * Checks if the given start and end times fall within business hours.
     *
     * @param startTime the start time to check
     * @param endTime   the end time to check
     * @return {@code true} if the start and end times fall within business hours, {@code false} otherwise
     */
    public static boolean checkBusinessHours(LocalDateTime startTime, LocalDateTime endTime) {
        //declare business hours start and end time
        LocalTime businessHoursStartTime = LocalTime.of(8,0); // 8:00 am
        LocalTime businessHoursEndTime = LocalTime.of(22, 0); // 10:00 pm
        //set the zone of EST
        ZonedDateTime easternTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        //combine business hours with the zoned time to create time in EST
        ZonedDateTime businessHoursStart = easternTime.with(businessHoursStartTime);
        ZonedDateTime businessHoursEnd = easternTime.with(businessHoursEndTime);

        //to apply a zone to our LocalTime Objects, we first run our method to convert the time, then we create a zoned time with timezone.
        //this might be considered weird practice, but my time conversion at least works.
        ZonedDateTime convertedStartTime = ZonedDateTime.from(TimeConvert.fromLocalToEastern(startTime));
        ZonedDateTime convertedEndTime = ZonedDateTime.from(TimeConvert.fromLocalToEastern(endTime));

        if (convertedStartTime.getHour() < businessHoursStart.getHour() || convertedEndTime.getHour() > businessHoursEnd.getHour()) {
            return true;
        }

        return false;

    }


}
