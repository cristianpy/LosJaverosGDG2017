import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.pedidovenda.model.Insumo;


public class Teste {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
	//	Solicitante solicitante = new Solicitante();
		Insumo insumo = new Insumo();
		insumo.setNome("tera");

		//solicitante.setCategoria("cate");
		//solicitante.setDireccion("acaite");

	//	solicitante.setTelefono("0983");
		//solicitante.setCorreo("aaa@aaa.com");
		//solicitante.setObs("obser");
		
		
		manager.persist(insumo);
		
		trx.commit();
	}
	
}
