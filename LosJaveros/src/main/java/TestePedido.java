import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.pedidovenda.model.Requisito;

public class TestePedido {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		
		
		
		Requisito requisito = new Requisito();
		requisito.setNome("Fizcalizar");

		
		/*pedido.setDataInicio(new Date());
		pedido.setDataFin(new Date());
		pedido.setFormaPedido(FormaPedido.VERBAL);
		pedido.setObservacion("Aberto das 08 às 18h");
		pedido.setStatus(StatusPedido.ORCAMENTO);
		
		
		ItemRequisito item = new ItemRequisito();
		
		
		pedido.getItens().add(item);*/
		
		manager.persist(requisito);
		
		trx.commit();
	}
	
}
