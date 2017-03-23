var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#name").prop("disabled", connected)
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/getboard', function (line) {
            drawLine(JSON.parse(line.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function drawLine(message) {
    
    if($('#name').val()!=message.clientId) 
    {
        canvas = $('#pad')[0]
        context = canvas.getContext("2d")

        if (message.type == 'START') {
            context.beginPath()
            context.moveTo(message.x, message.y)
            context.lineTo(message.x, message.y)
            context.stroke()
        }
        if (message.type == 'MOVE') {
            context.lineTo(message.x, message.y)
            context.stroke()
        }
    }
    
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#connect" ).click(function() { if($("#name").val()) connect(); else alert("Введите имя")});
});

