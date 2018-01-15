package org.frekele.cielo.lio.remote.client.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.Arrays;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlType
@XmlEnum(String.class)
public enum StatusCieloLioEnum {

    DRAFT("DRAFT"),

    ENTERED("ENTERED"),

    CANCELED("CANCELED"),

    PAID("PAID"),

    APPROVED("APPROVED"),

    REJECTED("REJECTED"),

    RE_ENTERED("RE_ENTERED"),

    CLOSED("CLOSED");

    private String value;

    private StatusCieloLioEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static StatusCieloLioEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (StatusCieloLioEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<StatusCieloLioEnum> getAll() {
        return Arrays.asList(StatusCieloLioEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}