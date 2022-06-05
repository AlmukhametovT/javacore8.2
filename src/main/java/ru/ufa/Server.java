package ru.ufa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int port = 8069;
        String clientName = "";
        int count = 0;

        while (true) {

            try (ServerSocket serverSocket = new ServerSocket(port);
                 // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
                 Socket clientSocket = serverSocket.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                if (count == 0) {
                    System.out.println("New connection accepted");
                }

                final String knock = in.readLine();
                if (knock.equals("end")) {
                    System.out.println("Bye, message count = " + (count + 1));
                    System.out.println("Сonnection closed");
                    return;
                }
                switch (count) {
                    case 0:
                        out.println("Hi, Write your name");
                        count++;
                        break;
                    case 1:
                        clientName = clientName + knock;
                        out.println(clientName + ", Are you child? (yes/no)");
                        count++;
                        break;
                    case 2:
                        if (knock.equals("yes")) {
                            out.println("Welcome to the kids area, " + clientName + "! Let's play!");
                            count++;
                            break;
                        } else if (knock.equals("no")) {
                            out.println("Welcome to the adult zone," + clientName + "! Have a good rest, or a good working day!");
                            count++;
                            break;
                        } else
                            out.println("Write yes or no only");
                        break;
                    default:
                        out.println("tell me something else?");
                        count++;
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}