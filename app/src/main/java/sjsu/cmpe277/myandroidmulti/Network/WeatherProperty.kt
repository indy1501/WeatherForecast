package sjsu.cmpe277.myandroidmulti.Network

import androidx.activity.OnBackPressedCallback
import com.squareup.moshi.Json

data class WeatherProperty(
    val id: String,
    // used to map main from the JSON to mainpart in our class
    @Json(name = "main")
    val mainpart: MainWeatherPart,
    val name: String,
    val cod: Double,

    // used to map main from the JSON Response to Weather in our class
    @Json(name = "weather")
    val weather: List<Weather>

)

data class MainWeatherPart(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)



