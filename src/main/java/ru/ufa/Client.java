package ru.ufa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8069;
        System.out.println("write \"end\" for exit");

        while (true) {

            try (Socket socket = new Socket(host, port);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if (input.equals("end")) {
                    out.println(input);
                    System.out.println("Bye-Bye!");
                    return;
                }

                out.println(input);
                System.out.println(in.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}