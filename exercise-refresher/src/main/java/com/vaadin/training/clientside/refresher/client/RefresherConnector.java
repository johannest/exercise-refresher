package com.vaadin.training.clientside.refresher.client;

import com.google.gwt.user.client.Timer;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.training.clientside.refresher.Refresher;

@Connect(Refresher.class)
public class RefresherConnector extends AbstractExtensionConnector {

    RefresherServerRpc rpc = RpcProxy.create(RefresherServerRpc.class, this);
    private final Poller poller;

    private class Poller extends Timer {
        @Override
        public void run() {
            rpc.refresh();
        }
    }

    public RefresherConnector() {
        poller = new Poller();
    }

    @Override
    public RefresherState getState() {
        return (RefresherState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        poller.cancel();
        if (getState().enabled) {
            if (getState().interval > 0) {
                poller.scheduleRepeating(getState().interval);
            }
        }
    }

    @Override
    protected void extend(ServerConnector target) {
        // Not extending anything visible
    }

    @Override
    public void onUnregister() {
        poller.cancel();
        super.onUnregister();
    }

}
