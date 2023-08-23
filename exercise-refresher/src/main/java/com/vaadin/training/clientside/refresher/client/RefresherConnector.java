package com.vaadin.training.clientside.refresher.client;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.training.clientside.refresher.Refresher;

@Connect(Refresher.class)
public class RefresherConnector extends AbstractExtensionConnector {

    RefresherServerRpc rpc = RpcProxy.create(RefresherServerRpc.class, this);

    public RefresherConnector() {
    }

    @Override
    public RefresherState getState() {
        return (RefresherState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        // TODO: enable or disable polling based on the state
    }

    @Override
    protected void extend(ServerConnector target) {
        // Not extending anything visible
    }

    @Override
    public void onUnregister() {
        // TODO: cancel the timer
        super.onUnregister();
    }

}
