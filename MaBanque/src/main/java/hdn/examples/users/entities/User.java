package hdn.examples.users.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Auteur HDN Crée le Dec 6, 2018
 *
 * Cette classe permet de ...
 * 
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4651549644160854594L;

	@Id
	private String username;

	private String password;

	private boolean actif;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_avec_role", joinColumns = {
			@JoinColumn(name = "user_name", referencedColumnName = "username") }, inverseJoinColumns = {
					@JoinColumn(name = "role_name", referencedColumnName = "rolename") })
	private List<Role> roles;

	public User() {
		super();
	}

	public User(String username, String password, boolean actif) {
		super();
		this.username = username;
		this.password = password;
		this.actif = actif;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
