package com.uryonet.remotepentax.model.event;

public class ErrorEvent {

    public final Throwable e;

    public ErrorEvent(Throwable e) {
        this.e = e;
    }

}
