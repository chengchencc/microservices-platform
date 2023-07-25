package com.central.jpa.jpa;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ludan.generator.entity.validation.AbstractValidation;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.io.IOException;
//
///**
// * @author: chengchen
// * @create: 2021-01-19 09:46
// **/
//@Converter
//public class JpaJsonConverter implements AttributeConverter<AbstractValidation, String> {
//    private final static ObjectMapper objectMapper = new ObjectMapper();
//    static {
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }
//
//    @Override
//    public String convertToDatabaseColumn(AbstractValidation meta) {
//        try {
//            return objectMapper.writeValueAsString(meta);
//        } catch (JsonProcessingException ex) {
//            throw new JpaConvertException(ex);
//        }
//    }
//
//    @Override
//    public AbstractValidation convertToEntityAttribute(String dbData) {
//        try {
//            AbstractValidation typedObject = objectMapper.readValue(dbData, AbstractValidation.class);
//            AbstractValidation o =(AbstractValidation) objectMapper.readValue(dbData, typedObject.getClassType());
//            return o;
//        } catch (IOException ex) {
//             throw new JpaConvertException("Unexpected IOEx decoding json from database: " + dbData,ex);
//        }
//    }
//}
