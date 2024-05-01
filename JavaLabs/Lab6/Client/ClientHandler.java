import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


public class ClientHandler implements Runnable {

    private Socket client;
    private boolean isClosed = false;
    public ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    private ArrayList<ClientInfo> clientsInfos = new ArrayList<>();

    public ClientHandler(Socket clientSocket, ArrayList<ClientInfo> clientsInfos) throws IOException {
        this.client = clientSocket;
        this.clientsInfos = new ArrayList<>(clientsInfos);
        inputStream = new ObjectInputStream(client.getInputStream());
        outputStream = new ObjectOutputStream(client.getOutputStream());
    }

    public void setClientsInfos(ArrayList<ClientInfo> clientsInfos) {
        this.clientsInfos = new ArrayList<>(clientsInfos);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = (String) inputStream.readObject();
                if (request.charAt(request.length()-1) > '0' || request.charAt(request.length()-1) <= '9') {
                    String stringId = "" + request.charAt(request.length()-1);
                    int id = Integer.parseInt(stringId);
                    request = request.substring(0, request.length()-2);
                    ArrayList<Bee> bees = (ArrayList<Bee>) inputStream.readObject();
                    if ("SEND_BEES".equals(request)) {
                        for (ClientInfo clientInfo : clientsInfos) {
                            if (id == clientInfo.getNumber()) {
                                Server.clients.get(clientInfo.getNumber()-1).sendBees(bees);
                                break;
                            }
                        }
                    }
                }
                
                if ("CLOSE_CONNECTION".equals(request)) {
                    isClosed = true;
                    for (Iterator<ClientHandler> iterator = Server.clients.iterator(); iterator.hasNext(); ) {
                        ClientHandler clientHandler = iterator.next();
                        if (clientHandler.getClosed()) {
                            Server.clientsInfos.remove(Server.clients.indexOf(clientHandler));
                            iterator.remove();
                            break;
                        }
                    }
                    for (ClientHandler clientHandler : Server.clients ) {
                        clientHandler.setClientsInfos(Server.clientsInfos);
                        clientHandler.outputStream.writeObject("SEND_LIST");
                        clientHandler.sendClientsList();
                    }
                    break;
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                client.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getClosed() {
        return isClosed;
    }

    public void sendClientsList() {
        try {
            System.out.println(clientsInfos);
            outputStream.writeObject(clientsInfos);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBees(ArrayList<Bee> bees) {
        try {
            outputStream.writeObject("SEND_BEES");
            outputStream.writeObject(bees);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }
    
}
