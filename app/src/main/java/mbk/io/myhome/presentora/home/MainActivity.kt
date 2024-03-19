package mbk.io.myhome.presentora.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import mbk.io.myhome.databinding.ActivityMainBinding
import mbk.io.myhome.presentora.adapter.MainAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewPager.adapter = MainAdapter(
            supportFragmentManager,
            lifecycle
        )

        TabLayoutMediator(binding.tabLiyayot, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Cameras"
                else -> "Doors"
            }
        }.attach()
    }
} 