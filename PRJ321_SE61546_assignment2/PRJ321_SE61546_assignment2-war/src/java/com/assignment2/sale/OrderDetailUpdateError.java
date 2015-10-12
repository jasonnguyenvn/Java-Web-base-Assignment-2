/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sale;

import com.assignment2.common.ErrorBaseClass;

/**
 *
 * @author Hau
 */
public class OrderDetailUpdateError extends ErrorBaseClass {
    private String quantityLessThanOneErr;
    private String invalidQuantityValueErr;
    private String couldNotUpdateErr;
    

    public String getQuantityLessThanOneErr() {
        return quantityLessThanOneErr;
    }

    public void setQuantityLessThanOneErr(String quantityLessThanOneErr) {
        this.quantityLessThanOneErr = quantityLessThanOneErr;
        this.setRaisedErrors(true);
    }

    public String getInvalidQuantityValueErr() {
        return invalidQuantityValueErr;
    }

    public void setInvalidQuantityValueErr(String invalidQuantityValueErr) {
        this.invalidQuantityValueErr = invalidQuantityValueErr;
        this.setRaisedErrors(true);
    }

    public String getCouldNotUpdateErr() {
        return couldNotUpdateErr;
    }

    public void setCouldNotUpdateErr(String couldNotUpdateErr) {
        this.couldNotUpdateErr = couldNotUpdateErr;
        this.setRaisedErrors(true);
    }

    
    
    
    
    
    
}
