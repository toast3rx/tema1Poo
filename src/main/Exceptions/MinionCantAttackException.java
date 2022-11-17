package main.Exceptions;

public class MinionCantAttackException extends Exception {
    public MinionCantAttackException(final String message) {
        super(message);
    }
}
