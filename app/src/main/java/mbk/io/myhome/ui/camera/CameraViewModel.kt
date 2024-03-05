package mbk.io.myhome.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.myhome.data.RMRepository
import mbk.io.myhome.data.model.Camera
import mbk.io.myhome.data.model.Data
import mbk.io.myhome.utils.Resource

class CameraViewModel(private val repository: RMRepository): ViewModel() {

    fun getCameras(): LiveData<Resource<List<Camera>>> = repository.getCamera()
}