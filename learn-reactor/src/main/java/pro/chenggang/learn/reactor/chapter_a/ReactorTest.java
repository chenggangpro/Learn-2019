package pro.chenggang.learn.reactor.chapter_a;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ReactorTest {
    @Test
    public void testSimpleStringFlux() {
        StringBuilder str = new StringBuilder();
        //生成一个Flux
        Flux<String> stingFlux = Flux.just("Quick", "brown", "fox", "jumped", "over", "the", "wall");
        //拼接字符串
        stingFlux.subscribe(t -> {
            str.append(t).append(" ");
        });
        assertEquals("Quick brown fox jumped over the wall ", str.toString());
    }

    @Test
    public void testFibonacci() {
        Flux<Long> fibonacciGenerator = Flux.generate(
                //Callable
                () -> Tuples.of(0L, 1L),
                //BiFunction
                //state-->Tuples
                (state, sink) -> {
                    //next 指向下一个数
                    sink.next(state.getT1());
                    //返回T2 和 T1+T2
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                });
        List<Long> fibonacciSeries = new LinkedList<>();
        int size = 50;
        fibonacciGenerator.take(size).subscribe(fibonacciSeries::add);
        System.out.println(fibonacciSeries);
        assertEquals( 7778742049L, fibonacciSeries.get(size-1).longValue());
    }

    @Test
    public void testFluxGenerate(){
        // 手动生成一个Flux信号
        Flux<String> flux = Flux.generate(
                // Callable --> 提供一个 Tuples
                () -> Tuples.of(0L, 1L),
                (state, sink) -> {
                    //指定sink的下一个的值，该值为最终生成的Flux对象的值
                    sink.next("String->("+state.getT1()+")");
                    //返回 下一组Tuples
                    return Tuples.of(state.getT1()+1, state.getT2() + 1);
                });
        // 一秒钟从Flux中拿一个对象,输出
        Disposable subscribe = flux.delayElements(Duration.ofSeconds(1)).subscribe(System.out::println);
        long start = System.currentTimeMillis();
        // 拿10秒中国
        while (true){
            if(System.currentTimeMillis()-start> TimeUnit.MILLISECONDS.convert(10,TimeUnit.SECONDS)){
                break;
            }
        }
        subscribe.dispose();
    }

}
