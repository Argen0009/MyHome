package com.geeks.smarthome.data.local_db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mbk.io.myhome.data.model.camera.CameraEntity

@Dao
interface CameraDao {

    @Query("SELECT*FROM camera")
    suspend fun getAll(): List<CameraEntity>

    @Insert
    suspend fun insertCamera(camera: CameraEntity)

    @Query("DELETE FROM camera")
    suspend fun clearAll()

    @Delete
    suspend fun delete(cameraEntity: CameraEntity)
}