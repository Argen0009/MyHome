package mbk.io.myhome.domain.repositories

import androidx.lifecycle.LiveData
import com.geeks.smarthome.data.Resource
import com.geeks.smarthome.data.model.door.DoorModel
import mbk.io.myhome.data.model.door.DoorEntity

interface DoorsRepository {
    fun getDoors(): LiveData<Resource<DoorModel>>

    suspend fun  getDBDoors():List<DoorEntity>
    suspend fun clearAllDoors()

    suspend fun insert(doorEntity: DoorEntity)
    suspend fun deleteDoor(doorEntity: DoorEntity)
}