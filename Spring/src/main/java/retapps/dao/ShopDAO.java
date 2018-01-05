package retapps.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;

import retapps.entities.Shop;


public class ShopDAO 
{
	Connection connection = new Connection();
	
	public List<Shop> list()
	{
		connection.create();
		List<Shop> shops = null;
		try
		{
			connection.begin();
			Query query = connection.getEm().createNativeQuery("select * from shops",Shop.class);
			shops = query.getResultList();
			for(Shop s : shops)
			{
				Hibernate.initialize(s.getRetailer());
				Hibernate.initialize(s.getUsers());
			}
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
		return shops;
	}
	
	public void insert(int id,String retailerCode,String province,String street)
	{
		connection.create();
		Shop shop = new Shop(id,retailerCode,province,street);
		try
		{
			connection.begin();
			connection.getEm().persist(shop);
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
	public void remove(int id)
	{
		connection.create();
		Shop shop = null;
		try
		{
			connection.begin();
			shop = connection.getEm().find(Shop.class,id);
			connection.getEm().remove(shop);
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
	public Shop find(int id)
	{
		connection.create();
		Shop shop = null;
		try
		{
			connection.begin();
			shop = connection.getEm().find(Shop.class,id);
			Hibernate.initialize(shop.getRetailer());
			Hibernate.initialize(shop.getUsers());
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
		return shop;
	}
	
	public void update(int id,String retailerCode,String province,String street)
	{
		connection.create();
		Shop shop = null;
		try
		{
			connection.begin();
			shop = connection.getEm().find(Shop.class,id);
			shop.setRetailer_code(retailerCode);
			shop.setProvince(province);
			shop.setStreet(street);
			shop = connection.getEm().merge(shop);
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
