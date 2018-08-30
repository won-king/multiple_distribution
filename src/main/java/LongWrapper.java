/**
 * Created by wangke18 on 2018/8/30.
 */
public class LongWrapper implements ValueWrapper<Long> {
    public static final LongWrapper INSTANCE=new LongWrapper();

    @Override
    public Long get(Object o) {
        return (Long) o;
    }
}
