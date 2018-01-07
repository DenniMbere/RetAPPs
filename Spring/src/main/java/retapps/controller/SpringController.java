package retapps.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import retapps.dao.RetailerDAO;
import retapps.dao.UserDAO;
import retapps.entities.Retailer;
import retapps.entities.Shop;
import retapps.entities.User;
 
@Controller
public class SpringController 
{
	@Autowired
	@Qualifier("retailerDao")
	RetailerDAO daoRetailer ;
	@Autowired
	@Qualifier("userDao")
	UserDAO daoUser;
 
	@RequestMapping(value="/retailers",method = RequestMethod.GET)
	public @ResponseBody List<Retailer> getRetailersJSON()
	{
		return daoRetailer.list();
	}
	@RequestMapping(value="/shops/{code}",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody List<Shop> getShopsJSON(@PathVariable("code") String code)
	{
		return daoRetailer.find(code).getShops();
	}
	@RequestMapping(value="/users/{code}",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody List<User> getUsersJSON(@PathVariable("code") String code)
	{
		return daoRetailer.find(code).getUsers();
	}
	@RequestMapping(value="/users/{cf}", method = RequestMethod.PATCH,produces="application/json")
	public @ResponseBody User updateUserJSON
	(
			@PathVariable("cf") String cf,
			@RequestBody User user
	)
	{
		daoUser.update(cf, user.getName(), user.getSurname(), user.getMail(), user.getBirthday(), user.getShop_id());
		return daoUser.find(cf);
	}
	
}