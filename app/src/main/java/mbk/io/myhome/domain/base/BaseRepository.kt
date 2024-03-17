package mbk.io.myhome.domain.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geeks.smarthome.data.Resource

import kotlinx.coroutines.Dispatchers
import mbk.io.myhome.api.ApiService

abstract class BaseRepository (private val api: ApiService){
    fun <T> apiRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.Main) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                if (response != null) {
                    emit(Resource.Success(response))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }

        }

}