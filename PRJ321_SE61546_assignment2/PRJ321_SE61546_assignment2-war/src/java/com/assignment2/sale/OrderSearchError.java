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
public class OrderSearchError extends ErrorBaseClass {
    
    
    String invalidFromDateFormatErr;
    String invalidToDateFormatErr;
    String toDateEalierThanFromDateErr;

    public String getInvalidFromDateFormatErr() {
        return invalidFromDateFormatErr;
    }

    public void setInvalidFromDateFormatErr(String invalidDateFormatErr) {
        this.invalidFromDateFormatErr = invalidDateFormatErr;
        this.setRaisedErrors(true);
    }

    public String getInvalidToDateFormatErr() {
        return invalidToDateFormatErr;
    }

    public void setInvalidToDateFormatErr(String invalidToDateFormatErr) {
        this.invalidToDateFormatErr = invalidToDateFormatErr;
        this.setRaisedErrors(true);
    }

    public String getToDateEalierThanFromDateErr() {
        return toDateEalierThanFromDateErr;
    }

    public void setToDateEalierThanFromDateErr(String toDateEalierThanFromDateErr) {
        this.toDateEalierThanFromDateErr = toDateEalierThanFromDateErr;
        this.setRaisedErrors(true);
    }
    
    
    
    
}
