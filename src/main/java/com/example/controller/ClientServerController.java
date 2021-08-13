package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping(path = "api")
@RestController
public class ClientServerController {

    public static final String SERVER_URI_BID = "http://localhost:8080/api/bid";
    public static final String SERVER_URI_AUCTION = "http://localhost:8080/api/auction";
    private MethodService methodService;

    Map<Integer, List<Integer>> portNumberToAuction = new HashMap(); //AuctionId-Portnumber


    private static final AtomicInteger generateAuctionId = new AtomicInteger(0);
    private static final AtomicInteger generateBidId = new AtomicInteger(0);
    private static final AtomicInteger portNumber = new AtomicInteger(2370);

    private HashMap<Integer, Thread> portThread = new HashMap<>();

    private RestTemplate restTemplate = new RestTemplate();


    @Autowired
    public ClientServerController(MethodService methodService) {
        this.methodService = methodService;

        int initiatePort = portNumber.getAndIncrement();
        this.methodService.initiateFirstServer(initiatePort);
        List<Integer> ports = new ArrayList<>();
        ports.add(initiatePort);
        portNumberToAuction.put(-1, ports);
/*
        int initiatePort2 = portNumber.getAndIncrement();
        this.methodService.initiateFirstServer(initiatePort2);
        List<Integer> ports2 = new ArrayList<>();
        ports.add(initiatePort2);
        portNumberToAuction.put(-1, ports2);*/
    }


    @GetMapping(path = "/bid")
    public Iterable<BidTable> getAllBids() {
        return restTemplate.getForObject(SERVER_URI_BID, Iterable.class);
    }

    /*@GetMapping(path = "/auction")
    public @ResponseBody
    Iterable<Integer> getAllAuctions() throws ExecutionException, InterruptedException {
        // This returns a JSON or XML with the users
        return restTemplate.getForObject(SERVER_URI_AUCTION, Iterable.class);
    }*/

    @GetMapping(path = "/auction")
    public @ResponseBody
    Iterable<AuctionTable> getAllAuctions() {
        //System.out.println(auctionId2);
        // This returns a JSON or XML with the users

        System.out.println(portNumberToAuction.values().toArray().length);
        Random generator = new Random();
        Object[] values = portNumberToAuction.values().toArray();
        List<Integer> randomPortList = (List<Integer>) values[generator.nextInt(values.length)];
        Integer randomPort = randomPortList.get(0);
        //int port1 = portNumberToAuction.get(auctionId).get(0);
        //int port2 = portNumberToAuction.get(auctionId).get(0);
        //if (port1 == port2)
        System.out.println(methodService.getAuctionURIforPort(randomPort));
        return restTemplate.getForObject(methodService.getAuctionURIforPort(randomPort), Iterable.class);
        //System.out.println("Port1 and Port2 differs in return");
        //throw new Exception("Diffs in ports");
    }

    @GetMapping(path = "/auction/{auctionId}")
    public @ResponseBody
    Iterable<BidTable> getAllBidsForAuction(@PathVariable Integer auctionId) throws ExecutionException, InterruptedException {
        System.out.println(portNumberToAuction.get(auctionId).get(0));
        int port1 = portNumberToAuction.get(auctionId).get(0);
        System.out.println(methodService.getBidURIforPort(port1));
        return restTemplate.getForObject(methodService.getBidURIforPort(port1), Iterable.class);
    }

    @PostMapping(path = "/auction/setting")
    public void setServersAuctionId(int port1, int port2, int auctionId) {
        restTemplate.postForObject(methodService.getAuctionURIforPort(port1) + "/setting", auctionId, Integer.class);
        restTemplate.postForObject(methodService.getAuctionURIforPort(port2) + "/setting", auctionId, Integer.class);
    }

    @GetMapping(path = "/server/started")
    public void serverHasStarted(@RequestBody Integer auctionId) {
        int port = portNumberToAuction.get(auctionId).get(0);
        //portThread.get(port).notify();
        System.out.println("Incoming confirmation that server: " + port + "has started");
    }

    @PostMapping(path = "/auction2")
    public void addNewAuction2(@RequestBody Integer auctioneer) throws InterruptedException {
        Integer port = portNumber.getAndIncrement();
        Integer auctionId = generateAuctionId.getAndIncrement();
        PostAuctionTemplate postAuctionTemplate = new PostAuctionTemplate(auctionId, auctioneer);
        restTemplate.postForObject(methodService.getAuctionURIforPort(8080), postAuctionTemplate, PostAuctionTemplate.class);
    }

    @PostMapping(path = "/auction")
    public void addNewAuction(@RequestBody Integer auctioneer) throws InterruptedException {
        Integer port1 = portNumber.getAndIncrement();
        Integer port2 = portNumber.getAndIncrement();
        Integer auctionId = generateAuctionId.getAndIncrement();

        while (!methodService.createNewServer(port1)) {
            port1 = portNumber.getAndIncrement();
        }
        while (!methodService.createNewServer(port2)) {
            port2 = portNumber.getAndIncrement();
        }
        List<Integer> ports = new ArrayList<>();
        ports.add(port1);
        ports.add(port2);
        portNumberToAuction.put(auctionId, ports);

        //setServersAuctionId(port1,port2,auctionId);
        System.out.println("URL IS" + methodService.getAuctionURIforPort(port1));
        PostAuctionTemplate postAuctionTemplate = new PostAuctionTemplate(auctionId, auctioneer);

        while (true) {
            try {
                restTemplate.postForObject(methodService.getAuctionURIforPort(port1), postAuctionTemplate, PostAuctionTemplate.class);
                restTemplate.postForObject(methodService.getAuctionURIforPort(port2), postAuctionTemplate, PostAuctionTemplate.class);
                System.out.println("POST worked");
                return;
            } catch (Exception e) {
                System.out.println("POST didnt work");
                Thread.sleep(300);
                e.printStackTrace();
            }
        }
    }

    @PostMapping(path = "/bid")
    public void addNewBid(@RequestBody PostBidTemplate postBidTemplate) throws ExecutionException, InterruptedException {
        restTemplate.postForObject(SERVER_URI_BID, postBidTemplate, PostBidTemplate.class);
    }

    @GetMapping(path = "/generate")
    public @ResponseBody
    Integer getBidId() {
        return generateBidId.getAndIncrement();
    }
}