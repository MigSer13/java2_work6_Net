package ru.geekbrains.java2_work6_Net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int PORT = 8189;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запустился");
            while (true) {
                socket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

