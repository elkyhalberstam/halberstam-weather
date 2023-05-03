package halberstam.weather;

import halberstam.weather.CurrentWeatherView;
import halberstam.weather.ForecastWeatherController;
import halberstam.weather.OpenWeatherMapService;
import halberstam.weather.fivedayforecast.FiveDayForecast;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForecastWeatherControllerTest {

    static {
        // This makes it so that our service returns immediately
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }
    @Test
    void updateWeather() {
        //given
        OpenWeatherMapService service = mock();
        CurrentWeatherView view = mock();
        ForecastWeatherController controller = new ForecastWeatherController(view, service);
        FiveDayForecast fiveDayForecast = mock();
        Observable<FiveDayForecast> observable = Observable.just(fiveDayForecast);
        doReturn(observable).when(service).getFiveDayForecast("New York");

        //when
        controller.updateWeather("New York");

        //then
        verify(service).getFiveDayForecast("New York");
        verify(view).setForecast(fiveDayForecast);
    }

}