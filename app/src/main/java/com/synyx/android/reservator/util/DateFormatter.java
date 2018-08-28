package com.synyx.android.reservator.util;

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
}
