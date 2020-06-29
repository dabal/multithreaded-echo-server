package pl.dabal.echo.server;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class EchoServerWorker implements Runnable {

    Socket clientSocket;

    public EchoServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                log.debug(String.format("client ip: %s - msg: %s", clientSocket.getInetAddress().getHostAddress(), inputLine));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }
}
