package retapps.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="RETAILERS")
public class Retailer 
{
	@Id
	@Column(name="CODE")
	private String code;
	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy="retailer",fetch=FetchType.LAZY)
	private List<Shop> shops;
	
	@ManyToMany
	@JoinTable(
			name="RETAILERS_USERS", joinColumns = @JoinColumn(name="RETAILER_CODE",referencedColumnName="CODE"),
			inverseJoinColumns= @JoinColumn(name="USER_CF",referencedColumnName="CF"))
	private List<User> users;
	
	public Retailer() {}
	
	public Retailer(String code, String name) 
	{
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
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
		return "Code: " + this.code + " " + "Name: " + this.name;
	}

	public String toJSON()
	{
		String ris = "{";
		ris += "'code':'"+this.code+"',";
		ris += "'name':'"+this.name+"'";
		ris += "}";
		ris = ris.replace("'", "\"");
		return ris;
	}
	

}
