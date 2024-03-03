package mbk.io.myhome.ui.door

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mbk.io.myhome.databinding.FragmentDoorBinding
import mbk.io.model.DoorModel
import mbk.io.myhome.ui.adapter.DoorAdapter

class DoorFragment : Fragment() {

    private lateinit var binding: FragmentDoorBinding

    var doorList = arrayListOf<DoorModel>(
        DoorModel(
            "Door 1",
            ""
        ),
        DoorModel(
            "Door 1",
            ""
        ),
        DoorModel(
            "Door 1",
            ""
        ),
    )

    private val adapter = DoorAdapter(doorList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoor.adapter = adapter

    }
}