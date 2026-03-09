package com.killqwerty.killqwerty_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Killqwerty_KMP",
    ) {
        App()
    }
}