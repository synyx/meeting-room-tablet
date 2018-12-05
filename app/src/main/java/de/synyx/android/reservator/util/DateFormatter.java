package de.synyx.android.reservator.util;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class DateFormatter {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            Locale.getDefault());

    public String formatToISOTime(Date date) {

        // we have to place the colon ourselves, as SimpleDateFormat only supports timezone without colon
        return dateFormat.format(date).replaceAll("(.*)(\\d\\d)$", "$1:$2");
    }


    // TODO: 07.12.18 - internationalisierung
    public static String formatToString(Duration remainingTime) {

        PeriodFormatter formatter = new PeriodFormatterBuilder().appendDays()
                .appendSuffix(" day", " days")
                .appendSeparator(" ")
                .appendHours()
                .appendSuffix(" h")
                .appendSeparator(" ")
                .appendMinutes()
                .appendSuffix(" min")
                .toFormatter();

        return formatter.print(remainingTime.toPeriod());
    }
}
