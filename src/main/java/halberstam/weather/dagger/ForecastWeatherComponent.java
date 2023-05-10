package halberstam.weather.dagger;

import dagger.Component;
import halberstam.weather.CurrentWeatherFrame;
import dagger.Component;


import javax.inject.Singleton;

@Singleton
@Component(modules = {OpenWeatherMapServiceModule.class})
public
interface ForecastWeatherComponent {

    CurrentWeatherFrame providesCurrentWeatherFrame();

}
