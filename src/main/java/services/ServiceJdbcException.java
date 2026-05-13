package services;

public class ServiceJdbcException extends RuntimeException {
    public ServiceJdbcException(String mensaje) {
        super(mensaje);
    }

    public ServiceJdbcException(String mensaje, Throwable throwable) {
        super(mensaje, throwable);
    }
}
