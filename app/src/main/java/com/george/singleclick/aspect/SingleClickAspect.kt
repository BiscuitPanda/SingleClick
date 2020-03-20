package com.george.singleclick.aspect

import android.view.View
import com.george.singleclick.annotation.SingleClick
import com.george.singleclick.utils.ClickUtil.isFastDoubleClick
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class SingleClickAspect {
    /**
     * 定义切点，标记切点为所有被@SingleClick注解的方法
     */
    @Pointcut("execution(@com.george.singleclick.annotation.SingleClick * *(..))")
    fun methodAnnotated() {
    }

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("methodAnnotated()")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint) {
        println("aroundJoinPoint")
        // 取出方法的参数
        var view: View? = null
        for (arg in joinPoint.args) {
            if (arg is View) {
                view = arg
                break
            }
        }
        if (view == null) {
            return
        }
        // 取出方法的注解
        val methodSignature =
            joinPoint.signature as MethodSignature
        val method = methodSignature.method
        if (!method.isAnnotationPresent(SingleClick::class.java)) {
            return
        }
        val singleClick = method.getAnnotation(SingleClick::class.java)
        // 判断是否快速点击
        if (!isFastDoubleClick(view, singleClick.value)) { // 不是快速点击，执行原方法
            joinPoint.proceed()
            println("不是快速点击")
        } else {
            println("是快速点击")
        }
    }
}