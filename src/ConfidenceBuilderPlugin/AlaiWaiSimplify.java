package ConfidenceBuilderPlugin;
import com.rma.io.DssFileManagerImpl;
import hec.heclib.dss.HecDssCatalog;
import hec.heclib.dss.HecPairedData;
import hec.io.DSSIdentifier;
import hec.io.PairedDataContainer;
import java.sql.Timestamp;
import java.util.Date;
import static ConfidenceBuilderPlugin.LineThinner.DouglasPeukerReduction;
import static ConfidenceBuilderPlugin.LineThinner.VisvaligamWhyattSimplify;

public class AlaiWaiSimplify {
    public static void main(String[] args) {

        // Defining the record
        String DssFilePath = ("C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq_V6.dss");
        String Pathname = "/12-Stochastic/Duration Plugin-AlaWaiDurations12/Probability-undef/Frequency Full/A3 - FLOW - 1 Hour DurationMax/Existing C:Determinis:DuratPlugi-AlaWaiDurations12/\n";
        String saveFile = "C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq_Simple.dss";

        HecDssCatalog catalog = new HecDssCatalog();
        catalog.setDSSFileName(DssFilePath, true);
        String[] pathnameList = catalog.getPathnameList(true);
        int recordNumbers = pathnameList.length;
        System.out.println(recordNumbers);

        /* Retrieving the Data---------------------------------------*/
        for (int j = 0; j<pathnameList.length;j++)
        {
            DSSIdentifier myDss = new DSSIdentifier(DssFilePath, pathnameList[j]);
            PairedDataContainer mypdc = DssFileManagerImpl.getDssFileManager().readPairedDataContainer(myDss);
            double [] xOrds = mypdc.xOrdinates;
            double [] yOrds = mypdc.yOrdinates[0];

            //CREATING X (Probability) LIST : Data probabilities are non-sense. Reconstructing the probabilities for the dataset with plotting position 1/(1+n)
            for(int i=0; i<= 280499; i++){
                xOrds[280499-i]= (1/(1+(((double)i))));
            }
            //* Convert to probability space--------------------------------------*/
            for(int i=0; i< xOrds.length; i++){
                xOrds[i] = NormalDistribution.GetInvCDF(xOrds[i]);
            }

            //Calling the reduction
            Line myLine = new Line(xOrds,yOrds);
            Line mySimpleLine = DouglasPeukerReduction(myLine,.001);
            System.out.println("Number of vertices final: " + mySimpleLine.getVerticesCount());

            // Saving everything back
            mypdc.xOrdinates = mySimpleLine.getXords();
            double [][] yForDSS = new double[1][];
            yForDSS[0] = mySimpleLine.getYords();
            mypdc.yOrdinates = yForDSS;
            mypdc.numberOrdinates = mySimpleLine.getVerticesCount();
            mypdc.xunits = "Zscore";
            mypdc.types = null;
            mypdc.fileName = saveFile;
            HecPairedData dssPairedData1 = new HecPairedData();
            dssPairedData1.setDSSFileName(saveFile);
            int status = dssPairedData1.write(mypdc);
            dssPairedData1.done();
        }

    }
}
