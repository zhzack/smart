package xyz.blue.entity;

import javax.websocket.Session;
import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 8957107006902627635L;

    private Integer client_id;

    private Session session;

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Client(Integer client_id, Session session) {
        this.client_id = client_id;
        this.session = session;
    }

    public Client() {
    }
}
