package com.neuronx.modbus;

import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.net.ModbusTCPListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ModbusConfig {

    @Value("${modbus.host}")
    private String host;

    @Value("${modbus.port}")
    private int port;

    @Bean
    public ModbusTCPMaster modbusTCPMaster() {
        ModbusTCPMaster tcpListener = new ModbusTCPMaster(host, port);
        try {
            tcpListener.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tcpListener;
    }
}

