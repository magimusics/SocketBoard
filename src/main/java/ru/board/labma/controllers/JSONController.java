package ru.board.labma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.board.labma.json.Results;

import java.util.List;

/**
 * Created by ivangeel on 21.02.17.
 */

@Controller
public class JSONController {

    @Autowired
    JDBCHandler jdbcHandler;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ModelAndView getAllJSONReply()
    {
        System.out.println("getAllJSONReply works!");

        List<Results> resultsList = jdbcHandler.takeJSONs();

        return new ModelAndView("/index", "results", resultsList);
    }
}
