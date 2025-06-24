import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Servidor {
    public static void main(String[] args) {
        int port = 12345;
        try {

            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Servidor rodando na porta" + port);
            Socket cliente = null;
            while (true) {
                cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                //Prepara leitor para receber mensagem
                BufferedReader receberMensagem = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                String mensagem = receberMensagem.readLine();
                System.out.println("Mensagem recebida do cliente: " + mensagem);
                if (mensagem.equals("sair")) {
                    break;
                }
            }
            cliente.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

    }
}