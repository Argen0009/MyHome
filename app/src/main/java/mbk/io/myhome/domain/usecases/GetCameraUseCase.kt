package mbk.io.myhome.domain.usecases

import androidx.lifecycle.LiveData
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.camera.CameraModel
import mbk.io.myhome.data.model.camera.CameraEntity
import mbk.io.myhome.domain.repositories.CamerasRepository
import javax.inject.Inject

class GetCameraUseCase @Inject constructor(private val camerasRepository: CamerasRepository) {
    fun getCameras(): LiveData<Resource<CameraModel>> = camerasRepository.getCameras()

    suspend fun getDBCameras(): List<CameraEntity> = camerasRepository.getDBCameras()

    suspend fun clearAll() = camerasRepository.clearAll()

    suspend fun deleteCamera(cameraEntity: CameraEntity) = camerasRepository.deleteCamera(cameraEntity)

    suspend fun insert(cameraEntity: CameraEntity) = camerasRepository.insert(cameraEntity)
}