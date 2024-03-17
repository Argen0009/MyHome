package mbk.io.myhome.presentora.camera

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mbk.io.myhome.data.model.camera.CameraEntity
import mbk.io.myhome.databinding.FragmentCameraBinding
import mbk.io.myhome.domain.api.App
import mbk.io.myhome.domain.base.BaseFragment
import mbk.io.myhome.presentora.adapter.CameraAdapter


@AndroidEntryPoint
class CameraFragment : BaseFragment() {
    private lateinit var binding: FragmentCameraBinding
    private val viewModel: CameraViewModel by viewModels()
    private val adapter = CameraAdapter(false)
    private var list: List<CameraEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT)
        binding.rvCamera.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.cameraDao().getAll()
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
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val deletedCamera = adapter.currentList[position]

        lifecycleScope.launch {
            viewModel.deleteCamera(deletedCamera)
            val updatedList = adapter.currentList.toMutableList().apply {
                removeAt(position)
            }
            adapter.submitList(updatedList)
            Log.e("ololo", "onSwiped: $updatedList")
        }
    }
}

    fun getData() {
        viewModel.getCameras().stateHandler(
            success = { it ->
                val list = it.data.cameras
                Log.e("ololo", "List of cameraModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.cameraDao().clearAll()
                    list.forEach {
                        val camera = CameraEntity(
                            favorites = it.favorites,
                            name = it.name,
                            rec = it.rec,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "camera : ${camera.toString()}")
                        App.db.cameraDao().insertCamera(camera)
                    }
                    withContext(Dispatchers.Main) {
                        val listDB = App.db.cameraDao().getAll()
                        Log.e("ololo", "List of cameraEntiies: ${listDB.toString()}")
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
    val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
    itemTouchHelper.attachToRecyclerView(binding.rvCameras)

    binding.rvCameras.adapter = adapter

    CoroutineScope(Dispatchers.IO).launch {
        list = viewModel.getDBCameras()
        withContext(Dispatchers.Main) {
            if (list.isEmpty()) {
                getData()
            } else {
                adapter.submitList(list)
                adapter.notifyDataSetChanged()
            }
        }
    }


    binding.swiperefresh.setOnRefreshListener {
        getData()
    }

}