package de.synyx.android.reservator.util.functional;

import android.arch.core.util.Function;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class FunctionUtils {

    private FunctionUtils() {

        // hide
    }

    public static <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {

        List<R> result = new ArrayList<>(list.size());

        for (T item : list) {
            result.add(mapper.apply(item));
        }

        return result;
    }
}
