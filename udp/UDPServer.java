import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] rcvData = new byte[1024];

        System.out.println("Server is running... Waiting for data...");

        DatagramPacket receivePacket = new DatagramPacket(rcvData, rcvData.length);
        serverSocket.receive(receivePacket);

        String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
        String[] parts = clientMsg.trim().split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        int sum = a + b;

        System.out.println("Received numbers: " + a + " and " + b);
        System.out.println("Sum: " + sum);

        serverSocket.close();
    }
}
