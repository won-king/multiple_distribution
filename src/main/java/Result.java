/**
 * Created by wangke18 on 2018/8/24.
 */
public class Result<T> {
    public T t;

    public static Result nonValue(){
        return new Result();
    }
}
