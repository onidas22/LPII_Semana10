package fabricas;

import dao.AuspiciadorDAO;
import dao.ClubDAO;
import dao.DirectorDAO;
import dao.GradoDAO;

//Es una f�brica de objetos
//Se usa el patr�n DAO (Data Access Object)
public abstract class Fabrica {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;

	public abstract ClubDAO getClubDAO();
	public abstract AuspiciadorDAO getAuspiciadorDAO();
	public abstract GradoDAO getGradoDAO();
	public abstract DirectorDAO getDirectorDAO();
	
	//Va fabricar subfabricas (Mysql y sqlserver)
	public static Fabrica getFabrica(int tipo){
		Fabrica salida = null;
		switch(tipo){
			case MYSQL: 
				salida = new FabricaMysql();
				break;
			
		}
		return salida;
	}
}
