package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.screen.RoomDto;
import de.synyx.android.reservator.util.SchedularFacadeImpl;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.disposables.CompositeDisposable;

import java.util.List;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class RoomStatusViewModel extends ViewModel {

    private MutableLiveData<List<RoomDto>> rooms;

    private LoadRoomUseCase loadRoomUseCase;
    private SchedulerFacade schedulerFacade;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RoomStatusViewModel() {

        // TODO move to registry
        loadRoomUseCase = new LoadRoomUseCase();
        schedulerFacade = new SchedularFacadeImpl();
    }

    public LiveData<List<RoomDto>> getRooms() {

        if (rooms == null) {
            rooms = new MutableLiveData<>();
            loadRooms();
        }

        return rooms;
    }


    private void loadRooms() {

        // TODO load single room by roomId, not list
        compositeDisposable.add(loadRoomUseCase.execute()
            .observeOn(schedulerFacade.io())
            .subscribeOn(schedulerFacade.mainThread())
            .subscribe(rooms::postValue));
    }
}
