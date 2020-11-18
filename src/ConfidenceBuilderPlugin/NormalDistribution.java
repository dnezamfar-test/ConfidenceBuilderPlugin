package ConfidenceBuilderPlugin;

public class NormalDistribution {

    public static double GetInvCDF(double probability){
        // Stolen from Will's statistics Library
        double zScore;
        //Taylor Series Coefficients
        double c0 = 2.515517;
        double c1 = 0.802853;
        double c2 = 0.010328;
        double d1 = 1.432788;
        double d2 = 0.189269;
        double d3 = 0.001308;
        //QC the input
        if(probability == 0.5) { zScore = 0; return zScore;}
        if(probability <= 0) {probability = 0.0000000000000001;}
        if(probability >= 1) {probability = 0.9999999999999999;}
        //Conversion happens here
        double t = Math.sqrt(Math.log(1/Math.pow(probability,2)));
        zScore = t - (c0+c1*t+c2*Math.pow(t,2)) / (1+d1*t+d2*Math.pow(t,2)+d3*Math.pow(t,3));
        return zScore;
    }
}
