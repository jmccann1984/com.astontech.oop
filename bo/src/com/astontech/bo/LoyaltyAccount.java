package com.astontech.bo;

/**
 * Created by Joshua.McCann on 6/22/2017.
 */
public class LoyaltyAccount extends BaseBO{
    //region PARAMETERS

    private int LoyaltyAccountId;
    private LoyaltyCompany LoyaltyCompanyAccount;
    private Employee EmployeeLoyaltyAccount;
    private String MemberNumber;

    //endregion

    //region CONSTRUCTORS

    public LoyaltyAccount(){
        LoyaltyCompanyAccount = new LoyaltyCompany();
        EmployeeLoyaltyAccount = new Employee();
    }

    public LoyaltyAccount(String memberNumber){
        LoyaltyCompanyAccount = new LoyaltyCompany();
        EmployeeLoyaltyAccount = new Employee();
        this.setMemberNumber(memberNumber);
    }

    //endregion

    //region GETTERS / SETTERS

    public int getLoyaltyAccountId() {
        return LoyaltyAccountId;
    }

    public void setLoyaltyAccountId(int loyaltyAccountId) {
        LoyaltyAccountId = loyaltyAccountId;
    }

    public LoyaltyCompany getLoyaltyCompanyAccount() {
        return LoyaltyCompanyAccount;
    }

    public void setLoyaltyCompanyAccount(LoyaltyCompany loyaltyCompanyAccount) {
        LoyaltyCompanyAccount = loyaltyCompanyAccount;
    }

    public Employee getEmployeeLoyaltyAccount() {
        return EmployeeLoyaltyAccount;
    }

    public void setEmployeeLoyaltyAccount(Employee employeeLoyaltyAccount) {
        EmployeeLoyaltyAccount = employeeLoyaltyAccount;
    }

    public String getMemberNumber() {
        return MemberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        MemberNumber = memberNumber;
    }

    //endregion
}
