package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

    @FXML
    private Button getWeather;

    @FXML
    private Text sample_info;

    @FXML
    private Text temperature;

    @FXML
    private Text looks_like;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text power;

    @FXML
    private TextField city;

    @FXML
    void initialize(){
        getWeather.setOnAction(actionEvent -> {
            String getUserCity = city.getText().trim();
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=4c843068c30eb5b3528a82deb33daff6&units=metric");
            if (!output.isEmpty()){
                JSONObject object = new JSONObject(output);
                temperature.setText("ТЕМПЕРАТУРА: " + object.getJSONObject("main").getDouble("temp"));
                looks_like.setText("ОЩУЩАЕТСЯ: " + object.getJSONObject("main").getDouble("feels_like"));
                temp_max.setText("МАКСИМУМ: " + object.getJSONObject("main").getDouble("temp_max"));
                temp_min.setText("МИНИМУМ: " + object.getJSONObject("main").getDouble("temp_min"));
                power.setText("ДАВЛЕНИЕ: " + object.getJSONObject("main").getDouble("pressure"));
            }
        });
    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("City is not found");
        }
        return content.toString();
    }
}

