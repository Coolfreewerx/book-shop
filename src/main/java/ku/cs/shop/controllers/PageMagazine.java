package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PageMagazine {
    @FXML
    public void handleCartoonBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือการ์ตูน
        try {
            com.github.saacsos.FXRouter.goTo("pageBookCartoon");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของหนังสือการ์ตูนไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleMagazineButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า Magazine
        try {
            com.github.saacsos.FXRouter.goTo("pageMagazine");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของ Magazine ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
