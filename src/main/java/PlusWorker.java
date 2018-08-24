/**
 * Created by wangke18 on 2018/8/24.
 */
public class PlusWorker implements Worker<Long> {

    @Override
    public Long handle(Object[] params) {
        long result=0;
        for(Object o:params){
            result+=Long.parseLong((String) o);
        }
        return result;
    }
}
