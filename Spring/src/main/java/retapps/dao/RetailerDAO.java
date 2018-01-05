package retapps.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;

import retapps.entities.Retailer;


public class RetailerDAO 
{
	Connection connection = new Connection();
	
	public List<Retailer>list()
	{
		connection.create();
		List<Retailer> retailers = null;
		try
		{
			connection.begin();
			Query query = connection.getEm().createNativeQuery("select * from retailers",Retailer.class);
			retailers = query.getResultList();
			connection.commit();
			for(Retailer r : retailers)
			{
				Hibernate.initialize(r);
				Hibernate.initialize(r.getShops());
				Hibernate.initialize(r.getUsers());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		finally
		{
			if(connection!=null)
				connection.close();
		}
		
		return retailers;
	}
	public void insert(String code,String name)
	{
		connection.create();
		Retailer retailer = new Retailer(code,name);
		try 
		{
			connection.begin();
			connection.getEm().persist(retailer);
			connection.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		finally
		{
			if(connection!=null)
				connection.close();
		}
	}
	public void remove(String code)
	{
		connection.create();
		Retailer retailer = null;
		try
		{
			connection.begin();
			retailer = connection.getEm().find(Retailer.class, code);
			connection.getEm().remove(retailer);
			connection.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		finally
		{
			if(connection!=null)
				connection.close();
		}
	}
	public Retailer find(String code)
	{
		connection.create();
		Retailer retailer = null;
		try
		{
			connection.begin();
			retailer = connection.getEm().find(Retailer.class, code);
			Hibernate.initialize(retailer.getShops());
			Hibernate.initialize(retailer.getUsers());
			connection.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		finally
		{
			if(connection!=null)
				connection.close();
		}
		return retailer;
	}
	public void update(String code,String name)
	{
		connection.create();
		Retailer retailer = null;
		try
		{
			connection.begin();
			retailer = connection.getEm().find(Retailer.class,code);
			retailer.setName(name);
			retailer = connection.getEm().merge(retailer);
			connection.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connection.rollback();
		}
		finally
		{
			if(connection!=null)
				connection.close();
		}
	}
	

}
