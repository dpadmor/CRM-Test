package com.agilemonkeys.test.crm.server.resource.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"photo"})
public class Customer {

    public Customer (String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Customer () {};

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Lob
    private byte [] photo = null;

    private String idphoto;

    private String photoName;

    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column()
    @LastModifiedDate
    private Date updatedAt;

    @Column()
    @LastModifiedBy
    private String updatedBy;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getCreatedAt () {
        return createdAt;
    }

    public Date getUpdatedAt () {
        return updatedAt;
    }

    public String getCreatedBy() { return createdBy; }

    public String getUpdatedBy () { return updatedBy; }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(String idphoto) {
        this.idphoto = idphoto;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", idphoto='" + idphoto + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
