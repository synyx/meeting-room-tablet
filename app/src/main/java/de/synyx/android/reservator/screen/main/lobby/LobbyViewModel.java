package de.synyx.android.reservator.screen.main.lobby;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.screen.RoomDto;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.disposables.CompositeDisposable;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LobbyViewModel extends ViewModel {

    private MutableLiveData<List<RoomDto>> rooms;

    private LoadRoomsUseCase loadRoomsUseCase;
    private SchedulerFacade schedulerFacade;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LobbyViewModel() {

        loadRoomsUseCase = Registry.get(LoadRoomsUseCase.class);
        schedulerFacade = Registry.get(SchedulerFacade.class);
    }

    public LiveData<List<RoomDto>> getRooms() {

        if (rooms == null) {
            rooms = new MutableLiveData<>();
            loadRooms();
        }

        return rooms;
    }


    private void loadRooms() {

        compositeDisposable.add(loadRoomsUseCase.execute()
            .observeOn(schedulerFacade.io())
            .subscribeOn(schedulerFacade.mainThread())
            .subscribe(rooms::postValue));
    }
}
