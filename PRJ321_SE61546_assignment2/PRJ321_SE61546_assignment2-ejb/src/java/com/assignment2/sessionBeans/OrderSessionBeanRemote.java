/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Hau
 */
@Remote
public interface OrderSessionBeanRemote {

    List searchFromDateToDate(Date fromDate, Date toDate);
    
}
