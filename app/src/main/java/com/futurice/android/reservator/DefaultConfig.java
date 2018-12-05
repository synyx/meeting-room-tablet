package com.futurice.android.reservator;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.util.DefaultTimeProvider;
import de.synyx.android.reservator.util.TimeProvider;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class DefaultConfig {

    public static void init() {

        Registry.put(TimeProvider.class, new DefaultTimeProvider());
    }
}
