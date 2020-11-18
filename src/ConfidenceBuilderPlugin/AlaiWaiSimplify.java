package ConfidenceBuilderPlugin;
import com.rma.io.DssFileManagerImpl;
import hec.heclib.dss.DSSPathname;
import hec.heclib.dss.HecDataManager;
import hec.heclib.dss.HecPairedData;
import hec.io.DSSIdentifier;
import hec.io.PairedDataContainer;
import hec.script.HecDss;
import java.sql.Timestamp;
import java.util.Date;
import static ConfidenceBuilderPlugin.LineThinner.DouglasPeukerReduction;
import static ConfidenceBuilderPlugin.LineThinner.VisvaligamWhyattSimplify;
// need to identify how to get list of pathnames from a dss file.
//



public class AlaiWaiSimplify {
    public static void main(String[] args) {

        // Defining the record
        String DssFilePath = ("C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq_V6.dss");
        String Pathname = "/12-Stochastic/Duration Plugin-AlaWaiDurations12/Probability-undef/Frequency Full/A3 - FLOW - 1 Hour DurationMax/Existing C:Determinis:DuratPlugi-AlaWaiDurations12/\n";
        String saveFile = "C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq_V6.dss";

        /* Retrieving the Data---------------------------------------*/
        Date date = new Date();
        System.out.print(new Timestamp(date.getTime()));
        DSSIdentifier myDss = new DSSIdentifier(DssFilePath, Pathname);
        PairedDataContainer mypdc = DssFileManagerImpl.getDssFileManager().readPairedDataContainer(myDss);
        double [] xOrds = mypdc.xOrdinates;
        double [] yOrds = mypdc.yOrdinates[0];

        System.out.println("Y Right out the DSS File");
        for (int i=0; i<10; i++) {
            System.out.println(yOrds[i]);
        }
        System.out.println("X Right out the DSS File");
        for (int i=0; i<10; i++) {
            System.out.println(xOrds[i]);
        }
        //CREATING X (Probability) LIST : Data probabilities are non-sense. Reconstructing the probabilities for the dataset with plotting position 1/(1+n)
        for(int i=280499; i>= 0; i--){
            xOrds[i]= 1/(1+(((double)i)));
        }
        System.out.println("Fixed the Probabilities");
        for (int i=0; i<10; i++) {
            System.out.println(xOrds[i]);}

        //* Convert to probability space--------------------------------------*/
        for(int i=0; i< xOrds.length; i++){
            xOrds[i] = NormalDistribution.GetInvCDF(xOrds[i]);
        }
        System.out.println("Z Scores");
        for (int i=0; i<10; i++) {
            System.out.println(xOrds[i]);}

        //Calling the reduction
        Line myLine = new Line(xOrds,yOrds);
        //Line mySimpleLine = DouglasPeukerReduction(myLine,.01);
        Line mySimpleLine = VisvaligamWhyattSimplify(144, myLine);
        System.out.println("Time it finished: " + new Timestamp(date.getTime()) );
        System.out.println("Number of vertices final: " + mySimpleLine.getVerticesCount());

        // Saving everything back
        mypdc.xOrdinates = mySimpleLine.getXords();
        double [][] yForDSS = new double[1][];
        yForDSS[0] = mySimpleLine.getYords();
        mypdc.yOrdinates = yForDSS;
        mypdc.numberOrdinates = mySimpleLine.getVerticesCount();
        mypdc.fileName = "C:\\Projects\\Work\\HEC-WAT\\AlaiWai Freq Curves\\Just_Full_Freq_Simple.dss";
        HecPairedData dssPairedData1 = new HecPairedData();
        dssPairedData1.setDSSFileName(saveFile);
        int status = dssPairedData1.write(mypdc);
        dssPairedData1.done();
    }
}
