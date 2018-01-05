package retapps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import retapps.dao.RetailerDAO;
import retapps.dao.UserDAO;
import retapps.entities.Retailer;
import retapps.entities.Shop;
import retapps.entities.User;
 
@Controller
public class SpringController 
{
	RetailerDAO daoRetailer = new RetailerDAO();
	UserDAO daoUser = new UserDAO();
 
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
	@RequestMapping(value="/update/{cf}/{name}/{surname}/{mail}/{birthday}/{shopId}", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody User updateUserJSON
	(
			@PathVariable("cf") String cf,
			@PathVariable("name") String name,
			@PathVariable("surname") String surname,
			@PathVariable("mail") String mail,
			@PathVariable("birthday") String birthday,
			@PathVariable("shopId") int shopId
	)
	{
		daoUser.update(cf, name, surname, mail, birthday, shopId);
		return daoUser.find(cf);
	}
	
	
	
	
}