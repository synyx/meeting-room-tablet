package com.synyx.android.reservator.util.proxy;

import android.content.Context;

import android.support.annotation.NonNull;

import com.synyx.android.reservator.config.Registry;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class ProxyConfig {

    private ProxyConfig() {

        // hide
    }

    public static void init(@NonNull Context context) {

        Registry.put(PermissionManager.class, new PermissionManager(context));
    }
}
