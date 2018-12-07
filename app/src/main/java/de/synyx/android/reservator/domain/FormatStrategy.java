package de.synyx.android.reservator.domain;

import org.joda.time.format.PeriodFormatter;


/**
 * @author  Max Dobler - dobler@synyx.de
 */

@FunctionalInterface
public interface FormatStrategy {

    PeriodFormatter getFormat();
}
