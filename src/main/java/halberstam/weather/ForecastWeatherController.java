package halberstam.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

public class ForecastWeatherController {

    private CurrentWeatherView view;
    private OpenWeatherMapService service;

    @Inject

    public ForecastWeatherController(
        CurrentWeatherView view,
                OpenWeatherMapService service)
    {
        this.view = view;
        this.service = service;
    }

    public void updateWeather(String cityName)
    {
        service.getFiveDayForecast(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        view::setForecast,
                        Throwable::printStackTrace
                );
    }
}
