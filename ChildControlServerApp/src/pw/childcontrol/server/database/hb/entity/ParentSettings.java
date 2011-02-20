/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "ParentSettings")
@SequenceGenerator(name = "SEQ_PARENT_SETTINGS", sequenceName = "PARENT_SETTINGS_SEQUENCE", allocationSize = 1)
public class ParentSettings implements Serializable {

	private static final long serialVersionUID = -7818171935249217197L;

	@Id
	private long idParentSettings;

	private long idParent;
	private long idChild;
	private float stepTime;
	
	ParentSettings(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PARENT_SETTINGS")
	public long getIdParentSettings() {
		return idParentSettings;
	}
	
	public void setIdParentSettings(long idParentSettings) {
		this.idParentSettings = idParentSettings;
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

	@Column(name = "stepTime", nullable = false)
	public float getStepTime() {
		return stepTime;
	}

	public void setStepTime(float stepTime) {
		this.stepTime = stepTime;
	}
}
