package DagemovBackend.DaemovBackendEcommer.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

    public static final String DUPLICATE_RECORD= "is alredy in database a record with this :  ";
    public static final String NO_RECORD_FOUND ="We can't found it :(";
    public static final String REQUIRED_INPUT ="This input is required";
    public static final String MAX_LENGTH_STRING = "character overflow \nThis file only can have max";
    public static final String DUPLICATE_RECORD_NAME ="There is a same record with same name in this context";
    public static final String ERROR_DELETE ="We can't delete it in this context  : ID ==>";
    public static final String ERROR_UPDATE ="We can't update it in this context  : ID ==>";
    public static final String INVALID_INPUT=" This field don't have the right format";


    //GOOD ANSWERS

    public static final String SUCCESS_DELETE ="Record deleted successfully";
    public static final String SUCCESS_UPDATE ="Record updated successfully";
    public static final String SUCCESS_CREATE ="Record created successfully";


}
