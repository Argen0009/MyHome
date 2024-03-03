package mbk.io.myhome.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import mbk.io.myhome.data.api.ApiService
import mbk.io.myhome.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class RMRepository(private val api: ApiService) {

    fun getCamera(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
          val response = api.getCamera()
            if (response.isSuccessful){
                emit(Resource.Success(response.body()?.success?: false))
            }else{
                emit(Resource.Error("Failed to fetch playlists: ${response.message()}"))
            }
        }catch (e : IOException){
            emit(Resource.Error("Failed to fetch playlists: ${e.message}"))
        }
    }

    fun getDoors(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            val response = api.getDoors()
            if (response.isSuccessful){
                emit(Resource.Success(response.body()?.success?: false))
            }else{
                emit(Resource.Error("Failed to fetch playlists: ${response.message()}"))
            }
        }catch (e : IOException){
            emit(Resource.Error("Failed to fetch playlists: ${e.message}"))
        }
    }


}