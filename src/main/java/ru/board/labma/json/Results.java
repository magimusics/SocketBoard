package ru.board.labma.json;

import java.util.Hashtable;

/**
 * Created by ivangeel on 17.02.17.
 */

public class Results {

    private String clientId;
    private String typeC;
    private int x;
    private int y;

    public Results() {
    }

    public Results(String clientId, String type, int x, int y) {
        this.clientId = clientId;
        this.typeC = type;
        this.x = x;
        this.y = y;
    }

    public String getClientId() {
        return clientId;
    }

    public String getType() {
        return typeC;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{\"clientId\":\""+clientId+"\", \"type\":\""+typeC+"\", \"x\":"+x+",\"y\":"+y+"}";
    }
}
