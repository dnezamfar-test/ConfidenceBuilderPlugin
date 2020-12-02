import com.rma.io.DssFileManagerImpl;
import hec.heclib.dss.HecDssCatalog;
import hec.heclib.dss.HecPairedData;
import hec.io.DSSIdentifier;
import hec.io.PairedDataContainer;
import rma.stats.NormalDist;
import java.util.ArrayList;

public class AlaiWaiSimplify {
    public static void main(String[] args) {

        // Defining the record
        String DssFilePath = ("C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq.dss");
        String Pathname = "/12-Stochastic/Duration Plugin-AlaWaiDurations12/Probability-undef/Frequency Full/A3 - FLOW - 1 Hour DurationMax/Existing C:Determinis:DuratPlugi-AlaWaiDurations12/\n";
        String saveFile = "C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\AlaiWai_FullFreq_QC.dss";

        HecDssCatalog catalog = new HecDssCatalog();
        catalog.setDSSFileName(DssFilePath, true);
        String[] pathnameList = catalog.getPathnameList(true);
        int recordNumbers = pathnameList.length;



        //Retrieving the Data---------------------------------------
        for (int j = 0; j<pathnameList.length;j++)
        {
            System.out.println("record STart!");
            //int j=0;
            DSSIdentifier myDss = new DSSIdentifier(DssFilePath, pathnameList[j]);
            PairedDataContainer mypdc = DssFileManagerImpl.getDssFileManager().readPairedDataContainer(myDss);
            double [] xOrds = mypdc.xOrdinates;
            double [] yOrds = mypdc.yOrdinates[0];

            //CREATING X (Probability) LIST : Data probabilities are non-sense. Reconstructing the probabilities for the dataset with plotting position 1/(1+n)
            for(int i=1; i<= 280500; i++){
                xOrds[280500-i]= (1/(1+(((double)i))));
            }
            //Convert to probability space--------------------------------------
            NormalDist StandardNormal = new NormalDist(0,1);
            for(int i=0; i< xOrds.length; i++){
                xOrds[i] = StandardNormal.invCDF(xOrds[i]);
            }
            //Calling the reduction
            Line myLine = new Line(xOrds,yOrds);
            System.out.println("last point is: " + myLine.getPoint(myLine.getVerticesCount()-1).getY());
            Line mySimpleLine = LineThinner.DouglasPeukerReduction(myLine,.001);
            System.out.println("Number of vertices final: " + mySimpleLine.getVerticesCount());
            System.out.println("last simple point is: " + myLine.getPoint(myLine.getVerticesCount()-1).getY());

            //Bring Zscores back to probabilities
            ArrayList<Double> XordsProbabilities = new ArrayList<>();
            for(int i=0; i < mySimpleLine.getVerticesCount(); i++){
                XordsProbabilities.add(StandardNormal.getCDF(mySimpleLine.getPoint(i).getX())); }
            Double[] xCoordinates = XordsProbabilities.toArray(new Double[0]);
            double[] xCoordinates2 = new double[xCoordinates.length];
            for(int i=0 ; i < xCoordinates2.length; i++){
                xCoordinates2[i] = xCoordinates[i];
            }
            System.out.println("length of xCoords: "+ xCoordinates2.length);


            Line mySimpleLineX = new Line(xCoordinates2,mySimpleLine.getYords() );
            //Line mySimpleLineX = new Line(xOrds,yOrds );
            System.out.println("last point is: " + mySimpleLineX.getPoint(mySimpleLineX.getVerticesCount()-1).getY());

            // Saving everything back
            mypdc.xOrdinates = mySimpleLineX.getXords();
            double [][] yForDSS = new double[1][];
            yForDSS[0] = mySimpleLineX.getYords();
            mypdc.yOrdinates = yForDSS;
            mypdc.numberOrdinates = mySimpleLineX.getVerticesCount();
            mypdc.xunits = "Probability";

            mypdc.types = null;
            mypdc.fileName = saveFile;
            HecPairedData dssPairedData1 = new HecPairedData();
            dssPairedData1.setDSSFileName(saveFile);
            int status = dssPairedData1.write(mypdc);
            dssPairedData1.done();
        }

    }
}
