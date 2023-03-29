package com.isikodon.kafka.stream;

import com.isikodon.kafka.schema.CustomerData;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

/**
 * Declares a Custom Serdes Wrapper that allows us to configure the Serializers/Deserializers
 */
public class CustomerDataSerde extends Serdes.WrapperSerde<CustomerData> {
    public CustomerDataSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>());
        this.deserializer().configure(
                Map.of(
                        JsonDeserializer.TRUSTED_PACKAGES, "com.isikodon.kafka.schema"
                ),
                false
        );
    }
}
