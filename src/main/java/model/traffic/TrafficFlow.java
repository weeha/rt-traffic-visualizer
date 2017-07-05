package model.traffic;

import com.google.protobuf.ByteString;
import openlr.binary.ByteArray;

/**
 * Created by Florian Noack on 27.05.2017.
 */
public class TrafficFlow extends Traffic{

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private boolean roadClosure;
    private String travelTime = null;
    private String relativeSpeed = null;
    private String trafficCondition = null;
    private ProtobufTrafficFlowV5.TrafficFlow pFLow;

    public TrafficFlow(){
        super();
    }

    public TrafficFlow(ProtobufTrafficFlowV5.TrafficFlow pFLow){
        this.pFLow = pFLow;
        if(pFLow.getSpeed() != null) {
            travelTime = pFLow.getSpeed().getTravelTimeSeconds() + " Seconds";
            averageSpeed = pFLow.getSpeed().getAverageSpeedKmph() + " km/h";
        }
    }

    public void setTravelTime(String travelTime){
        this.travelTime = travelTime;
    }

    public String getTravelTime(){
        return travelTime;
    }

    public String getConfidence(){
        return (this.pFLow.getSpeed() == null) ? "" : Integer.toString(this.pFLow.getSpeed().getConfidence());
    }

    public void setRelativeSpeed(String relSpeed){
        relativeSpeed = relSpeed;
    }

    public String getRelativeSpeed(){
        return (this.pFLow.getSpeed() == null) ? "" : Float.toString(this.pFLow.getSpeed().getRelativeSpeed());
    }

    public void setTrafficCondition(String cond){
        trafficCondition = cond;
    }

    public String getTrafficCondition(){
        return trafficCondition;
    }

    public void setRoadClosed(boolean closed){
        roadClosure = closed;
    }

    public boolean isRoadCosed(){
        return roadClosure;
    }

    public ProtobufTrafficFlowV5.TrafficFlow getTrafficFlow(){
        return this.pFLow;
    }

    @Override
    public String getRawString(){
        ByteArray bytes = new ByteArray(this.pFLow.getLocation().getOpenlr().toByteArray());
        char[] hexChars = new char[bytes.size() * 2];
        for ( int j = 0; j < bytes.size(); j++ ) {
            int v = bytes.get(j) & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return(new String(hexChars));
    }
}
