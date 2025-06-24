import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 12345;
        Socket socket = null;

        try {
            while (true) {
                socket = new Socket(ip, port);
                Scanner input = new Scanner(System.in);
                String mensagem = input.next();


                // Cria writer para enviar dados ao servidor
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream()), true
                );



                out.println(mensagem);
                System.out.println("Mensagem enviada ao servidor: " + mensagem);

                if(mensagem.equals("sair")){
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }


    }
}
