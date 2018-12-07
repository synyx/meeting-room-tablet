package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.disposables.Disposable;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class MeetingRoomViewModel extends ViewModel {

    private MutableLiveData<MeetingRoom> room;

    private LoadRoomUseCase loadRoomUseCase;
    private SchedulerFacade schedulerFacade;

    private long calendarId;
    private Disposable disposable;

    public MeetingRoomViewModel() {

        loadRoomUseCase = new LoadRoomUseCase();
        schedulerFacade = Registry.get(SchedulerFacade.class);
    }

    public LiveData<MeetingRoom> getRoom() {

        loadRoom();

        return room;
    }


    public void setCalendarId(Long calendarId) {

        this.calendarId = calendarId;
    }


    private void loadRoom() {

        if (room == null) {
            room = new MutableLiveData<>();
        }

        disposable = loadRoomUseCase.execute(calendarId)
                .observeOn(schedulerFacade.io())
                .subscribeOn(schedulerFacade.mainThread())
                .subscribe(room::postValue);
    }


    public void tick() {

        disposable.dispose();
        loadRoom();
    }


    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.dispose();
    }
}
