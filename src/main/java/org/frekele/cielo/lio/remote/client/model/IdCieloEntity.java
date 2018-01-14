package org.frekele.cielo.lio.remote.client.model;

import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author leandro.freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IdCieloEntity implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    public IdCieloEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
