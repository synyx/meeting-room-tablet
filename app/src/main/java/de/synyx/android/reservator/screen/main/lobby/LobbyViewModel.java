package de.synyx.android.reservator.screen.main.lobby;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.screen.RoomDto;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.Observable;

import io.reactivex.disposables.Disposable;

import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LobbyViewModel extends ViewModel {

    private MutableLiveData<List<RoomDto>> rooms;

    private LoadVisibleRoomsUseCase loadVisibleRoomsUseCase;
    private SchedulerFacade schedulerFacade;

    private Disposable disposable;

    public LobbyViewModel() {

        loadVisibleRoomsUseCase = new LoadVisibleRoomsUseCase();
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

        disposable = Observable.interval(0, 1, MINUTES)
                .flatMap(ignored -> loadVisibleRoomsUseCase.execute().toObservable())
                .observeOn(schedulerFacade.io())
                .subscribeOn(schedulerFacade.mainThread())
                .subscribe(rooms::postValue);
    }


    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.dispose();
    }
}
