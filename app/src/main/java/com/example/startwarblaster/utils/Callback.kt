package com.example.startwarblaster.utils

interface Callback<T> {
   fun onCall(playLoad: T)
}