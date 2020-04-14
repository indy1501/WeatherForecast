package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<WeatherFragmentBinding>(inflater, R.layout.weather_fragment,container,false)

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        viewModel._response.observe(viewLifecycleOwner, Observer { newResponse ->
            binding.location.text = newResponse.toString() //display the raw json data
        })

        viewModel._temp.observe(viewLifecycleOwner, Observer { newTemp ->
            binding.temperature.text = newTemp.toString()
        })

        viewModel._description.observe(viewLifecycleOwner, Observer { newCondition ->
            binding.condition.text = newCondition.toString()

        if(binding.condition.text == "broken clouds"){

            binding.display.setImageResource(R.drawable.cloudy)

        }else if(binding.condition.text == "sunny") {

            binding.display.setImageResource(R.drawable.sunny)

        }else if(binding.condition.text == "clear sky"){

            binding.display.setImageResource(R.drawable.sunny)

        }else if(binding.condition.text == "overcast clouds"){

            binding.display.setImageResource(R.drawable.cloudy1)

        }else if(binding.condition.text == "haze"){

            binding.display.setImageResource(R.drawable.hazy)

        }else if(binding.condition.text == "thunderstorm with rain"){

            binding.display.setImageResource(R.drawable.thunderstorm)

        }else{

            binding.display.setImageResource(R.drawable.sprinkle)
        }

        })

        viewModel._humidity.observe(viewLifecycleOwner, Observer { newHumidity ->
            binding.humidity.text = newHumidity.toString()
        })

        viewModel._feeling.observe(viewLifecycleOwner, Observer { newFeelsLike ->
            binding.feelTemp.text = newFeelsLike.toString()

        })

        binding.refresh.setOnClickListener { view ->

            Snackbar.make(view, "searching weather at current location", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

        return binding.root//inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

    }

}
