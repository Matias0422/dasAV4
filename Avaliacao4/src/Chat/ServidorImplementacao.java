package Chat;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServidorImplementacao extends UnicastRemoteObject implements Servidor {
	
	private ArrayList<Cliente> pessoas = new ArrayList<Cliente>();
	
	protected ServidorImplementacao() throws RemoteException {
		super();
	}

	@Override
	public void registrar(String nome) throws RemoteException {
		int identificador = pessoas.size() + 1;
		Cliente pessoa = new ClienteImplementacao(identificador, nome);
		pessoas.add(pessoa);

		System.out.println("Pessoa registrada com sucesso!!\n\nAproveite o chat!!");
	}

	@Override
	public void enviarMsgDireta(String mensagem, int identificador) throws RemoteException {
		
		int i = 0;
		 while (i < pessoas.size()) {

			if (pessoas.get(i).getIdentificador() == identificador ) {
			 
				Cliente pessoa = (Cliente) pessoas.get(i);
		
				pessoa.exibirMsg(mensagem, pessoas.get(i).getNome());
				
				break;
			}	
			
			i++;

		 }
		
	}

	@Override
	public void enviarMsgParaTodos(String mensagem) throws RemoteException {
		int i = 0;
		 while (i < pessoas.size()) {

			Cliente pessoa = (Cliente) pessoas.get(i);
			
			pessoa.exibirMsg(mensagem, pessoas.get(i).getNome());
			
			i++;

		 }
		
	}
	
	
	public static void main (String[] args) {

		try {
			
			Registry registry = LocateRegistry.createRegistry(8000);

			Servidor servidor = new ServidorImplementacao();
	
			registry.bind("server", servidor);
			
			//System.setProperty("java.rmi.server.hostname","192.168.0.19");
			
			//Naming.rebind("server",servidor);
			

		} catch (Exception e) {

			System.out.println ("Error: " + e.getMessage());
		}
		
	}
}
