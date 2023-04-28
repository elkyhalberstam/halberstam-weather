
package halberstam.weather.fivedayforcast;

import com.google.gson.annotations.SerializedName;

public class Main {

    private double feels_like;
    @SerializedName("grnd_level")

    private double grndLevel;
    private double humidity;
    private double pressure;
    private double sea_level;
    public double temp;
    @SerializedName("temp_kf")

    private double tempKf;
    @SerializedName("temp_max")

    private double tempMax;
    @SerializedName("temp_min")

    private double tempMin;

}