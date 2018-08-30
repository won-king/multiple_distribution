import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangke18 on 2018/8/24.
 * 多路分发器
 * 目标是，泛化方法的调用，将传统的方法调用转化为一个关键词调用
 * 这样做的好处是，屏蔽了传统方法调用严格约束的两个要素，即类和方法名，他们都变得透明化了
 * 亦即，我要调用一个服务，我不关心是哪个类的哪个方法提供这样一个服务逻辑，我只关心我传给你调用符，参数，和你返给我的返回值
 * 你需要把调用符分发到注册到这个调用符上的对象的某个方法上，把参数传给他，然后调用他，完了把结果返给我就行了
 * 用生产者和消费者来类比就是，worker是生产者，调用方是消费者，dispatcher是zookeeper
 * 那么作为生产者的工作有：
 * 1.确定业务范围，我要生产哪些对象，即worker的泛型参数，此泛型参数也是生产方法的返回值
 * 2.实现生产逻辑
 *
 * 调用方的义务有：
 * 1.提供调用符，表明调用哪个服务
 * 2.提供参数
 * 3.提供结果转换器，将生产者生产出来的对象转换成我想要的对象句柄
 *
 * 典型的应用场景有：如果一个switch有非常多的分支，并且case里面的逻辑比较复杂，那么就可以用这个多路分发器来实现
 */
public class Dispatcher {
    private Map<String,Worker> workerMap;
    private static Dispatcher instance;

    private Dispatcher(){
        workerMap=new HashMap<>(2);
    }

    public static Dispatcher build(){
        return instance=new Dispatcher();
    }

    public Dispatcher register(String key, Worker worker){
        workerMap.put(key, worker);
        return instance;
    }

    public Dispatcher dispatch(String key, Object[] params, Result result){
        Worker worker=workerMap.get(key);
        if(worker==null){
            return instance;
        }
        result.t=worker.handle(params);
        return instance;
    }

    public <T> T dispatch(String key, Object[] params, ValueWrapper<T> valueWrapper){
        Worker worker=workerMap.get(key);
        if(worker==null){
            return null;
        }
        return valueWrapper.get(worker.handle(params));
    }
}
