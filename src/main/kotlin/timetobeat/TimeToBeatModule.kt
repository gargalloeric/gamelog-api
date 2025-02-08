package com.mocosoft.timetobeat

import org.koin.dsl.module

val timeToBeatModule = module {
    single<TimeToBeatService> { TimeToBeatServiceImpl(get())}
}