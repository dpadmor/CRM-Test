package com.agilemonkeys.test.crm.server.resource.repository;

import com.agilemonkeys.test.crm.server.resource.model.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {


    @Query("SELECT customer FROM Customer customer where customer.idphoto = :photoId")
    Customer findByPhotoId(@Param("photoId") String photoId);
}
