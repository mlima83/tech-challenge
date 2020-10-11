package com.pixeon.base.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class DefaultEntityDto {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtCreated;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date dtUpdated;

    private boolean active = true;
    
    private long version;
    
    public DefaultEntityDto() {}

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
}
