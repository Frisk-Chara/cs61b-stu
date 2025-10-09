package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private class WordSeries extends TreeMap<String, TimeSeries> {
        public WordSeries() {super();}
    }

    private WordSeries wordSeries;
    private TimeSeries totalTimeData;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordSeries = new WordSeries();
        totalTimeData = new TimeSeries();
        In wordSeriesFileRead = new In(wordsFilename);
        In timeDataFileRead = new In(countsFilename);
        //读取wordFilename中的数据存入wordSeries中
        while (wordSeriesFileRead.hasNextLine()) {
            String nextLine = wordSeriesFileRead.readLine();
            String[] splitLine = nextLine.split("\t");
            if (!wordSeries.containsKey(splitLine[0])) {
                TimeSeries temp = new TimeSeries();
                temp.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
                wordSeries.put(splitLine[0], temp);
            } else {
                wordSeries.get(splitLine[0]).put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            }
        }
        while (timeDataFileRead.hasNextLine()) {
            String nextLine = timeDataFileRead.readLine();
            String[] splitLine = nextLine.split(",");
            totalTimeData.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(wordSeries.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries temp = new TimeSeries();
        TimeSeries origin = wordSeries.get(word);
        if (origin == null) {
            return temp;
        }
        List<Integer> years = origin.years();
        for (int i : years) {
            temp.put(i, origin.get(i));
        }
        return temp;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries result = new TimeSeries();
        List<Integer> years = totalTimeData.years();
        for (int i : years) {
            result.put(i, totalTimeData.get(i));
        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries origin = wordSeries.get(word);
        if (origin == null) {
            return new TimeSeries();
        }
        TimeSeries temp = new TimeSeries(origin, startYear, endYear);
        return temp.dividedBy(totalTimeData);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries origin = wordSeries.get(word);
        if (origin == null) {
            return new TimeSeries();
        }
        return origin.dividedBy(totalTimeData);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        for (String i : words) {
            result = result.plus(wordSeries.get(i));
        }
        result = new TimeSeries(result, startYear, endYear);
        return result.dividedBy(totalTimeData);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();
        for (String i : words) {
            result = result.plus(wordSeries.get(i));
        }

        return result.dividedBy(totalTimeData);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
