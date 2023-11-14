package com.neuronx.modbus;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Component;

@Component
public class ModbusScheduler {

    @Autowired
    private ModbusService modbusService;

    @Scheduled(fixedDelay = 5000) // adjust the delay as needed
    public void readModbusDataScheduled() {
        modbusService.readModbusData();
    }
}
