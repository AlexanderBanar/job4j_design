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
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        fullClientReply.append(str);
                    }
                    String[] rawMessage = fullClientReply.toString().split("=", 2);
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
                } catch (Exception e) {
                    LOG.error("Exception in line A", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in line B", e);
        }
    }
}
