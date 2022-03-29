package com.pjap.domain.extensions

import com.pjap.domain.entities.CustomError
import com.pjap.domain.entities.ErrorThrowable
import com.pjap.domain.entities.NetworkError
import com.pjap.domain.entities.UnknownError
import java.net.UnknownHostException

fun CustomError.toThrowable(): ErrorThrowable {
    return ErrorThrowable(code, message, cause)
}

fun Throwable.toError(): CustomError {
    return when (this) {
        is ErrorThrowable -> this.toError()
        is UnknownHostException -> NetworkError(message = this.message, cause = this)
        else -> UnknownError(message = this.message, cause = this)
    }
}

/**
 * A convenient method for wrapping a [Throwable] in a[CustomResult].
 * Converts a [Throwable] to a [CustomResult.OnError].
 */
/*fun <T> Throwable.toResult(): CustomResult<T> {
    return when (this) {
        is ErrorThrowable -> CustomResult.OnError(this.toError())
        is UnknownHostException -> CustomResult.OnError(NetworkError(message = this.message, cause = this))
        else -> CustomResult.OnError(UnknownError(message = this.message, cause = this))
    }
}*/