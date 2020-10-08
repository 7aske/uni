const wsUri = "ws://localhost:8080/cs230_dz12_nikola_tasic_3698_war_exploded/ws";
let websocket = null;

function init() {
    websocket = new WebSocket(wsUri);
    websocket.onopen = onOpen;
    websocket.onerror = onError;
    websocket.onmessage = onMessage;
}

function onOpen(evt) {
    console.info("CONNECTED");
}

function onError(evt) {
    console.error("ERROR: " + evt.data);
}

function onMessage(evt) {
    console.log("RECEIVED: " + evt.data);
    const json = JSON.parse(evt.data);

    document.getElementById('firstName').value = json.firstName;
    document.getElementById('lastName').value = json.lastName;
    document.getElementById('city').value = json.city;
}

function doSend(message) {
    if (websocket) {
        console.log("SENT: " + message);
        websocket.send(message);
    }
}

window.addEventListener("load", init, false);

