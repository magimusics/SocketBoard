<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>WebSocket</title>
    <link href="/resources/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/main.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="/resources/js/sockjs.min.js"></script>
    <script src="/resources/js/stomp.min.js"></script>
    <script src="/resources/js/boardscript.js"></script>

</head>
<body>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-8">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">Введите имя</label>
                    <input type="text" id="name" class="form-control" placeholder="Это обязательно!">
                    <button id="connect" class="btn btn-default" type="submit">Подключиться</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Отключиться</button>
                </div>
            </form>
        </div>
    </div>
    <br>

    <canvas id='pad' width='860' height='600' style="border: solid 2px #f5f5f5"></canvas>
    <script>
        canvas  = $('#pad')[0]
        context = canvas.getContext("2d")
        pendown = false
        start = true
        var jResults;

        <c:if test="${not empty results}">

        jResults= ${results}
        </c:if>

        if(jResults){
            for(var i = 0; i < jResults.length; i++){
                var str = JSON.stringify(jResults[i])
                var parsedStr = JSON.parse(str)

                if(parsedStr.type=='START'){
                    context.beginPath(parsedStr.x, parsedStr.y)
                    context.moveTo(parsedStr.x, parsedStr.y)
                    context.lineTo(parsedStr.x, parsedStr.y)
                    context.stroke()
                }
                if(parsedStr.type=='MOVE'){
                    context.lineTo(parsedStr.x, parsedStr.y)
                    context.stroke()
                }
            }
        }

        $('#pad').mousemove(function(event)
        {
            $('#pad').mousedown(function() { pendown = true  } )
            $('#pad')  .mouseup(function() { pendown = false } )

            var xpos = event.pageX - canvas.offsetLeft
            var ypos = event.pageY - canvas.offsetTop

            if (pendown)
            {
                if(start)
                {
                    start = false
                    stompClient.send("/app/board", {}, JSON.stringify({"clientId":$('#name').val(), "type":'START', "x": xpos, "y": ypos}));
                }
                else {
                    context.lineTo(xpos, ypos)
                    stompClient.send("/app/board", {}, JSON.stringify({
                        "clientId": $('#name').val(),
                        "type": 'MOVE',
                        "x": xpos,
                        "y": ypos
                    }));
                }
            }
            else
            {
                start = true
                context.moveTo(xpos, ypos)
            }

            context.stroke()
        })

    </script>


</div>
</body>
</html>
