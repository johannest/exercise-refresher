package com.vaadin.training.clientside.refresher;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.AbstractExtension;
import com.vaadin.training.clientside.refresher.client.RefresherServerRpc;
import com.vaadin.training.clientside.refresher.client.RefresherState;

@SuppressWarnings("serial")
public class Refresher extends AbstractExtension {

    private static final int DEFAULT_POLL_INTERVAL_MS = 1000;

    private List<PollListener> listeners = new ArrayList<PollListener>();

    private final RefresherServerRpc rpc = new RefresherServerRpc() {

        @Override
        public void refresh() {
            for (PollListener listener : listeners) {
                listener.poll();
            }
        }
    };

    public Refresher() {
        registerRpc(rpc);
        setInterval(DEFAULT_POLL_INTERVAL_MS);
    }

    public void setInterval(int interval) {
        getState().interval = interval;
    }

    public int getInterval() {
        return getState().interval;
    }

    @Override
    public RefresherState getState() {
        return (RefresherState) super.getState();
    }

    public void setEnabled(boolean enabled) {
        getState().enabled = enabled;
    }

    public void addPollListener(PollListener listener) {
        listeners.add(listener);
    }

    public void removePollListener(PollListener listener) {
        listeners.remove(listener);
    }

    public static interface PollListener {
        public void poll();
    }
}
