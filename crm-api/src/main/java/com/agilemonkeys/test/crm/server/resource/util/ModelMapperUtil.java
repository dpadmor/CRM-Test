package com.agilemonkeys.test.crm.server.resource.util;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ModelMapperUtil extends ModelMapper {

    public ModelMapperUtil() {
        super();
        super.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    }

    public <D,T> Page<D> map (Page<T> source, Class<D> destinationType) {
        return source.map(entity -> map(entity, destinationType));
    }

}


