package agent.datasense;

import agent.transport.TransportHandlerException;
import agent.transport.TransportHandlerException;
import agent.transport.mqtt.ConnectedCupMQttTransportHandler;
/**
 * Created by anushkas on 5/23/16.
 */
public class Main {
    private static ConnectedCupMQttTransportHandler connectedCupMQttTransportHandler;

    public  Main() {
        connectedCupMQttTransportHandler = ConnectedCupMQttTransportHandler.getInstance();
    }

    public static void main(String[] args) {
        connectedCupMQttTransportHandler = ConnectedCupMQttTransportHandler.getInstance();
        String deviceId ="1ibeakzjhd469";
        String tenantDomain = "carbon.super";
        String deviceOwner = "admin";
        String payload = "20";
//        payload = " {\"event\": {\"metaData\": {\"owner\": \"" + deviceOwner +
//                "\", \"type\": \"connectedlap\",\"deviceId\": " +
//                "\"" + deviceId + "\",\"timestamp\": " + System.currentTimeMillis() +
//                "},\"payloadData\": { \"batteryusage\": " + Float.parseFloat(payload) + ", \"chargerpluggedin\": \"true\", \"cpuusage\": " + Float.parseFloat(payload) + ", \"networktraffic\": "+ Float.parseFloat(payload) +",\"memoryusage\": " + Float.parseFloat(payload) + ", \"harddiskusage\": "+ Float.parseFloat(payload) +"} }}";
        payload = " {\"event\": {\"metaData\": {\"owner\": \"" + deviceOwner +
                "\", \"type\": \"temperature\",\"deviceId\": " +
                "\"" + deviceId + "\",\"timestamp\": " + System.currentTimeMillis() +
                "},\"payloadData\": { \"temperature\": " + Float.parseFloat(payload) + ", \"coffeelevel\": 0} }}";
        String token = "4e441f75bcb53b22c8315bddcc992ac9";
        System.out.println(payload);
        if (!connectedCupMQttTransportHandler.isConnected()) {
            connectedCupMQttTransportHandler.setToken(token);
            connectedCupMQttTransportHandler.connect();

        }
        try {
            if (connectedCupMQttTransportHandler.isConnected()) {
                connectedCupMQttTransportHandler.publishToConnectedCup(deviceOwner, deviceId, payload, tenantDomain, 0,
                        true);
            }
        } catch (TransportHandlerException e) {
           e.printStackTrace();
        }
    }
}
