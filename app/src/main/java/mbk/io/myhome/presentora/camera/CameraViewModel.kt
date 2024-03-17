package mbk.io.myhome.presentora.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.camera.CameraModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mbk.io.myhome.data.RMRepository
import mbk.io.myhome.data.model.camera.CameraEntity
import mbk.io.myhome.domain.usecases.GetCameraUseCase
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val cameraUseCase: GetCameraUseCase) :
    ViewModel() {
    fun getCameras(): LiveData<Resource<CameraModel>> = cameraUseCase.getCameras()

    suspend fun getDBCameras(): List<CameraEntity> = cameraUseCase.getDBCameras()

    suspend fun clearAll() = cameraUseCase.clearAll()

    suspend fun insertCamera(cameraEntity: CameraEntity) = cameraUseCase.insert(cameraEntity)
}