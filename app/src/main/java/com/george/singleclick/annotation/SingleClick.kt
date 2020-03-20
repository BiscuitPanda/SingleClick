package com.george.singleclick.annotation

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class SingleClick( /* 点击间隔时间 */
    val value: Long = 1000
)