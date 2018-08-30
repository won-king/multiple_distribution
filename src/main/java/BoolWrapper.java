/**
 * Created by wangke18 on 2018/8/30.
 */
public class BoolWrapper implements ValueWrapper<Boolean> {
    public static final BoolWrapper INSTANCE=new BoolWrapper();

    @Override
    public Boolean get(Object o) {
        return (Boolean) o;
    }
}
