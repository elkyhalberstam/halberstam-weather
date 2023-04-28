package halberstam.weather;

import halberstam.weather.currentweather.CurrentWeather;
import halberstam.weather.fivedayforcast.FiveDayForcast;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    @GET("/data/2.5/weather?appid=90f7feeea989c2bfa008fde0a22016ba&units=imperial")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String location);

    @GET("/data/2.5/forecast?&appid=90f7feeea989c2bfa008fde0a22016ba&units=imperial")
    Observable<FiveDayForcast> getFiveDayForcast(@Query("q") String location);
}

