package hdn.examples.users.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
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
		
	@ManyToMany (mappedBy="roles")
	private Collection<User> users;

	public Role() {
		super();
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
