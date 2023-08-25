package com.webbank.springsecurity.model;

import java.util.List;

public enum AuthorityEnum {
    VIEW_ACCOUNT("VIEW_ACCOUNT"),
    VIEW_CARDS("VIEW_CARDS"),
    VIEW_LOANS("VIEW_LOANS"),
    VIEW_BALANCE("VIEW_BALANCE"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    AuthorityEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getUserRoleSubstring(){
        return AuthorityEnum.ROLE_USER.name.substring("ROLE_".length());
    }

    public static String getAdminRoleSubstring(){
        return AuthorityEnum.ROLE_ADMIN.name.substring("ROLE_".length());
    }

    public static List<AuthorityEnum> getAllAuthorities(){
        return List.of(
            VIEW_ACCOUNT, VIEW_CARDS, VIEW_LOANS, VIEW_BALANCE, ROLE_USER, ROLE_ADMIN
        );
    }

}