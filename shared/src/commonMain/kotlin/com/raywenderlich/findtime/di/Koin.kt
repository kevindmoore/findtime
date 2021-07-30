package com.raywenderlich.findtime.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(timezoneModule())
    }

// called by iOS etc
fun initKoin() = initKoin() {}


