package DagemovBackend.DaemovBackendEcommer.domain.exception;


public class DuplicateRecord extends RuntimeException{
    public DuplicateRecord(String message) {
        super(message);
    }
}
