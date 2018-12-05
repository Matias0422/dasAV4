package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cliente extends Remote {

	public void exibirMsg(String mensagem, String nome) throws RemoteException;

	public int getIdentificador() throws RemoteException;

	public String getNome() throws RemoteException;

}