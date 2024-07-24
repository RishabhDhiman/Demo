package com.example.startwarblaster.viewmodel

data class State<out T>(val status: Status, val data: T? = null, val message: String? = null) {
    companion object {

        fun <T> success(data: T): State<T> {
            return State(status = Status.SUCCESS, data = data)
        }

        fun <T> empty(): State<T> {
            return State(status = Status.EMPTY)
        }

        fun <T> error(msg: String?): State<T> {
            return State(status= Status.ERROR, message =  msg)
        }

        fun <T> loading(): State<T> {
            return State(status = Status.LOADING)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    EMPTY
}