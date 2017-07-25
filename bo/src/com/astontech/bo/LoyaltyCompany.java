package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class LoyaltyCompany extends BaseBO{
    //region PARAMETERS

    private int LoyaltyCompanyId;
    private String CompanyName;
    private EntityType LoyaltyCompanyType;

    //endregion

    //region CONSTRUCTORS

    public LoyaltyCompany(){
        this.LoyaltyCompanyType = new EntityType();
    }

    public LoyaltyCompany(String companyName){
        this.LoyaltyCompanyType = new EntityType();
        this.setCompanyName(companyName);
    }

    //endregion

    //region GETTERS/SETTERS

    public int getLoyaltyCompanyId() {
        return LoyaltyCompanyId;
    }

    public void setLoyaltyCompanyId(int loyaltyCompanyId) {
        LoyaltyCompanyId = loyaltyCompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public EntityType getLoyaltyCompanyType() {
        return LoyaltyCompanyType;
    }

    public void setLoyaltyCompanyType(EntityType loyaltyCompanyType) {
        LoyaltyCompanyType = loyaltyCompanyType;
    }

    //endregion

}
