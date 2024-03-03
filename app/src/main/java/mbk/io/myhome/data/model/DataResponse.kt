package mbk.io.myhome.data.model

import java.io.Serializable

data class CameraResponse(
    val `data`: Data,
    val success: Boolean
):Serializable

data class Data(
    val cameras: List<Camera>,
    val room: List<String>
):Serializable

data class Camera(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val rec: Boolean,
    val room: String,
    val snapshot: String
):Serializable

data class DoorsResponse(
    val `data`: List<Door>,
    val success: Boolean
):Serializable

data class Door(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String,
    val snapshot: String
):Serializable