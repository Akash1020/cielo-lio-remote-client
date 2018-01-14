package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalJsonDeserialize extends StdDeserializer<BigDecimal> {

    private static final int SCALE = 2;

    protected BigDecimalJsonDeserialize() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jsonParser != null) {
            Long value = jsonParser.readValueAs(Long.class);
            if (value != null) {
                return BigDecimal.valueOf(value, SCALE);
            }
        }
        return null;
    }
}
