package be.yonicon.template.view;

public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String resourceType, String valueType, String value) {
        super(String.format("resource type %s has a conflicting %s having value %s.", resourceType, valueType, value));
    }
}
