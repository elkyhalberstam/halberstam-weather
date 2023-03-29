package halberstam.weather;

public class Main {
        private double temp;
        private double feelsLike;
        private double tempMin;
        private double tempMax;
        private double pressure;
        private double humidity;

        public double getTemp(){
            return temp;
        }
        public void setTemp(int input){
            this.temp = input;
        }
        public double getFeelsLike(){
            return feelsLike;
        }
        public void setFeelsLike(int input){
            this.feelsLike = input;
        }
        public double getTempMin(){
            return tempMin;
        }
        public void setTempMin(int input){
            this.tempMin = input;
        }
        public double getTempMax(){
            return tempMax;
        }
        public void setTempMax(int input){
            this.tempMax = input;
        }
        public double getPressure(){
            return pressure;
        }
        public void setPressure(int input){
            this.pressure = input;
        }
        public double getHumidity(){
            return humidity;
        }
        public void setHumidity(int input){
            this.humidity = input;
        }
    }
