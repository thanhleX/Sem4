package test.project4v2.handler;


public interface CommandHandler<C, R> {
    R handle(C command);
}