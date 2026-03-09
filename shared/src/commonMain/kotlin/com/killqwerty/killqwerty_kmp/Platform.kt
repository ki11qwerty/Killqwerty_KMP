package com.killqwerty.killqwerty_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform