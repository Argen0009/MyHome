package mbk.io.myhome.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import mbk.io.myhome.databinding.FragmentCameraBinding
import mbk.io.myhome.ui.adapter.CameraAdapter
import mbk.io.myhome.utils.Resource

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var cameraAdapter: CameraAdapter

    private val viewModel: CameraViewModel by viewModels()

    private lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        cameraAdapter = CameraAdapter(emptyList())
        binding.rvCamera.adapter = cameraAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cameraAdapter = CameraAdapter(emptyList())
        binding.rvCamera.adapter = cameraAdapter
        binding.rvCamera.layoutManager = LinearLayoutManager(requireContext())
        progressBar = binding.progressIndicator

        viewModel.getCameras().observe(viewLifecycleOwner){
            resource ->
            when(resource){
                is Resource.Loading ->{
                    progressBar.visibility = View.VISIBLE
                }is Resource.Success ->{
                    progressBar.visibility = View.GONE
                resource.data.let { cameraList->
                    val cameraList = cameraResponse.data.camera
                    cameraAdapter.setData(cameraList)
                }
                }is Resource.Error ->{
                    progressBar.visibility = View.GONE

                Toast.makeText(requireContext(),"Ошибка: ${resource.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}