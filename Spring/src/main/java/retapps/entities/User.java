package retapps.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="USERS")
public class User 
{
	@Id
	@Column(name="CF")
	private String cf;
	@Column(name="NAME")
	private String name;
	@Column(name="SURNAME")
	private String surname;
	@Column(name="MAIL")
	private String mail;
	@Column(name="BIRTHDAY")
	private Date birthday;
	@Column(name="SHOP_ID")
	private int shop_id;
	
	@ManyToOne
	@JoinColumn(name="SHOP_ID",insertable=false,updatable=false)
	private Shop shop;
	
	@ManyToMany
	@JoinTable(
			name="RETAILERS_USERS", joinColumns = @JoinColumn(name="USER_CF",referencedColumnName="CF"),
			inverseJoinColumns= @JoinColumn(name="RETAILER_CODE",referencedColumnName="CODE"))
	private List<Retailer> retailers;

	public User() {}
	
	public User(String cf, String name, String surname, String mail, Date birthday,int shop_id)
	{
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.birthday = birthday;
		this.shop_id = shop_id;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@JsonIgnore
	public List<Retailer> getRetailers() {
		return retailers;
	}

	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	@JsonIgnore
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String toString()
	{
		return 
			"CF: " + this.cf + "\n" +
			"Name: " + this.name + "\n" +
			"Surname: " + this.surname + "\n" +
			"Mail: " + this.mail + "\n" +
			"Birthday: " + this.birthday + "\n"+ 
			"Favorite shop id: " + this.shop_id + "\n";
	}
	
	
	

}
