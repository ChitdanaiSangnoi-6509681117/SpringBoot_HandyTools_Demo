package com.dev.chitdanai.handytools;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Storage {

    private @Id
    @GeneratedValue
    long id;
    private String toolDetail;
    private String ownerName;
    private String locationName;
    private Boolean borrowed;
    private String borrowerName;

    Storage(){}

    public Storage(String toolDetail, String ownerName, String locationName, Boolean borrowed,
            String borrowerName) {
        this.toolDetail = toolDetail;
        this.ownerName = ownerName;
        this.locationName = locationName;
        this.borrowed = borrowed;
        this.borrowerName = borrowerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToolDetail() {
        return toolDetail;
    }

    public void setToolDetail(String toolDetail) {
        this.toolDetail = toolDetail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((toolDetail == null) ? 0 : toolDetail.hashCode());
        result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
        result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
        result = prime * result + ((borrowed == null) ? 0 : borrowed.hashCode());
        result = prime * result + ((borrowerName == null) ? 0 : borrowerName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Storage other = (Storage) obj;
        if (id != other.id)
            return false;
        if (toolDetail == null) {
            if (other.toolDetail != null)
                return false;
        } else if (!toolDetail.equals(other.toolDetail))
            return false;
        if (ownerName == null) {
            if (other.ownerName != null)
                return false;
        } else if (!ownerName.equals(other.ownerName))
            return false;
        if (locationName == null) {
            if (other.locationName != null)
                return false;
        } else if (!locationName.equals(other.locationName))
            return false;
        if (borrowed == null) {
            if (other.borrowed != null)
                return false;
        } else if (!borrowed.equals(other.borrowed))
            return false;
        if (borrowerName == null) {
            if (other.borrowerName != null)
                return false;
        } else if (!borrowerName.equals(other.borrowerName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Storage [id=" + id + ", toolDetail=" + toolDetail + ", ownerName=" + ownerName + ", locationName="
                + locationName + ", borrowed=" + borrowed + ", borrowerName=" + borrowerName + "]";
    } 
}
