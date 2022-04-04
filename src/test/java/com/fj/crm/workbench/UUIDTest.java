package com.fj.crm.workbench;

import org.junit.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void testUUID(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
