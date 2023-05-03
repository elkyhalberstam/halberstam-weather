package halberstam.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

    CurrentWeatherView currentWeatherView = new CurrentWeatherView();

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
        panel.add(currentWeatherView, BorderLayout.CENTER);


        generateFiveDayForecast(enterCityName.getText());


        enterCityButton.addActionListener(e -> {

            generateFiveDayForecast(enterCityName.getText());

        });
    }

    private void generateFiveDayForecast (String cityName)
    {
        Disposable disposable = service.getFiveDayForecast(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        fiveDayForecast -> {
                            currentWeatherView.setForecast(fiveDayForecast);
                        },
                        Throwable::printStackTrace
                );
    }
}
