package ufrpe.flyfood.plot;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficosFlyFood {

    public void mostrarPontos(HashMap<String, int[]> pontos) {
        XYDataset dataset = createDataset(pontos);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Pontos de entrega",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();

        Shape circle = new Ellipse2D.Double(-2.5, -2.5, 15, 15);
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesShape(i, circle);
            if (dataset.getSeriesKey(i).toString().equals("R"))
                renderer.setSeriesPaint(i, Color.RED);
            else {
                renderer.setSeriesPaint(i, Color.BLUE);
            }
            renderer.setSeriesOutlinePaint(i, Color.BLACK);
            renderer.setSeriesOutlineStroke(i, new BasicStroke(1.0f));
        }
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator((dataset1, series, item) -> dataset1.getSeriesKey(series).toString());

        ChartFrame frame = new ChartFrame("Melhor Rota", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void melhorAlgoritmo() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Com otimização");
        XYSeries series2 = new XYSeries("Sem otimização");
        series.add(1, 0.0047);
        series.add(2, 0.0041);
        series.add(3, 0.0055);
        series.add(4, 0.0062);
        series.add(5, 0.0129);
        series.add(6, 0.0311);
        series.add(7, 0.0546);
        series.add(8, 0.1560);
        series.add(9, 0.5415);
        series.add(10, 0.9491);
        series.add(11, 2.2916);
        series.add(12, 7.0769);
        series.add(13, 15.8246);
        series.add(14, 71.7634);
        series.add(15, 212.2549);

        series2.add(1, 0.0013);
        series2.add(2, 0.0037);
        series2.add(3, 0.0048);
        series2.add(4, 0.0069);
        series2.add(5, 0.0129);
        series2.add(6, 0.0259);
        series2.add(7, 0.0568);
        series2.add(8, 0.1572);
        series2.add(9, 0.7547);
        series2.add(10, 3.7873);
        series2.add(11, 29.6257);
        series2.add(12, 357.9748);

        dataset.addSeries(series);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Crescimento do tempo em relação a entrada",
                "Tempo",
                "Qtd pontos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);

        ChartFrame frame = new ChartFrame("Crescimento de X em relação a Y", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void melhorPermutacao() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Com otimização");
        XYSeries series2 = new XYSeries("Sem otimização");
        series.add(1, 1);
        series.add(2, 2);
        series.add(3, 5);
        series.add(4, 13);
        series.add(5, 50);
        series.add(6, 66);
        series.add(7, 116);
        series.add(8, 257);
        series.add(9, 600);
        series.add(10, 1066);
        series.add(11, 3521);
        series.add(12, 6872);
        series.add(13, 22787);
        series.add(14, 59570);


        series2.add(1, 1);
        series2.add(2, 2);
        series2.add(3, 6);
        series2.add(4, 24);
        series2.add(5, 120);
        series2.add(6, 720);
        series2.add(7, 5040);
        series2.add(8, 40320);
        series2.add(9, 362880);
        series2.add(10, 3628800);
        series2.add(11, 39916800);
        series2.add(12, 479001600);

        dataset.addSeries(series);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Crescimento das permutações em relação a entrada",
                "Permutações",
                "Qtd pontos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < dataset.getSeriesCount(); i++) {
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        plot.setRenderer(renderer);

        ChartFrame frame = new ChartFrame("Crescimento de X em relação a Y", chart);
        frame.pack();
        frame.setVisible(true);
    }



    private XYDataset createDataset(HashMap<String, int[]> pontos) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (Map.Entry<String, int[]> ponto : pontos.entrySet()) {
            XYSeries series = new XYSeries(ponto.getKey());
            series.add(ponto.getValue()[0], ponto.getValue()[1]);
            dataset.addSeries(series);
        }
        return dataset;
    }
}
