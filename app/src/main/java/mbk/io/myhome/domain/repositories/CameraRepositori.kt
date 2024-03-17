package mbk.io.myhome.domain.repositories

import androidx.lifecycle.LiveData
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.camera.CameraModel
import mbk.io.myhome.data.model.camera.CameraEntity

interface CamerasRepository {
    fun getCameras(): LiveData<Resource<CameraModel>>
    suspend fun  getDBCameras():List<CameraEntity>
    suspend fun clearAll()
    suspend fun deleteCamera(cameraEntity: CameraEntity)

    suspend fun insert(cameraEntity: CameraEntity)
}