package org.frekele.cielo.lio.remote.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.BigDecimalJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.BigDecimalJsonSerialize;
import org.frekele.cielo.lio.remote.client.converter.OffsetDateTimeJsonDeserialize;
import org.frekele.cielo.lio.remote.client.converter.OffsetDateTimeJsonSerialize;
import org.frekele.cielo.lio.remote.client.core.CieloLioEntity;
import org.frekele.cielo.lio.remote.client.enumeration.StatusCieloLioEnum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderEntity implements CieloLioEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    private String number;

    private String reference;

    private StatusCieloLioEnum status;

    private String notes;

    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal price;

    @JsonDeserialize(using = BigDecimalJsonDeserialize.class)
    @JsonSerialize(using = BigDecimalJsonSerialize.class)
    private BigDecimal remaining;

    @JsonProperty("order_type")
    private String orderType;

    private String merchant;

    @JsonProperty("source_id")
    private String sourceId;

    private List<OrderItemEntity> items;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = OffsetDateTimeJsonDeserialize.class)
    @JsonSerialize(using = OffsetDateTimeJsonSerialize.class)
    private OffsetDateTime updatedAt;

    private List<OrderTransactionEntity> transactions;

    public OrderEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StatusCieloLioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusCieloLioEnum status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderTransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<OrderTransactionEntity> transactions) {
        this.transactions = transactions;
    }
}