package com.djc.kotlin.girl.utils

import io.reactivex.Flowable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.subscribers.SerializedSubscriber

/**
 * @author dong
 * @date 2017/12/7 11:14
 * @description
 */

object RxBus {
    //相当于Rxjava1.x中的Subject
    private val mBus: FlowableProcessor<Any> = PublishProcessor.create<Any>().toSerialized()


    /**
     * 发送消息
     * @param o
     */
    fun post(o: Any) {
        SerializedSubscriber(mBus).onNext(o)
    }

    /**
     * 确定接收消息的类型
     * @param aClass
     * @param <T>
     * @return
    </T> */
    fun <T> toFlowable(aClass: Class<T>): Flowable<T> {
        return mBus.ofType(aClass)
    }

    /**
     * 判断是否有订阅者
     * @return
     */
    fun hasSubscribers(): Boolean {
        return mBus.hasSubscribers()
    }


}
