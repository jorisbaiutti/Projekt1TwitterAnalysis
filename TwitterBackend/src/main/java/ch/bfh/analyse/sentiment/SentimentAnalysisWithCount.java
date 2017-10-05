package ch.bfh.analyse.sentiment;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SentimentAnalysisWithCount {
    DoccatModel model;

    public void trainModel(){
        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream("C:\\Projekt1TwitterAnalysis\\TwitterBackend\\src\\main\\resources\\inputfiles\\sentimentinputfile.txt");
            System.out.println(dataIn.toString());

            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            int cutoff = 2;
            int trainingIterations = 30;
            model = DocumentCategorizerME.train("en", sampleStream,cutoff, trainingIterations);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int classifyNewTweet(String tweet) throws IOException{
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        double[] outcomes = myCategorizer.categorize(tweet);
        String category = myCategorizer.getBestCategory(outcomes);

        System.out.print("-------\nTweet : " + tweet + " ===> ");
        if(category.equalsIgnoreCase("1")){
            System.out.println("POSITIV");
            return 1;
        }
        else {
            System.out.println("NEGATIV");
            return 0;
        }

    }
}
