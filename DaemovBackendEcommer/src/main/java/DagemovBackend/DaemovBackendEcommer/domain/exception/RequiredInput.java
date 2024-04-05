package DagemovBackend.DaemovBackendEcommer.domain.exception;

public class RequiredInput extends RuntimeException{
    public RequiredInput(String message) {
        super(message);
    }
}