package com.neuronx.modbus;

import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.procimg.SimpleInputRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ModbusService {

    private final ModbusTCPMaster modbusTCPMaster;

    public void readModbusData() {
        ModbusData modbusData = new ModbusData(null);
        System.out.println(modbusData.getProductName());
        System.out.println(modbusData.getRealTimeWeight());
        Register[] registers = convertIntArrayToRegisters(modbusData.values);

        try {
//            Register[] registers = modbusTCPMaster.readMultipleRegisters(1, 0, 78); // Adjust slave ID as needed

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
    public SimpleInputRegister[] convertIntArrayToRegisters(int[] intArray) {
        System.out.println(Arrays.toString(intArray));
        if (intArray == null) {
            return null;
        }

        SimpleInputRegister[] registers = new SimpleInputRegister[intArray.length / 2];

        for (int i = 0; i < intArray.length; i += 2) {
            int value1 = intArray[i];
            int value2 = (i + 1 < intArray.length) ? intArray[i + 1] : 0;

            registers[i / 2] = new SimpleInputRegister((byte) value1, (byte) value2);
        }

        return registers;
    }
}