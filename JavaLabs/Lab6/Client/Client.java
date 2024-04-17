import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Client implements Runnable {

    ArrayList<ClientInfo> infos = new ArrayList<>();
    ArrayList<Integer> clientsIdies = new ArrayList<>();
    private final int port = 8081;
    private static final String hostName = "127.0.0.1";
    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    private boolean isClosed = false;
    private Habitat beeWorld;

    public Client(Habitat beeWorld) throws IOException {
        this.beeWorld = beeWorld;
        ConnectToServer(port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (isClosed) {
                    break;
                }
                String request = (String) inputStream.readObject();
                if ("SEND_LIST".equals(request)) {
                    RequestClientsFromServer(outputStream, inputStream);
                }
                if ("SEND_BEES".equals(request)) {
                    getBeesFromServer();
                }
            } 
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void ConnectToServer(int port) {
        System.out.println("Trying to connect " + port);
        try {
            socket = new Socket(hostName, port);
            System.out.println("connected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RequestClientsFromServer(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        try {
            infos = new ArrayList<>((ArrayList<ClientInfo>) inputStream.readObject());

            for (ClientInfo client : infos) {
                System.out.println("Number : " + client.getNumber() + " IP: " + client.getIP() + " port: " + client.getPort());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientsIdies = new ArrayList<>();
        for (ClientInfo client : infos) {
            clientsIdies.add(client.getNumber());
        }
    }

    public void sendCloseMessage() {
        try {
            isClosed = true;
            outputStream.flush();
            outputStream.writeObject("CLOSE_CONNECTION");
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBees(String clientId, ArrayList<Bee> randomBees) {
        try {
            isClosed = true;
            outputStream.flush();
            outputStream.writeObject("SEND_BEES " + clientId);
            outputStream.writeObject(randomBees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBeesFromServer() {
        try {
            ArrayList<Bee> addedBees = (ArrayList<Bee>) inputStream.readObject();
            beeWorld.addNewBees(addedBees);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getClientsIdies() {
        return clientsIdies;
    }

    public ArrayList<ClientInfo> getInfos() {
        return infos;
    }

    

}
