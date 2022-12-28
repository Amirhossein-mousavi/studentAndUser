package com.example.myapplication.util

import android.content.Context
import android.widget.Toast

fun Context.toast (title : String) {
    Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
 }