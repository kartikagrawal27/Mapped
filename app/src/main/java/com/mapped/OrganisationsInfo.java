package com.mapped;

/**
 * Created by Pegasus on 7/28/16.
 */
public class OrganisationsInfo {
    private String orgname;

    public String getOrglogolink() {
        return orglogolink;
    }

    public String getOrgname() {
        return orgname;
    }

    private String orglogolink;


    public OrganisationsInfo() {
    }

    public OrganisationsInfo(String orgname, String orglogolink) {
        this.orgname = orgname;
        this.orglogolink = orglogolink;
    }






}
