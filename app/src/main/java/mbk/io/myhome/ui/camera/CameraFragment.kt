package mbk.io.myhome.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import mbk.io.myhome.ui.adapter.CameraAdapter
import mbk.io.myhome.databinding.FragmentCameraBinding
import mbk.io.model.CameraModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var cameraAdapter: CameraAdapter
    private val viewModel by viewModel<CameraViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cameraList = listOf(
            CameraModel("Камера 1"),
            CameraModel("Камера 2"),
            CameraModel("Камера 3")
        )

        cameraAdapter = CameraAdapter(cameraList)
        binding.rvCamera.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCamera.adapter = cameraAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}