/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "feedProdDetail")
@RequestScoped

public class FeedProdDetail {

    @Inject
    private FeedProdList fpd;
    
    
    public FeedProdDetail() {
        
    }
    
}
