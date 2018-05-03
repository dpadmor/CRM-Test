package com.agilemonkeys.test.crm.server.resource.util;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ModelMapperUtil extends ModelMapper {

    public <D,T> Page<D> map (Page<T> source, Class<D> destinationType) {
        return source.map(entity -> map(entity, destinationType));
    }

}


