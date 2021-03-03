package ru.geekbrains.java2_work6_Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class Client {
    private final String ADDRESS_SERVER = "localhost";
    private final int PORT_SERVER = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
//        try {
//            socket = new Socket(ADDRESS_SERVER, PORT_SERVER);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                socket.close();
//                in.close();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        socket = new Socket(ADDRESS_SERVER, PORT_SERVER);
                        in = new DataInputStream(socket.getInputStream());
                        out = new DataOutputStream(socket.getOutputStream());
                        String resultingStr = in.readUTF();
                        if ("end".equals(resultingStr.toLowerCase())) {
                            break;
                        }
                        System.out.println("Ответ сервера: " + resultingStr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner sc = new Scanner(System.in);
                try {
                    socket = new Socket(ADDRESS_SERVER, PORT_SERVER);
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    while (sc.hasNext()) {
                        String textToSend = sc.nextLine();
                        out.writeUTF(textToSend);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
