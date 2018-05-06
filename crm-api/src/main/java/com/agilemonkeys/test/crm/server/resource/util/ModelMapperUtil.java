package com.agilemonkeys.test.crm.server.resource.util;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ModelMapperUtil extends ModelMapper {

    public ModelMapperUtil() {
        super();
        super.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        /*super.addMappings(new PropertyMap<CustomerDto, Customer>() {
            @Override
            protected void configure() {
                skip(source.getCreatedAt(), destination.getCreatedAt());
                skip(source.getCreatedBy(), destination.getCreatedBy());
                skip(source.getUpdatedAt(), destination.getUpdatedAt());
                skip(source.getUpdatedBy(), destination.getUpdatedBy());

            }
        });*/
    }

    public <D,T> Page<D> map (Page<T> source, Class<D> destinationType) {
        return source.map(entity -> map(entity, destinationType));
    }

}


