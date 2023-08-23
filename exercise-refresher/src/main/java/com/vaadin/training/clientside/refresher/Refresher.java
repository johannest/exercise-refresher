package com.vaadin.training.clientside.refresher;

import com.vaadin.server.AbstractExtension;
import com.vaadin.training.clientside.refresher.client.RefresherServerRpc;
import com.vaadin.training.clientside.refresher.client.RefresherState;

public class Refresher extends AbstractExtension {

    private static final int DEFAULT_POLL_INTERVAL_MS = 1000;

    private final RefresherServerRpc rpc = new RefresherServerRpc() {

    };

    public Refresher() {
        registerRpc(rpc);
        setInterval(DEFAULT_POLL_INTERVAL_MS);
    }

    public void setInterval(int interval) {
        // TODO: Set the interval to shared state
    }

    public int getInterval() {
        // TODO: Get the interval from shared state
        return 0;
    }

    public void setEnabled(boolean enabled) {
        // TODO: Implement this
    }

    @Override
    public RefresherState getState() {
        return (RefresherState) super.getState();
    }

    public void addPollListener(PollListener listener) {
        // TODO: Implement this
    }

    public void removePollListener(PollListener listener) {
        // TODO: Implement this
    }

    public static interface PollListener {
        public void poll();
    }
}
