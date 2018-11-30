package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.screen.RoomDto;
import de.synyx.android.reservator.util.SchedularFacadeImpl;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.disposables.CompositeDisposable;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class RoomStatusViewModel extends ViewModel {

    private MutableLiveData<RoomDto> room;

    private LoadRoomUseCase loadRoomUseCase;
    private SchedulerFacade schedulerFacade;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RoomStatusViewModel() {

        loadRoomUseCase = new LoadRoomUseCase();
        schedulerFacade = Registry.get(SchedularFacadeImpl.class);
    }

    public LiveData<RoomDto> getRoom(long id) {

        if (room == null) {
            room = new MutableLiveData<>();
            loadRooms(id);
        }

        return room;
    }


    private void loadRooms(long id) {

        // TODO load room once per minute (like in LobbyViewModel)
        compositeDisposable.add(loadRoomUseCase.execute(id)
            .observeOn(schedulerFacade.io())
            .subscribeOn(schedulerFacade.mainThread())
            .subscribe(room::postValue));
    }
}
