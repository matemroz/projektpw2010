/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Parent")
@SequenceGenerator(name = "SEQ_PARENT", sequenceName = "PARENT_SEQUENCE", allocationSize = 1)
public class Parent implements Serializable {

	private static final long serialVersionUID = -6011746362018949204L;

	@Id
	private long idParent;
	private String shortedPassword;
	private String email;
	private String name;

	private ParentSettings parentSettings;
	
	public Parent() {
	}
	
	public void setParentSettings(ParentSettings parentSettings){
    	this.parentSettings = parentSettings;
    }
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
	public ParentSettings getParentSettings(){
    	return parentSettings;
    }

	public void setIdParent(long idParent) {
		this.idParent = idParent;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARENT")
	public long getIdParent() {
		return idParent;
	}

	public void setShortedPassword(String shortedPassword) {
		this.shortedPassword = shortedPassword;
	}

	@Column(name = "shortedPassword", nullable = false, length = 50)
	public String getShortedPassword() {
		return shortedPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "email", nullable = false, length = 100, unique = true)
	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "name", nullable = false, length = 50)
    public String getName() {
		return name;
	}
}
