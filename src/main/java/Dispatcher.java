import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangke18 on 2018/8/24.
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
}
