package Chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClienteImplementacao extends UnicastRemoteObject implements Cliente {
	
	private int identificador;
	private String nome;

	public ClienteImplementacao(int identificador, String nome) throws RemoteException {
		super();
		identificador = this.identificador;
		nome = this.nome;
	}

	@Override
	public int getIdentificador() {
		return identificador;
	}

	@Override
	public void exibirMsg(String mensagem, String nome) throws RemoteException {
		System.out.println(nome + ":" + mensagem);
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	public static void main (String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		Servidor servidor = (Servidor) Naming.lookup("//127.0.0.1:8000/server");
		
		System.out.println("<Registro de pessoa>\n");
		
		System.out.println("Nome:");

		Scanner scanner = new Scanner(System.in);
		String nome = scanner.nextLine();
		
		servidor.registrar(nome);
		
		int stop = 0;
		
		System.out.println("Digite /all-<mensagem> para enviar mensagem para todas as pessoas.\nDigite /<ID da pessoa>-<mensagem> para enviar mensagem para uma pessoa especifica.\nDigite /0 para gechar o chat. \n\n");
		
		while (stop != 1) {
			String input = scanner.nextLine();
			
			if (input.contains("/0") ) {
				break;
			}
			
			String[] mensagem = input.split("-");
			
			if (input.contains("/all") ) {
				servidor.enviarMsgParaTodos(mensagem[1]);
			}
			else if (input.contains("/") ) {
				servidor.enviarMsgDireta(mensagem[1], Integer.parseInt(mensagem[0].replaceAll("/", "")));
				System.out.println("Mensagem enviada!\n\n");
			}
				
		}
	

	}
}
