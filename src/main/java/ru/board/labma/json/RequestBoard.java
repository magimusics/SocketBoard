package ru.board.labma.json;

/**
 * Created by ivangeel on 17.02.17.
 */

public class RequestBoard {

    private String clientId;
    private String type;
    private int x;
    private int y;

    public RequestBoard() {
    }

    public RequestBoard(String clientId, String type, int x, int y) {
        this.clientId = clientId;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getClientId() {
        return clientId;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
