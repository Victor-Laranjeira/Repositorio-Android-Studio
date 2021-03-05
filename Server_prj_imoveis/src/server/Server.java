package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.igoralvessilvestre.prj_imoveis.Estate;

public class Server {
	private ServerSocket server;
	private Socket client;
	private int PORTA;
	private List<Estate> lista = new ArrayList<Estate>();

	public Server(int PORTA) {
		this.PORTA = PORTA;
	}

	public void conectar() {
		try {
			server = new ServerSocket(PORTA);
			System.out.println("Rodando servidor na porta " + PORTA);

			while (true) {
				client = server.accept();
				System.out.println("Cliente conectado");
				new Thread(new ClientListenner(client)).start();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	//////////////////////////////////////////////////////
	public static void main(String[] args) {
		new Server(8083).conectar();

	}
	//////////////////////////////////////////////////////

	class ClientListenner implements Runnable {
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private List<Estate> listaCliente;
		private String host;

		public ClientListenner(Socket client) throws IOException {
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
			host = client.getInetAddress().getHostAddress();
		}

		@Override
		public void run() {
			try {
				listaCliente =(ArrayList<Estate>) input.readObject();
				lista.addAll(listaCliente);
				lerLista();
				System.out.println("Tamanho da Lista do servidor: "+lista.size());
				output.close();
				input.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public void lerLista() {
			System.out.println("***********************************\n"+host+" adicionou: ");
			for (Estate e : listaCliente) {
				System.out.println(e.toString());
			}
			System.out.println("***********************************");
		}

	}

}
