package ru.geekbrains.java2_work6_Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int PORT = 8189;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запустился");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Scanner sc = new Scanner(System.in);
                        if (sc.hasNext()) {
                            while (sc.hasNext()) {
                                String textToSend = sc.nextLine();
                                try {
                                    out.writeUTF(textToSend);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }).start();
                String lineFromClient = in.readUTF();
                if ("end".equals(lineFromClient.toLowerCase())) {
                    break;
                }
                //out.writeUTF("Вы прислали на сервер: " + lineFromClient);
                System.out.println("Клиент: " + lineFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

