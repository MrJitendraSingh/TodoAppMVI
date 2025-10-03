package com.mj.appmvi.core

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TodoRemoteRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TodoDefaultRepo