package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servidor extends Remote {
	
	public void registrar (String nome) throws RemoteException;

	public void enviarMsgDireta (String mensagem, int identificador) throws RemoteException;
	
	public void enviarMsgParaTodos (String mensagem) throws RemoteException;

}