package com.mocosoft.covers

import org.koin.dsl.module

val coverModule = module {
    single<CoverService> { CoverServiceImpl(get()) }
}