package mbk.io.myhome.presentora.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.manager.Lifecycle
import mbk.io.myhome.presentora.camera.CameraFragment
import mbk.io.myhome.presentora.door.DoorFragment

class mainAdapter ( private val fragmentManager: FragmentManager,
private val lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle)
{
    private val fragments = listOf(
        CameraFragment(), DoorFragment()
    )

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CameraFragment()
            1 -> DoorFragment()
            else -> CameraFragment()
        }
    }
}