package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String[] rawMessage = in.readLine().split("=");
                    String clientRequest = rawMessage[1].replaceAll(" HTTP/1\\.1", "");
                    if (clientRequest.contains("Exit")) {
                        out.write(("HTTP/1.1 200 OK\r\nclosing the server...\r\n").getBytes());
                        out.write("Server is closed\r\n".getBytes());
                        server.close();
                        return;
                    }
                    if (clientRequest.contains("Hello")) {
                        out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n".getBytes());
                        out.write((clientRequest + "\r\n").getBytes());
                    }
                }
            }
        }
    }
}
