package tn.esprit.autoloc.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String id) {
        super(resourceName + " introuvable avec l'identifiant : " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
