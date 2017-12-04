package ch.bfh.categorizer;

import ch.bfh.entities.Tweet;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Patrick on 04.12.2017.
 */
@Component
public class SentimentCategorizer {
    DoccatModel model;

    public SentimentCategorizer(){
        this.trainModel();
    }

    public void trainModel() {
        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream("C:\\Projekt1TwitterAnalysis\\TwitterBackend\\src\\main\\resources\\inputfiles\\sentimentinput.txt");
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            int cutoff = 2;
            int trainingIterations = 30;
            model = DocumentCategorizerME.train("en", sampleStream, cutoff,
                    trainingIterations);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getTweetSentiment(Tweet tweet) {
        DocumentCategorizerME categorizer = new DocumentCategorizerME(model);
        double[] outcomes = categorizer.categorize(tweet.getContent());
        String category = categorizer.getBestCategory(outcomes);

        if (category.equalsIgnoreCase("1")) {
            return "Positive";
        } else {
            return "Negative";
        }
    }
}