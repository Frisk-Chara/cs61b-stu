package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {

    private NGramMap wordMap;

    public HistoryHandler(NGramMap Map) {
        this.wordMap = Map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String w : words) {
            lts.add(wordMap.weightHistory(w, startYear, endYear));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        String encodeImage = Plotter.encodeChartAsString(chart);

        return encodeImage;
    }
}
