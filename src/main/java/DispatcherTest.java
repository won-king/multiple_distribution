/**
 * Created by wangke18 on 2018/8/24.
 */
public class DispatcherTest {

    public static void main(String[] args) {
        Dispatcher dispatcher=Dispatcher.build();
        dispatcher.register("1", new CheckLengthWorker())
                .register("2", new PlusWorker());
        Result<Boolean> result=Result.nonValue();
        Result<Long> result1=Result.nonValue();
        dispatcher.dispatch("1", new Object[]{1, "a,b,c"}, result)
                .dispatch("2", new Object[]{"1","2","3"}, result1);
        System.out.println("checkLength->"+result.t);
        System.out.println("plusWorker->"+result1.t);
        System.out.println("checkLength->"+dispatcher.dispatch("1", new Object[]{1, "a"}, BoolWrapper.INSTANCE));
        System.out.println("plusWorker->"+dispatcher.dispatch("2", new Object[]{"1","3","5"}, LongWrapper.INSTANCE));
    }
}
