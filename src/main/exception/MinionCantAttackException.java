package main.exception;

public class MinionCantAttackException extends Exception {
    public MinionCantAttackException(final String message) {
        super(message);
    }
}
