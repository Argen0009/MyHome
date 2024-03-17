package mbk.io.myhome.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.smarthome.data.local_db.dao.CameraDao
import mbk.io.myhome.data.local.dao.DoorDao
import mbk.io.myhome.data.model.camera.CameraEntity
import mbk.io.myhome.data.model.door.DoorEntity

@Database(entities = [CameraEntity::class, DoorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao
}