package org.queiroz.themoviedb.api

import com.google.gson.JsonSyntaxException
import org.json.JSONException
import org.json.JSONObject
import org.queiroz.themoviedb.util.Resource
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


suspend inline fun <T> networkRequest(block: () -> T): Resource<T> {
    var response = Resource.error<T>("Something went wrong")
    try {
        response = Resource.success(block())
    } catch (e: IOException) {
        response = Resource.error("No network connectivity")
        Timber.d(e)
    } catch (e: HttpException) {
        Timber.e(getStatusMessage(e))
    } catch (e: JsonSyntaxException) {
        Timber.e(e)
    } catch (e: Exception) {
        Timber.e(e)
    }
    return response
}

fun getStatusMessage(e: HttpException): String {
    var message = ""
    try {
        e.response()?.errorBody()?.string()?.let {
            val jObjError = JSONObject(it)
            message = jObjError.getString("status_message")
        }
    } catch (e: JSONException) { }
    return message
}
