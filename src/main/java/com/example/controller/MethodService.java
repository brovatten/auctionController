package com.example.controller;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MethodService {


    public Boolean createNewServer(int port){
        String portCommand = "-Dserver.port=" + port;
        String[] args = new String[] {"java", "-jar", portCommand, "-Dspring.profiles.active=production", "server5.jar"};
        try {
            Runtime.getRuntime().exec(args);
        } catch (IOException e) {
            System.out.println("failed to create at port:" + port);
            e.printStackTrace();
            return false;
        }
        System.out.println("new Server created at port:" + port);
        return true;
    }

    public void initiateFirstServer(int initiatePort){
        createNewServer(initiatePort);
    }

    public String getBidURIforPort(int port){
        return "http://localhost:" + port + "/api/bid";
    }

    public String getAuctionURIforPort(int port){
        return "http://localhost:" + port + "/api/auction";
    }
}
