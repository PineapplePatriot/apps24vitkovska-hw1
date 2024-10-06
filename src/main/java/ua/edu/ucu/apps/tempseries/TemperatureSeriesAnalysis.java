package ua.edu.ucu.apps.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double [] temperatureSeries;
    private int actualSize;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        this.actualSize = temperatureSeries.length;
    }

    public double average() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double avg = average();
        double sumOfSquaredDiffs = 0;
        
        for (double temp : temperatureSeries) {
            sumOfSquaredDiffs += Math.pow(temp - avg, 2);
        }
        return Math.sqrt(sumOfSquaredDiffs / temperatureSeries.length);
    }

    public double min() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double minTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }
        return minTemp;
    }

    public double max() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double maxTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (temp > maxTemp) {
                maxTemp = temp;
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double closest = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp) < Math.abs(closest)) {
                closest = temp;
            } else if (Math.abs(temp) == Math.abs(closest) && temp > closest) {
                closest = temp;
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        double closest = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < Math.abs(closest - tempValue)) {
                closest = temp;
            } else if (Math.abs(temp - tempValue) == Math.abs(closest - tempValue) && temp > closest) {
                closest = temp;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }

        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                count++;
            }
        }
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                count++;
            }
        }
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
    
        int count = 0;
        for (double temp : temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                count++;
            }
        }
        double[] result = new double[count];
        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                result[index++] = temp;
            }
        }
        return result;
    }

    public void reset() {
        temperatureSeries = new double[0];
        actualSize = 0;
    }

    public double[] sortTemps() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
        throw new IllegalArgumentException("Temperature series is empty");
        }
        double[] sortedTemps = temperatureSeries.clone();
        Arrays.sort(sortedTemps);
        return sortedTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature series is empty");
        }
        double avg = average();
        double dev = deviation();
        double min = min();
        double max = max();

        return new TempSummaryStatistics(avg, dev, min, max);
    }

    public int addTemps(double... temps) {
        if (temps == null || temps.length == 0) {
        throw new IllegalArgumentException("No temperatures to add");
        }

        for (double temp : temps) {
            if (temp < -273) {
                throw new InputMismatchException("Temperature below absolute zero (-273°C)");
            }
        }

        if (actualSize + temps.length > temperatureSeries.length) {
            double[] newSeries = new double[temperatureSeries.length * 2 + temps.length];
            System.arraycopy(temperatureSeries, 0, newSeries, 0, actualSize);
            temperatureSeries = newSeries;
        }

        for (double temp : temps) {
            temperatureSeries[actualSize++] = temp;
        }
            return actualSize;
    }
}
