import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 12345;

        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                Socket socket = new Socket(ip, port);

                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream()), true
                );

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                System.out.print("Digite uma operação (ex: 2 + 3) ou 'sair': ");
                String operacao = input.nextLine();

                out.println(operacao);

                if (operacao.equalsIgnoreCase("sair")) {
                    socket.close();
                    break;
                }

                String resposta = in.readLine();
                System.out.println("Resultado do servidor: " + resposta);

                socket.close();
            }
        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
