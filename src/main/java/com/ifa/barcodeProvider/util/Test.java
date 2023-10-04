package com.ifa.barcodeProvider.util;

import com.ifa.barcodeProvider.controller.UpdateController;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Test {
    public  static List<String> data = new ArrayList<>();
    public  static String data1;
    private static SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
    private static UpdateController updateController;
    public static void flushSerialPort() throws SerialPortException {
        serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
        serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
        serialPort.closePort();
    }
    @Autowired
    public void setUptakeController(UpdateController updateController) {
        this.updateController = updateController;
    }


//    public static UptakeController uptakeController;

    public static void getScan() {

        if(serialPort.isOpened()) {
            try {
                flushSerialPort();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
        //Передаём в конструктор имя порта
//        serialPort = new SerialPort("COM3");
        try {
            //Открываем порт
            serialPort.openPort();



            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            serialPort.writeString("Get data");

        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
//        return data;
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0){
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
//                    data.add(serialPort.readString(event.getEventValue()));
                    data1 = serialPort.readString(event.getEventValue());
//                    System.out.println(data1);
//                    uptakeController.setCode(data1);
                      updateController.setCode(data1);
//                    data.forEach(System.out::println);
//                    uptakeController.setCode(data);
                    //И снова отправляем запрос
                    serialPort.writeString("Get data");
//                    flushSerialPort();


//                    serialPort.closePort();

                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
