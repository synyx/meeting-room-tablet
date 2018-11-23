package de.synyx.android.reservator.util;

import io.reactivex.Scheduler;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface SchedulerFacade {

    Scheduler io();


    Scheduler mainThread();
}
