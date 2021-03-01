package com.example.pizzabeer.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber

/**
 * Responsible for providing any utilities like shared preference and initializing timber.
 */
@InstallIn(SingletonComponent::class)
@Module
object UtilityModule {

    private const val LOG_PREFIX = "pizzaBeer/"

    init {
        initTimber()
    }

    private fun initTimber() {
        Timber.uprootAll()
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, LOG_PREFIX + tag, message, t)
            }

            override fun createStackElementTag(element: StackTraceElement): String {
                return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
            }
        })
    }
}