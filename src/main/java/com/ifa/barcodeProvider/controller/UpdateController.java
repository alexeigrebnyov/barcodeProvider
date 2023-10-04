package com.ifa.barcodeProvider.controller;


import com.ifa.barcodeProvider.util.DesktopApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ifa.barcodeProvider.util.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    String code;
    @Autowired
    public UpdateController() {
    }

//    @GetMapping("/time")
//    public ResponseEntity<List<Long>> getTime() {
//        List<Long> times =new ArrayList<>();
//        times.add(System.currentTimeMillis());
//        return ResponseEntity.ok(times);
//    }
    @GetMapping("/code")
    public ResponseEntity<String> getCode() {
//        List<String> codes =new ArrayList<>();
//        setCode(null);
        Test.getScan();
//        codes.add(code);

        return ResponseEntity.ok(code);

    }
    @GetMapping("/{code}")
    public void setCode1(@PathVariable("code") String code) {
        setCode(code);
    }

    @GetMapping("/setcode")
    public void setCodeNull() {
        setCode(null);
    }

    @GetMapping("/openGormonu")
    public void openGormonu() {
        File file = new File("//192.168.7.100/ifa/ifaList/GormonuReport.xlsx");
        DesktopApi.open(file);
    }

    @GetMapping("/openImm")
    public void openImm() {
        File file = new File("//192.168.7.100/ifa/ifaList/ImmReport.xlsx");
        DesktopApi.open(file);
    }

    @GetMapping("/openTorch")
    public void openTorch() {
        File file = new File("//192.168.7.100/ifa/ifaList/TorchReport.xlsx");
        DesktopApi.open(file);
    }
    @GetMapping("/openVich")
    public void openVich() {
        File file = new File("//192.168.7.100/ifa/ifaList/report.xlsx");
        DesktopApi.open(file);
    }

    @GetMapping("/openProof")
    public void openProof() {
        File file = new File("//192.168.7.100/ifa/ifaList/ProofReport.xlsx");
        DesktopApi.open(file);
    }
    @GetMapping("/run")
    public void run() {
        File file = new File("C:/IFA.jar");
        DesktopApi.open(file);
    }
    @GetMapping("/runTORCH")
    public void runTorch() {
        File file = new File("C:/IFAtorch.jar");
        DesktopApi.open(file);
    }
    @GetMapping("/runGorm")
    public void runGorm() {
        File file = new File("C:/IFAGorm.jar");
        DesktopApi.open(file);
    }
    @GetMapping("/runImmulite")
    public void runImmulite() {
        File file = new File("C:/IFAImmulite.jar");
        DesktopApi.open(file);
    }
    @GetMapping("/check")
    public void check() {
        System.out.println("connection ok");    }


    public void setCode(String code) {
        this.code = code;
    }
}
