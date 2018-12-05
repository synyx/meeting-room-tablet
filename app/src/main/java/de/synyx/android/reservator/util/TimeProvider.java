package de.synyx.android.reservator.util;

import org.joda.time.DateTime;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface TimeProvider {


    default DateTime now() {

        return DateTime.now();
    }
}
