package com.agilemonkeys.test.crm.commons.exception;

public class EntityNotFoundCRMException extends Exception{

    private String idCustomer;

    public EntityNotFoundCRMException(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
}
