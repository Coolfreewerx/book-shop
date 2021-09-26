package ku.cs.shop.services;

public interface ConditionFilterer<T> {
    boolean match(T t);
}
