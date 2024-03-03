package mbk.io.myhome.utils


sealed class Resource<T>(
    val message: String? = null,
    val data: T? = null,
) {
    class Loading<T> : Resource<T>()
    class Error<T>(message: String) : Resource<T>(message = message)
    class Success<T>(data: Boolean) : Resource<T>(data = data)
}