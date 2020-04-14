package sjsu.cmpe277.myandroidmulti.Network


import android.media.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.R.drawable.*
import java.nio.channels.MulticastChannel


private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"
private const val currentCity = "Rio Bravo"

class WeatherViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    val _response = MutableLiveData<String>()
    val _temp = MutableLiveData<String>()
    val _humidity = MutableLiveData<String>()
    val _description = MutableLiveData<String>()
    val _feeling = MutableLiveData<String>()
    val _display = MutableLiveData<Int>()
    val _icon = MutableLiveData<String>()



    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        getWeatherProperties()
    }

    private fun getWeatherProperties() {
        //_response.value = "Set the Weather API Response here!"
        //WeatherApi.retrofitService.getProperties()
        WeatherApi.retrofitService.getProperties(
            city = currentCity,
            units = "metric",
            apiKey = WeatherAPPID
        ).enqueue(
            object: Callback<WeatherProperty> {
                override fun onFailure(call: Call<WeatherProperty>, t: Throwable) {

                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<WeatherProperty>, response: Response<WeatherProperty>) {

                    //_response.value = response.body()
                    _response.value = "${response.body()?.name}"

                    _temp.value = "${response.body()?.mainpart?.temp} "

                    _humidity.value = "Humidity: ${response.body()?.mainpart?.humidity}"

                    _description.value = "${response.body()?.weather?.get(0)?.description}"

                    _icon.value = "${response.body()?.weather?.get(0)?.icon}"

                    _feeling.value = "Feels like: ${response.body()?.mainpart?.feels_like}"

                   _display.value = sunny

                }

            }
        )
    }

}

