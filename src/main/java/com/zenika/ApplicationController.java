package com.zenika;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by acogoluegnes on 08/07/15.
 */
@RestController
public class ApplicationController {

    @RequestMapping("/entities/{id}")
    public String get(@PathVariable String id) {
        randomLatency();
        return "Requested entity: "+id;
    }

    @RequestMapping("/entities")
    public List<String> select() {
        randomLatency();
        return Arrays.asList("one","two","three");
    }


    Random random = new Random();

    public void randomLatency() {
        double v = random.nextDouble() * 100;
        try {
            Thread.sleep((long)v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
