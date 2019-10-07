package pro.chenggang.learn.reactor.chapter_a;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class SampleSubscriber<T> extends BaseSubscriber<T> {

	@Override
	public void hookOnSubscribe(Subscription subscription) {
		System.out.println("Subscribed");
		request(1);
	}

	@Override
	public void hookOnNext(T value) {
		System.out.println("Inner:"+value);

		request(1);
	}

	@Override
	protected void hookOnError(Throwable throwable) {
    	System.out.println("HookOnError:"+throwable);
	}
}