/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Children")
@SequenceGenerator(name = "SEQ_CHILDREN", sequenceName = "CHILDREN_SEQUENCE", allocationSize = 1)
public class Children implements Serializable {

	private static final long serialVersionUID = 6678269068525969028L;

	@Id
	private long idChild;
	private long idArea;
	private String imei;
	private String name;
	private Date dateOfBirth;
	private String key;
	private Area area;

	Children() {
	}

	public void setArea(Area area){
		this.area = area;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
	public Area getArea(){
    	return area;
    }
	
	public void setIdChild(long idChild) {
		this.idChild = idChild;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CHILDREN")
	public long getIdChild() {
		return idChild;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name = "imei", nullable = false, length = 50)
	public String getImei() {
		return imei;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "dateOfBirth", nullable = false)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "shortedPassword", nullable = false, length = 50)
	public String getKey() {
		return key;
	}
}
