/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Family")
@SequenceGenerator(name = "SEQ_FAMILY", sequenceName = "FAMILY_SEQUENCE", allocationSize = 1)
public class Family implements Serializable {

	private static final long serialVersionUID = 7321384206568640170L;

	@Id
	private long idFamily;

	private long idParent;
	private long idChild;
	private Children child;
	private Parent parent;
	
	@OneToOne
	@JoinColumn(name = "idChild")
	public Children getChild() {
		return child;
	}

	public void setChild(Children child) {
		this.child = child;
	}

	@OneToOne
	@JoinColumn(name = "idParent")
	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FAMILY")
	public long getIdFamily() {
		return idFamily;
	}

	public void setIdFamily(long idFamily) {
		this.idFamily = idFamily;
	}

	public long getIdParent() {
		return idParent;
	}

	public void setIdParent(long idParent) {
		this.idParent = idParent;
	}

	public long getIdChild() {
		return idChild;
	}

	public void setIdChild(long idChild) {
		this.idChild = idChild;
	}
}
