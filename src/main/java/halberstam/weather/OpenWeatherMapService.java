package halberstam.weather;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface OpenWeatherMapService {
    @GET("/data/2.5/weather?q=Passaic&appid=90f7feeea989c2bfa008fde0a22016ba&units=imperial")
    Observable<CurrentWeather> getCurrentWeather();
}

