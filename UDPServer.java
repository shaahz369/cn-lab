import java.net.*;
import java.io.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);
        byte[] buffer = new byte[1024];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DatagramPacket packet;

        while (true) {
            // Receive packet from client
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client: " + received);

            if ("exit".equals(received)) break;

            // Send response
            System.out.print("Server: ");
            String response = reader.readLine();
            buffer = response.getBytes();
            DatagramPacket toSend = new DatagramPacket(
                buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.send(toSend);

            if ("exit".equals(response)) break;
        }
        socket.close();
    }
}
