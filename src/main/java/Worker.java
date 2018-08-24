/**
 * Created by wangke18 on 2018/8/24.
 */
public interface Worker<T> {
    T handle(Object[] params);
}
