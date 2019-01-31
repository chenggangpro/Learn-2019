package pro.chenggang.learn.reactor.chapter_b;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * 一个Publisher必须产生以下4中类型的事件：
 * 1. 订购事件
 * 2. 数据类型T
 * 3. 完成事件
 * 4. 错误事件
 * 一个Publisher散发许多Data Events,然而，他必须只发布一个完成/错误/订购事件
 * 一旦一个完成/错误事件发布,那么Publisher不能再Subscriber再发送Data Events
 *
 * 既然背压是规范中的重要一部分,一个Publisher不能随意的向Subscriber发送Events,
 * 取而代之的，一个Subscriber必须指定他可以接收多少Events,并且一个Publisher发布的Events必须小于或等于Subscriber指定的值
 */
public class FibonacciPublisher implements Publisher<Integer> {

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        int count = 0, a = 0, b = 1;
        while (count < 50) {
            int sum = a + b;
            subscriber.onNext(b);
            a = b;
            b = sum;
            count++;
        }

        subscriber.onComplete();
    }

}
