package mbk.io.myhome.presentora.door

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geeks.smarthome.presentor.ui.door_activity.adapter.DoorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.myhome.databinding.FragmentDoorBinding
import mbk.io.myhome.domain.api.App
import mbk.io.myhome.domain.base.BaseFragment

@AndroidEntryPoint
class DoorFragment : BaseFragment() {
    private lateinit var binding: FragmentDoorBinding
    private val viewModel: DoorViewModel by viewModels()
    private val adapter = DoorAdapter(true)
    private var list: List<DoorEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDoorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoor.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.doorDao().getAll()
            withContext(Dispatchers.Main) {
                if (list.isEmpty()) {
                    getData()
                } else {
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun getData() {
        viewModel.getCameras().stateHandler(
            success = { it ->
                val list = it.data
                Log.e("ololo", "List of doorModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.doorDao().clearAll()
                    list.forEach {
                        val door = DoorEntity(
                            favorites = it.favorites,
                            name = it.name,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "door: ${door.toString()}")
                        App.db.doorDao().insertDoor(door)

                    }
                    val listDB = App.db.doorDao().getAll()
                    Log.e("ololo", "List of doorEntiies: ${listDB.toString()}")
                    withContext(Dispatchers.Main) {
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}