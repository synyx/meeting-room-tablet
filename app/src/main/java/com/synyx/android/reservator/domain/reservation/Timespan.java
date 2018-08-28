package com.synyx.android.reservator.domain.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
@Builder
public class Timespan {

    @NonNull
    @Getter
    @Setter
    private Date start;

    @NonNull
    @Getter
    @Setter
    private Date end;

    private String name;

    @Override
    public String toString() {

        return String.format("[%s - %s]", start.getTime(), end.getTime());
    }
}
