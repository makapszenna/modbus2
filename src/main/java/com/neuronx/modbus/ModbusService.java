package com.neuronx.modbus;

import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.procimg.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ModbusService {

    private final ModbusTCPMaster modbusTCPMaster;

    public void readModbusData() {
        try {
            Register[] registers = modbusTCPMaster.readMultipleRegisters(1, 0, 78); // Adjust slave ID as needed

            // Process the response data
            int[] values = new int[registers.length];
            for (int i = 0; i < registers.length; i++) {
                values[i] = registers[i].toUnsignedShort();
            }

            System.out.println("Modbus Data: " + Arrays.toString(values));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}