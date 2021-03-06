package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    StringBuilder fullClientReply = new StringBuilder();
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        fullClientReply.append(str);
                        str = in.readLine();
                    }
                    String[] rawMessage = fullClientReply.toString().split("=", 2);
                    String clientRequest = rawMessage[1].replaceAll(" HTTP/1\\.1", "");
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend\r\n\r\n".getBytes());
                    if (clientRequest.contains("Exit")) {
                        out.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
                        out.write(("closing the server...\r\n\r\n").getBytes());
                        out.write("Server is closed\r\n\r\n".getBytes());
                        server.close();
                        return;
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write((clientRequest + "\r\n\r\n").getBytes());
                    }
                } catch (Exception e) {
                    LOG.error("Exception in line A", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in line B", e);
        }
    }
}
