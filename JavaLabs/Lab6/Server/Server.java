import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class Server {

    private static int number = 1;
    private static int port = 8081;
    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    public static ArrayList<ClientInfo> clientsInfos = new ArrayList<>();
    

    private static ServerSocket server;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(port);
            while(true) {
                Socket client = server.accept();
                System.out.println("Connected " + number);
                clientsInfos.add(new ClientInfo("127.0.0.1", port, number));
                number++;
                ClientHandler clientHandler = new ClientHandler(client, clientsInfos);
                clients.add(clientHandler);

                for (ClientHandler cHandler : clients) {
                    cHandler.outputStream.writeObject("SEND_LIST");
                    cHandler.setClientsInfos(clientsInfos);
                    cHandler.sendClientsList();
                }

                pool.execute(clientHandler);
            }
        } finally {
            server.close();
        }
        
        
        
    }

    

}

