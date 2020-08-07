package com.example.icloud_work.Util;

import java.util.HashMap;
import java.util.Objects;

public class TokenUtil {

    static final int DEFAULT_MAX_COUNT = 100;
    // 默认增长速率为1
    static final int DEFAULT_CREATE_RATE = 1;
    // 使用HashMap存放令牌桶，这里默认为10个令牌桶
    public static HashMap<String, TokenUtil> buckets = new HashMap(10);

    //自定义容量，一旦创建不可改变
    final int maxCount;
    //自定义增长速率1s几个令牌
    int createRate;
    //当前令牌数
    int size=0;



    // 默认令牌桶的容量及增长速率
    public TokenUtil() {
        maxCount = DEFAULT_MAX_COUNT;
        createRate = DEFAULT_CREATE_RATE;
    }
    // 自定义令牌桶容量及增长速率
    public TokenUtil(int maxCount, int createRate) {
        this.maxCount = maxCount;
        this.createRate = createRate;
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == maxCount;
    }

    public boolean isEmpty(){return size==0;}

    //根据速率自增生成一个令牌
    public synchronized void incrTokens() {
        for (int i = 0; i < createRate; i++)
        {
            if (isFull())
                return;
            size++;
        }
    }

    // 取一个令牌
    public synchronized boolean getToken() {
        if (!isEmpty())
            size--;
        else
            return false;
        return true;
    }

//    @Override
//    public boolean equals(Object obj) {
////        if (obj == null)
////            return false;
////        BucketUtil bucket = (BucketUtil) obj;
////        if (bucket.size != size || bucket.createRate != createRate || bucket.maxCount != maxCount)
////            return false;
////        return true;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCount, size, createRate);
    }
}
