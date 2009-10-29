package com.momentumsystems.eeoc.cts.domain;

import javax.persistence.*;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
@Entity
@Table(name = "SUBMITTAL_TYPE_LV")
public class SubmittalTypeLv {
    private int code;
    private String descTxt;
    private String shortDescTxt;

    @Id
    @Column(name = "CODE", nullable = false, length = 3)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "DESC_TXT", length = 50)
    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    @Basic
    @Column(name = "SHORT_DESC_TXT", length = 8)
    public String getShortDescTxt() {
        return shortDescTxt;
    }

    public void setShortDescTxt(String shortDescTxt) {
        this.shortDescTxt = shortDescTxt;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubmittalTypeLv that = (SubmittalTypeLv) o;

        if (code != that.code) return false;

        return true;
    }

    public int hashCode() {
        return code;
    }
}
