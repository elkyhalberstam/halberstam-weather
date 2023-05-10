package halberstam.weather;

import halberstam.weather.dagger.DaggerForecastWeatherComponent;
import halberstam.weather.dagger.ForecastWeatherComponent;

public class Main {

    public static void main(String[] args) {
        ForecastWeatherComponent component = DaggerForecastWeatherComponent
                .builder()
                .build();
        CurrentWeatherFrame frame = component.providesCurrentWeatherFrame();
        frame.setVisible(true);
    }

}
