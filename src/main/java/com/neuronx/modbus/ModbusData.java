package com.neuronx.modbus;

import com.ghgande.j2mod.modbus.procimg.Register;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ModbusData {

    private final int[] values;

    public ModbusData(Register[] registers) {
        this.values = registers != null ?
                Arrays.stream(registers)
                        .map(obj -> (Register) obj)
                        .map(Register::toUnsignedShort)
                        .toArray() :
                new int[0];  // or handle the case when 'registers' is null
    }


    public float getRealTimeWeight() {
        return convertToFloat(values[0]);
    }

    public int getAcceptanceValue() {
        return values[1];
    }

    public int getCounter() {
        return values[2];
    }

    public int getReserved1() {
        return values[3];
    }

    public int getReserved2() {
        return values[4];
    }

    public String getProductName() {
        byte[] productNameBytes = Arrays.copyOfRange(getBytes(values, 5, 20), 0, 16);
        return new String(productNameBytes).trim();
    }

    public float getPreSetWeight() {
        return convertToFloat(values[20]);
    }

    public float getUpperLimit() {
        return convertToFloat(values[21]);
    }

    public float getLowerLimit() {
        return convertToFloat(values[22]);
    }

    public int getProductBatchNumber() {
        return values[23];
    }

    public int getSensitivity() {
        return values[24];
    }

    public int getSensitivityX() {
        return values[25];
    }

    public int getSensitivityY() {
        return values[26];
    }

    public float getPhaseAngle() {
        return convertToFloat(values[27]);
    }

    public int getCheckweigherStatus() {
        return values[28];
    }

    public int getCommunicationPulse() {
        return values[29];
    }

    public float getCurrentWeight() {
        return convertToFloat(values[30]);
    }

    public int getMalfunctionNumber() {
        return values[31];
    }

    public int getCurrentXValue() {
        return values[32];
    }

    public int getCurrentYValue() {
        return values[33];
    }

    public int getQtyOfUnderweightProduct() {
        return values[34];
    }

    public float getWeightOfUnderweightProduct() {
        return convertToFloat(values[35]);
    }

    public float getAverageWeightOfUnderweightProduct() {
        return convertToFloat(values[36]);
    }

    public float getUnderweightPercentage() {
        return convertToFloat(values[37]);
    }

    public int getQtyOfQualifiedProducts() {
        return values[38];
    }

    public float getWeightOfQualifiedProducts() {
        return convertToFloat(values[39]);
    }

    public float getAverageWeightOfQualifiedProducts() {
        return convertToFloat(values[40]);
    }

    public float getPercentageOfQualifiedProducts() {
        return convertToFloat(values[41]);
    }

    public int getQtyOfOverweightProduct() {
        return values[42];
    }

    public float getWeightOfOverweightProduct() {
        return convertToFloat(values[43]);
    }

    public float getAverageWeightOfOverweightProduct() {
        return convertToFloat(values[44]);
    }

    public float getPercentageOfOverweightProduct() {
        return convertToFloat(values[45]);
    }

    public int getTotalQty() {
        return values[46];
    }

    public float getTotalWeight() {
        return convertToFloat(values[47]);
    }

    public float getTotalAverage() {
        return convertToFloat(values[48]);
    }

    public int getAbnormalWeighingQty() {
        return values[49];
    }

    public float getPercentageAbnormalWeighing() {
        return convertToFloat(values[50]);
    }

    public int getMdDetectedQty() {
        return values[51];
    }

    public float getPercentageMdDetectedQty() {
        return convertToFloat(values[52]);
    }

    public int getReserved35() {
        return values[53];
    }

    // Customized Data Area
    public int getCustomizedData1() {
        return values[54];
    }

    public int getCustomizedData2() {
        return values[55];
    }

    public int getCustomizedData3() {
        return values[56];
    }

    // Control Area
    public int getControlSetting() {
        return values[57];
    }

    public int getStartStopCommand() {
        return values[58];
    }

    public int getProductBatchNumberForRemoteControl() {
        return values[59];
    }

    public int getReserved60() {
        return values[60];
    }

    public int getReserved61() {
        return values[61];
    }

    public int getReserved62() {
        return values[62];
    }

    public int getReserved63() {
        return values[63];
    }

    public int getReserved64() {
        return values[64];
    }

    public int getReserved65() {
        return values[65];
    }

    public int getReserved66() {
        return values[66];
    }

    public int getReserved67() {
        return values[67];
    }

    public int getReserved68() {
        return values[68];
    }

    public int getReserved69() {
        return values[69];
    }

    public int getReserved70() {
        return values[70];
    }

    public int getReserved71() {
        return values[71];
    }

    public int getReserved72() {
        return values[72];
    }

    public int getReserved73() {
        return values[73];
    }

    public int getReserved74() {
        return values[74];
    }

    public int getReserved75() {
        return values[75];
    }

    public int getReserved76() {
        return values[76];
    }

    public int getReserved77() {
        return values[77];
    }

    public int getReserved78() {
        return values[78];
    }

    @Override
    public String toString() {
        return "ModbusData{" +
                "values=" + Arrays.toString(values) +
                '}';
    }

    private float convertToFloat(int value) {
        byte[] bytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    private byte[] getBytes(int[] values, int startIdx, int length) {
        byte[] result = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            result[i * 2] = (byte) (values[startIdx + i] >> 8);
            result[i * 2 + 1] = (byte) values[startIdx + i];
        }
        return result;
    }
}
