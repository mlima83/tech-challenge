package com.pixeon.base.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pixeon.base.dto.DefaultEntityDto;

@Entity 
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class DefaultEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtCreated;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtUpdated;

    private boolean active = true;
    
    @Version
    private long version;
    
    @Transient
    private StateChange stateChange;
    
    public DefaultEntity() {}
    
    public DefaultEntity(DefaultEntityDto defaultEntity) {
    	this.active = defaultEntity.isActive();
    	this.dtCreated = defaultEntity.getDtCreated();
    	this.dtUpdated = defaultEntity.getDtUpdated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Date getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(Date dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public boolean isNew() {
		return StateChange.INSERT.equals(this.stateChange);
	}
	
	public StateChange getStateChange() {
		return stateChange;
	}

	public void setStateChange(StateChange stateChange) {
		this.stateChange = stateChange;
	}

}
