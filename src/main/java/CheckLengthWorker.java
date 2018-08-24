/**
 * Created by wangke18 on 2018/8/24.
 */
public class CheckLengthWorker implements Worker<Boolean> {

    @Override
    public Boolean handle(Object[] params) {
        int i= (int) params[0];
        String string= (String) params[1];
        String[] strings=string.split(",");
        return i==strings.length;
    }
}
