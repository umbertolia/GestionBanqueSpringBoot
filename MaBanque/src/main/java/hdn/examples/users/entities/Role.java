package hdn.examples.users.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */

@Entity
@Table(name = "role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -969665538539581207L;

	@Id
	private String roleName;
		
	@ManyToMany (mappedBy="roles", fetch = FetchType.EAGER)
	private Collection<User> users;

	public Role() {
		super();
	}

	
	
	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}



	public Role(String roleName, Collection<User> users) {
		super();
		this.roleName = roleName;
		this.users = users;
	}



	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	
	
	}
