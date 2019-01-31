package pro.chenggang.learn.reactor.chapter_b.verify;

import org.reactivestreams.Subscriber;
import org.reactivestreams.tck.SubscriberBlackboxVerification;
import org.reactivestreams.tck.TestEnvironment;
import pro.chenggang.learn.reactor.chapter_b.FibonacciSubscriber;

public class FibonacciSubsciberVerification extends SubscriberBlackboxVerification<Long> {

    public FibonacciSubsciberVerification(){
        super(new TestEnvironment());
    }

    @Override
    public Subscriber<Long> createSubscriber() {
        return new FibonacciSubscriber();
    }

    @Override
    public Long createElement(int element) {
        return (long) element;
    }
}
