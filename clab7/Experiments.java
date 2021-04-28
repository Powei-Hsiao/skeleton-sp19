import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Double> bst = new BST<>();
        List<Double> optimalDepths = new ArrayList<>();
        List<Double> avgDepths = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int i = 0;
        while (bst.size() < 5000) {
            bst.add(Math.random());
            optimalDepths.add(ExperimentHelper.optimalAverageDepth(bst.size()));
            avgDepths.add(bst.avgDepth());
            xValues.add(i);
            i += 1;
        }

        XYChart chart = new XYChartBuilder().width(5000).height(20).xAxisTitle("x label").yAxisTitle("y label").build();

        chart.addSeries("Average optimal depth", xValues, optimalDepths);
        chart.addSeries("Real average depth by randomly add item in BST", xValues, avgDepths);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Double> bst = new BST<>();
        while (bst.size() < 5000) {
            bst.add(Math.random());
        }

        List<Double> avgDepths = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int i = 0;

        while (i < 10000) {
            avgDepths.add(bst.avgDepth());
            xValues.add(i);
            ExperimentHelper.randomlyDelete(bst);

            double temp = Math.random();
            while (bst.contains(temp)) {
                temp = Math.random();
            }
            ExperimentHelper.randomlyInsert(bst, temp);
            i += 1;
        }

        XYChart chart = new XYChartBuilder().width(10000).height(20).xAxisTitle("x label").yAxisTitle("y label").build();

        chart.addSeries("Average optimal depth after randomly deletion and insertion", xValues, avgDepths);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Double> bst = new BST<>();
        while (bst.size() < 5000) {
            bst.add(Math.random());
        }

        List<Double> avgDepths = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int i = 0;

        while (i < 20000) {
            avgDepths.add(bst.avgDepth());
            xValues.add(i);
            ExperimentHelper.randomlyDeleteBoth(bst);

            double temp = Math.random();
            while (bst.contains(temp)) {
                temp = Math.random();
            }
            ExperimentHelper.randomlyInsert(bst, temp);
            i += 1;
        }

        XYChart chart = new XYChartBuilder().width(20000).height(20).xAxisTitle("x label").yAxisTitle("y label").build();

        chart.addSeries("Average optimal depth after randomly deletion and insertion", xValues, avgDepths);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        Experiments.experiment3();
    }
}
