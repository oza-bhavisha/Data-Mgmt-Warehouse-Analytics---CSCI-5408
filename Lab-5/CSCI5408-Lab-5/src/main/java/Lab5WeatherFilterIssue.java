import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Lab5WeatherFilterIssue {

    public static void main(String[] args) {
        try {
            // Specify the path to the inout and output JSON file relative to the project directory
            String inputFilePath = Paths.get("src/main/data/weather.json").toString();
            String outputFilePath = Paths.get("src/main/data/summer_weather.json").toString();

            String jsonString = readJsonFile(inputFilePath);

            JSONTokener tokener = new JSONTokener(jsonString);
            JSONObject jsonObject = new JSONObject(tokener);


            JSONArray dailyArray = jsonObject.getJSONArray("daily");

            JSONObject filteredData = new JSONObject();

            // Filter the data for the next 5 days
            for (int i = 1; i <= 5; i++) {
                JSONObject dailyData = dailyArray.getJSONObject(i);

                // Get the timestamp for the start of the day
                long dayStartTime = dailyData.getLong("dt");
                LocalDate dayDate = LocalDateTime.ofEpochSecond(dayStartTime, 0, ZoneOffset.UTC).toLocalDate();
                // Check if it's during the day and feels_like temperature is greater than 15°C
                if (dailyData.getJSONObject("feels_like").getDouble("day") > 15) {
                    // Exclude the current, minutely, and hourly fields
                    dailyData.remove("moonset");
                    dailyData.remove("sunrise");
                    dailyData.remove("moonrise");
                    dailyData.remove("current");
                    dailyData.remove("minutely");
                    dailyData.remove("hourly");
                    dailyData.remove("sunset");
                    dailyData.remove("morn");
                    dailyData.remove("eve");
                    dailyData.remove("night");
                    dailyData.remove("pressure");
                    dailyData.remove("humidity");
                    dailyData.remove("dew_point");
                    dailyData.remove("wind_speed");
                    dailyData.remove("wind_deg");
                    dailyData.remove("wind_gust");
                    dailyData.remove("weather");
                    dailyData.remove("clouds");
                    dailyData.remove("pop");
                    dailyData.remove("rain");
                    dailyData.remove("uvi");
                    // Add the filtered data to the output JSON object
                    filteredData.put(String.valueOf(i), dailyData);
                }
            }

            // Write the filtered data to the output JSON file
            writeJsonFile(filteredData.toString(), outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readJsonFile(String filePath) throws Exception {
        FileReader reader = new FileReader(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            stringBuilder.append((char) character);
        }
        reader.close();
        return stringBuilder.toString();
    }

    private static void writeJsonFile(String json, String filePath) throws Exception {
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }
}