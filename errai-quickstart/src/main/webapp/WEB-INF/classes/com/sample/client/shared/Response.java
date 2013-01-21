package com.sample.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.enterprise.client.cdi.api.Conversational;

/**
 * A marshallable bean that carries the response event from the server back to
 * the client.
 * <p>
 * This is a {@code @Conversational} bean, so if it is fired as a CDI event from
 * within an observer method, it will only be delivered to the client who originated
 * the first event. Without the {@code @Conversational} annotation, this event would
 * be broadcast to every client every time it was fired. See the
 * <a href="https://docs.jboss.org/author/display/ERRAI/Events#Events-Conversationalevents">
 * conversational events</a> section of the Errai Reference Guide for details.
 */
@Portable
@Conversational
public class Response {
    private int id;
    private String message;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}