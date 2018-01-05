package retapps.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="SHOPS")
public class Shop 
{
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="RETAILER_CODE")
	private String retailer_code;
	@Column(name="PROVINCE")
	private String province;
	@Column(name="STREET")
	private String street;
	
	@OneToMany(mappedBy="shop",fetch=FetchType.LAZY)
	private List<User> users;
	
	@ManyToOne
	@JoinColumn(name="RETAILER_CODE",insertable= false, updatable = false)
	private Retailer retailer;

	public Shop() {}
	
	public Shop(int id, String retailer_code, String province, String street)
	{
		this.id = id;
		this.retailer_code = retailer_code;
		this.province = province;
		this.street = street;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRetailer_code() {
		return retailer_code;
	}

	public void setRetailer_code(String retailer_code) {
		this.retailer_code = retailer_code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@JsonIgnore
	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String toString()
	{
		return
			"ID: " + this.id + "\n" + "Retailer Code: " + this.retailer_code + "\n" + "Province: " + this.province + "\n" + "Street: " + this.street;
	}
	
	
	

}
