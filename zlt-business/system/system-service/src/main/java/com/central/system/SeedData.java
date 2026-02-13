package com.central.system;

import com.central.system.dictionary.SysDictDto;
import com.central.system.dictionary.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.printf("seeding data...");

        //TODO:核心逻辑

        System.out.printf("seed data complete");
    }
}
