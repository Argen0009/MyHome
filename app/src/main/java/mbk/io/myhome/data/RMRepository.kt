package mbk.io.myhome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import mbk.io.myhome.data.api.ApiService
import mbk.io.myhome.utils.Resource
import kotlinx.coroutines.Dispatchers
import mbk.io.myhome.data.model.Camera
import mbk.io.myhome.data.model.Door
import java.io.IOException

class RMRepository(private val api: ApiService) {

    fun getCamera(): LiveData<Resource<List<Camera>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getCamera()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.isSuccessful && response.body() != null && response.code() in 200..300))
                } else {
                    emit(Resource.Error("Failed to fetch cameras: ${response.message()}"))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Failed to fetch cameras: ${e.message}"))
            }
        }
    }

    fun getDoors(): LiveData<Resource<List<Door>>> = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            val response = api.getDoors()
            if (response.isSuccessful){
                emit(Resource.Success(response.isSuccessful && response.body() != null && response.code() in 200..300))
            }else{
                emit(Resource.Error("Failed to fetch playlists: ${response.message()}"))
            }
        }catch (e : IOException){
            emit(Resource.Error("Failed to fetch playlists: ${e.message}"))
        }
    }


}