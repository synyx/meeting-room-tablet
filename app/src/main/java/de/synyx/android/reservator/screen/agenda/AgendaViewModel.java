package de.synyx.android.reservator.screen.agenda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.util.SchedulerFacade;

import io.reactivex.disposables.Disposable;


public class AgendaViewModel extends ViewModel {

    private MutableLiveData<AgendaDto> agenda;
    private SchedulerFacade schedulerFacade;
    private LoadAgendaUseCase loadAgendaUseCase;
    private Disposable disposable;

    public AgendaViewModel() {

        schedulerFacade = Registry.get(SchedulerFacade.class);
        loadAgendaUseCase = new LoadAgendaUseCase();
    }

    public LiveData<AgendaDto> getAgenda(long calendarId) {

        if (agenda == null) {
            agenda = new MutableLiveData<>();
            loadAgenda(calendarId);
        }

        return agenda;
    }


    private void loadAgenda(long calendarId) {

        disposable =
            loadAgendaUseCase.execute(calendarId) //
            .observeOn(schedulerFacade.io()) //
            .subscribeOn(schedulerFacade.mainThread()) //
            .subscribe(agenda::postValue);
    }


    @Override
    protected void onCleared() {

        super.onCleared();
        disposable.dispose();
    }
}
