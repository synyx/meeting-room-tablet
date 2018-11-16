package com.synyx.android.reservator.config;

import com.synyx.android.reservator.data.EventAdapter;
import com.synyx.android.reservator.data.EventAdapterImpl;
import com.synyx.android.reservator.data.EventRepositoryImpl;
import com.synyx.android.reservator.data.RoomRepositoryImpl;
import com.synyx.android.reservator.domain.event.EventRepository;
import com.synyx.android.reservator.domain.room.RoomRepository;
import com.synyx.android.reservator.usecase.LoadRoomsUseCase;
import com.synyx.android.reservator.util.SchedularFacadeImpl;
import com.synyx.android.reservator.util.SchedulerFacade;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LobbyConfig {

    private LobbyConfig() {

        // hide
    }

    public static void init() {

        Registry.put(EventAdapter.class, new EventAdapterImpl());
        Registry.put(EventRepository.class, new EventRepositoryImpl());
        Registry.put(SchedulerFacade.class, new SchedularFacadeImpl());
        Registry.put(RoomRepository.class, new RoomRepositoryImpl());
        Registry.put(LoadRoomsUseCase.class, new LoadRoomsUseCase());
    }
}
