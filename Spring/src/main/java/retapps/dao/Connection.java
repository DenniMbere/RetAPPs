package retapps.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection 
{
	private EntityManager em;
	public Connection() {}
	
	public void create()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Retapps");
		em = factory.createEntityManager();		
	}
	public void begin()
	{
		em.getTransaction().begin();
	}
	public void commit()
	{
		em.getTransaction().commit();
	}
	public void rollback()
	{
		em.getTransaction().rollback();
	}
	public void close()
	{
		em.close();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
