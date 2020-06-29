package pl.dabal.echo.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class EchoServer {

    private ExecutorService executorService;
    private Integer port;

    public EchoServer(Integer numberOfThreads, Integer port) {
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                log.info(String.format("new connectioon from - %s", clientSocket.getInetAddress().getHostAddress()));
                executorService.submit(new EchoServerWorker(clientSocket));
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer(5, 6666);
        server.start();
    }

}