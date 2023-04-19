package halberstam.weather;

import halberstam.weather.currentweather.CurrentWeather;
import halberstam.weather.fivedayforcast.FiveDayForcast;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherServiceTest {

    @Test
    void getCurrentWeather() {
        //given

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        //when
        CurrentWeather weather = service.getCurrentWeather("Passaic").blockingFirst();

        //then
        assertNotNull(weather);
        assertNotNull(weather.weather.get(0).description);
        assertTrue(weather.main.temp > 0);
    }

    @Test
    void getFiveDayForcast() {
        //given

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        //when
        FiveDayForcast fiveDayForcast = service.getFiveDayForcast().blockingFirst();

        //then
        assertNotNull(fiveDayForcast);
        assertNotNull(fiveDayForcast.city.name);
        assertNotNull(fiveDayForcast.list.get(0).clouds);
    }
}