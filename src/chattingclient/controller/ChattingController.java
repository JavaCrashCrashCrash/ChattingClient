package chattingclient.controller;

import chattingclient.model.Client;
import chattingclient.view.MainUI;

public class ChattingController {
    public void startProgram() {
        MainUI mainUI = new MainUI();
        connect();

    }
    public void connect() {
        Client client = new Client();
        client.connect();
    }
}
