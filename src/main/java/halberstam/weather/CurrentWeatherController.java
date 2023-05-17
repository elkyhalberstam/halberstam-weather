package halberstam.weather;

import halberstam.weather.currentweather.CurrentWeather;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherController {
    private OpenWeatherMapService service;
    private JLabel imageLabel;
    private JLabel degreesLabel;

    @Inject
    public CurrentWeatherController(OpenWeatherMapService service,
                                     @Named("imageLabel") JLabel imageLabel,
                                     @Named("degreesLabel") JLabel degreesLabel) {
        this.service = service;
        this.imageLabel = imageLabel;
        this.degreesLabel = degreesLabel;
    }

    public void updateWeather(String cityName)
    {
        service.getCurrentWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        this::setCurrentWeather,
                        Throwable::printStackTrace
                );
    }

    public void setCurrentWeather(CurrentWeather currentWeather) throws MalformedURLException {
        degreesLabel.setText(String.valueOf(currentWeather.main.temp));
        String icon = currentWeather.weather.get(0).icon;
        String url = "https://openweathermap.org/img/w/" + icon + ".png";
        imageLabel.setIcon(new ImageIcon(new URL(url)));
    }
}
