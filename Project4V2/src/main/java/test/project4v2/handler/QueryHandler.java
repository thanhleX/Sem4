package test.project4v2.handler;

public interface QueryHandler<Q, R> {
    R getHandle(Q query);
}