import java.io.Serializable;

public class ClientInfo implements Serializable {
    private int port;
    private String IP;
    private int number;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ClientInfo(String IP, int port, int number) {
        this.IP = IP;
        this.port = port;
        this.number = number;
    }

    @Override
    public String toString() {
        return "ClientInfo [port=" + port + ", IP=" + IP + ", number=" + number + "]";
    }

    

}
