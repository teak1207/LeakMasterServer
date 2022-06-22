package com.devGong.leakmasterserver.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreInstallCheckModel {

    private char flag;
    private String serialNumber;
    private String modemNumber;
    private String dateTime;
    private String paraLen;
    private String debugMsg;
    private String chksum;

}
