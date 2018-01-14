package org.frekele.cielo.lio.remote.client.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeJsonSerialize extends StdSerializer<OffsetDateTime> {

    protected OffsetDateTimeJsonSerialize() {
        super(OffsetDateTime.class);
    }

    @Override
    public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        if (value != null) {
            jsonGenerator.writeString(value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
    }
}
