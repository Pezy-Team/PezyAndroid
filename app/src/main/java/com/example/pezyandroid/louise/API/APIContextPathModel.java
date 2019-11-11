package com.example.pezyandroid.louise.API;

import android.content.Context;

import com.example.pezyandroid.context.PezyApplication;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

/**
 * Created by anubi on 28/10/2560.
 */
public class APIContextPathModel {

    /**
     * Ex. http://www.domain-name.com:port
     */
    protected static SharePreferencesUtil sp;

    /**
     * IPADDRESS AND PORTS.
     */
    public static final String IP_ADDRESS = "103.40.146.174";//getIpAddress();
    public static final String PORTS = "8080";//getPORTS();
    public static final String PROJECT = "assettracking_api/rest/v1/";//getPROJECT();

    /**
     * IPADDRESS AND PORTS HIBERNATE.
     */
    public static final String IP_ADDRESS_HIBERNATE = "103.40.146.174";
    public static final String PORTS_HIBERNATE = "2534";
    public static final String PROJECT_HIBERNATE = "assettracking/api/v1/";

    /**
     * localhost on the emulator
     */
    public static String DOMAIN_NAME = getDomainName();

    /**
     * GETTER & SETTER AREA
     */

    public static String getIpAddress() {
        return SharePreferencesUtil.init("CONFIGURATION", Context.MODE_PRIVATE, PezyApplication.getContext()).getString("ip", "nothing");
    }

    public static String getPORTS() {
        return SharePreferencesUtil.init("CONFIGURATION", Context.MODE_PRIVATE, PezyApplication.getContext()).getString("port", "nothing");
    }

    public static String getPROJECT() {
        return SharePreferencesUtil.init("CONFIGURATION", Context.MODE_PRIVATE, PezyApplication.getContext()).getString("project", "nothing");
    }

    public static String getDomainName() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(IP_ADDRESS)
                .append(":")
                .append(PORTS)
                .append("/")
                .append(PROJECT);
        return sb.toString();
    }

    public static String getDomainNameHibernate() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://")
                .append(IP_ADDRESS_HIBERNATE)
                .append(":")
                .append(PORTS_HIBERNATE)
                .append("/")
                .append(PROJECT_HIBERNATE);
        return sb.toString();
    }
}
