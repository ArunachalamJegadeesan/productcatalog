package com.example.catalog.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductCatalog{


    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUsoc() {
        return usoc;
    }

    public void setUsoc(String usoc) {
        this.usoc = usoc;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }


    @Size(min=5, max=100)
    private String productName;


    @Size(min=5, max=10)
    private String usoc;


    private String regionCode;


    @Size(min=2, max=2)
    private String stateCode;

    @Size (min=1,max=1)
    private String available ;


    public ProductCatalog(){

    }

    public ProductCatalog(String productName, String usoc,
                          String regionCode, String stateCode, String available){
        this.productName=productName;
        this.usoc=usoc;
        this.regionCode=regionCode;
        this.stateCode=stateCode;
        this.available=available;
    }

    


}






