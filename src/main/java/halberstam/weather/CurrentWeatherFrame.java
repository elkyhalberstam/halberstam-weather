package halberstam.weather;

import halberstam.weather.fivedayforecast.FiveDayForecast;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame {
    public CurrentWeatherFrame()
    {
        setSize(650, 600);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel enterCityPanel = new JPanel();
        enterCityPanel.setLayout(new FlowLayout());

        JTextField enterCityName  = new JTextField("Passaic             ");
        JButton enterCityButton = new JButton("Enter City");

        enterCityPanel.add(enterCityName);
        enterCityPanel.add(enterCityButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(enterCityPanel, BorderLayout.NORTH);

        setContentPane(panel);
        CurrentWeatherView currentWeatherView = new CurrentWeatherView();
        panel.add(currentWeatherView, BorderLayout.CENTER);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);
        FiveDayForecast intitalFiveDayForecast =
                service.getFiveDayForecast(enterCityName.getText()).blockingFirst();
        currentWeatherView.setForecast(intitalFiveDayForecast);

        enterCityButton.addActionListener(e -> {

            Disposable disposable = service.getFiveDayForecast(enterCityName.getText())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .subscribe(
                            fiveDayForecast -> {
                                currentWeatherView.setForecast(fiveDayForecast);
                            },
                            Throwable::printStackTrace
                    );

        });
    }
}
