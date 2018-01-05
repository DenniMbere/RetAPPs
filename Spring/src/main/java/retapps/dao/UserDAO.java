package retapps.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;

import retapps.entities.User;


public class UserDAO 
{
	Connection connection = new Connection();
	
	public List<User>list()
	{
		connection.create();
		List<User> users = null;
		try
		{
			connection.begin();
			Query query = connection.getEm().createNativeQuery("select * from users",User.class);
			users = query.getResultList();
			connection.commit();
			for(User u : users)
			{
				Hibernate.initialize(u.getRetailers());
				Hibernate.initialize(u.getShop());
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
		
		return users;
	}
	public void insert(String cf,String name,String surname,String mail,String birthday,int favoriteShop)
	{
		connection.create();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try
		{
			date = sdf.parse(birthday);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		User user = new User(cf,name,surname,mail,date,favoriteShop);
		try 
		{
			connection.begin();
			connection.getEm().persist(user);
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
	public void remove(String cf)
	{
		connection.create();
		User user = null;
		try
		{
			connection.begin();
			user = connection.getEm().find(User.class, cf);
			connection.getEm().remove(user);
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
	public User find(String cf)
	{
		connection.create();
		User user = null;
		try
		{
			connection.begin();
			user = connection.getEm().find(User.class, cf);
			Hibernate.initialize(user.getShop());
			Hibernate.initialize(user.getRetailers());
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
		return user;
	}
	public void update(String cf,String name,String surname,String mail,String birthday,int shopId)
	{
		connection.create();
		User user = null;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try 
		{
			date = sdf.parse(birthday);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(date);
		try
		{
			connection.begin();
			user = connection.getEm().find(User.class,cf);
			user.setName(name);
			user.setSurname(surname);
			user.setMail(mail);
			user.setBirthday(date);
			user.setShop_id(shopId);
			user = connection.getEm().merge(user);
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
