package be.yonicon.template.view;

public class ServiceException extends RuntimeException {

    public ServiceException(String operation, String resourceType, String id) {
        super(String.format("resource type %s with id %s returned in a incorrect state: %s", resourceType, id, operation));
    }
}
