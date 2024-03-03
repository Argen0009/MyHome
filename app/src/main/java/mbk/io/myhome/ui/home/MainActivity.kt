package mbk.io.myhome.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mbk.io.myhome.databinding.ActivityMainBinding
import mbk.io.myhome.ui.adapter.mainAdapter
import mbk.io.myhome.ui.door.DoorFragment
import mbk.io.myhome.ui.camera.CameraFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = mainAdapter(supportFragmentManager)
        adapter.addFragment(CameraFragment(),"Camera")
        adapter.addFragment(DoorFragment(),"Door")


        binding.viewPager.adapter = adapter
        binding.tabLiyayot.setupWithViewPager(binding.viewPager)
    }
}