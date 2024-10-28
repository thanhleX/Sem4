package test.project4v2.Mediator;
public interface Mediator {
    <TResponse> TResponse send(Command<TResponse> command);
    <TResponse> TResponse send(Query<TResponse> query);

    interface Command<TResponse> {
    }

    interface Query<TResponse> {
    }
}
