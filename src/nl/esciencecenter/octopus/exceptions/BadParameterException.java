package nl.esciencecenter.octopus.exceptions;

import java.net.URI;

public class BadParameterException extends OctopusException {

    private static final long serialVersionUID = 1L;

    public BadParameterException(String s, String adaptorName, URI uri) {
        super(s, adaptorName, uri);
    }

    public BadParameterException(String message, Throwable t, String adaptorName, URI uri) {
        super(message, t, adaptorName, uri);
    }

}