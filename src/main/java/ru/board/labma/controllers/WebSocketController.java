package ru.board.labma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.board.labma.json.RequestBoard;
import ru.board.labma.json.Results;

/**
 * Created by ivangeel on 17.02.17.
 */

@Controller
public class WebSocketController {

    @Autowired
    JDBCHandler jdbcHandler = new JDBCHandler();

    @MessageMapping("/board")
    @SendTo("/topic/getboard")
    public Results results(RequestBoard requestBoard) throws Exception
    {
        Results results = new Results(requestBoard.getClientId(), requestBoard.getType(), requestBoard.getX(), requestBoard.getY());

        System.out.println(requestBoard.getClientId() + ", " + requestBoard.getType() + ", " + requestBoard.getX() + ", " + requestBoard.getY());

        jdbcHandler.setJSONs(requestBoard);

        return results;
    }
}
