import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket servidor = new ServerSocket(port)) {
            System.out.println("Servidor rodando na porta " + port);

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new ClienteHandler(cliente)).start();
            }

        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }
}

class ClienteHandler implements Runnable {
    private Socket cliente;

    public ClienteHandler(Socket socket) {
        this.cliente = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(cliente.getOutputStream()), true)
        ) {
            String operacao = in.readLine();

            if (operacao == null || operacao.equalsIgnoreCase("sair")) {
                out.println("Conexão encerrada.");
                cliente.close();
                return;
            }

            String resultado = calcular(operacao);
            out.println(resultado);

        } catch (IOException e) {
            System.out.println("Erro ao lidar com cliente: " + e.getMessage());
        }
    }

    private String calcular(String input) {
        try {
            String[] partes = input.trim().split(" ");
            if (partes.length != 3) return "Formato inválido. Use: número operador número";

            double a = Double.parseDouble(partes[0]);
            String op = partes[1];
            double b = Double.parseDouble(partes[2]);

            switch (op) {
                case "+": return String.valueOf(a + b);
                case "-": return String.valueOf(a - b);
                case "*": return String.valueOf(a * b);
                case "/":
                    if (b == 0) return "Erro: divisão por zero";
                    return String.valueOf(a / b);
                default: return "Operador inválido.";
            }
        } catch (Exception e) {
            return "Erro ao processar operação: " + e.getMessage();
        }
    }
}
