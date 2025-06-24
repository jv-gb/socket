import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
    String ip = "127.0.0.1";
    int port = 12345;
    Socket socket = null;

    try{
        socket = new Socket(ip, port);

        // Cria writer para enviar dados ao servidor
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()), true
        );

        String mensagem = "Olá, servidor!";
        out.println(mensagem);
        System.out.println("Mensagem enviada ao servidor: " + mensagem);
    }
    catch(Exception e){
        System.out.println("Erro: " + e);
    }


    }
}
