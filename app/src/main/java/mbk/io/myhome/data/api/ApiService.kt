package mbk.io.myhome.data.api

import mbk.io.myhome.data.model.CameraResponse
import mbk.io.myhome.data.model.DoorsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras/")
    suspend fun getCamera(

    ): Response<CameraResponse>

    @GET("doors/")
    suspend fun getDoors(
    ): Response<DoorsResponse>
}