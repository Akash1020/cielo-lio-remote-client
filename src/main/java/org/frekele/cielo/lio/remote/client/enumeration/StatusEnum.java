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
public enum StatusEnum {

    DRAFT("DRAFT"),

    ENTERED("ENTERED"),

    CANCELED("CANCELED"),

    PAID("PAID"),

    APPROVED("APPROVED"),

    REJECTED("REJECTED"),

    RE_ENTERED("RE_ENTERED"),

    CLOSED("CLOSED");

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @XmlValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
        if (value != null && value.length() != 0) {
            for (StatusEnum obj : getAll()) {
                if (obj.value.equals(value)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public static List<StatusEnum> getAll() {
        return Arrays.asList(StatusEnum.values());
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
