package view.Analysis;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.traffic.FlowAnalysis;
import model.traffic.TrafficAnalysis;

import java.util.List;

/**
 * Created by Florian Noack on 05.09.2017.
 */
public class FlowAnalysisChart<X, Y> extends AnalysisChart{

    public FlowAnalysisChart() {
        super();
    }

    public FlowAnalysisChart(String identifier) {
        super(identifier);
    }

    @Override
    public void setTrafficAnalysis(List aList) {
        String yAxis = this.getYAxis().getLabel();
        if (yAxis != null){
            if(yAxis.equals("Travel Time [seconds]")){
                createTravelTime(aList);
            }else if(yAxis.equals("Relative Speed")){
                createRelativeSpeed(aList);
            }else if(yAxis.equals("Average Speed [km/h]")){
                createAverageSpeed(aList);
            }else if(yAxis.equals("Confidence")){
                createConfidence(aList);
            }
        }
        this.getData().add(series);
    }

    private void createAverageSpeed(List aList) {
        for(FlowAnalysis a : (List<FlowAnalysis>)(Object)aList){
            System.out.println(a.getAverageSpeed());
            series.getData().add(new XYChart.Data(ft.format(a.getDate()), a.getAverageSpeed()));
            series.setName(label);
        }
    }

    private void createRelativeSpeed(List aList) {
        for(FlowAnalysis a : (List<FlowAnalysis>)(Object)aList){
            series.getData().add(new XYChart.Data(ft.format(a.getDate()), a.getRelativeSpeed()));
            series.setName(label);
        }
    }

    private void createConfidence(List aList) {
        for(FlowAnalysis a : (List<FlowAnalysis>)(Object)aList){
            series.getData().add(new XYChart.Data(ft.format(a.getDate()), a.getConfidence()));
            series.setName(label);
        }
    }

    private void createTravelTime(List aList) {
        for(FlowAnalysis a : (List<FlowAnalysis>)(Object)aList){
            System.out.println(a.getTravelTime());
            series.getData().add(new XYChart.Data(ft.format(a.getDate()), a.getTravelTime()));
            series.setName(label);
        }
    }
}
