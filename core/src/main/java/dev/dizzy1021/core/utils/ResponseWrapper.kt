package dev.dizzy1021.core.utils

data class ResponseWrapper<out R>(val state: State, val data: R?, val message: String?) {

    companion object {
        fun <R> success(data: R?): ResponseWrapper<R> {
            return ResponseWrapper(State.SUCCESS, data, null)
        }

        fun <R> failure(msg: String, data: R?): ResponseWrapper<R> {
            return ResponseWrapper(State.FAILURE, data, msg)
        }

        fun <R> pending(data: R?): ResponseWrapper<R> {
            return ResponseWrapper(State.PENDING, data, null)
        }

    }

}