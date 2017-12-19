package ch.bfh.categorizer;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SentimentCategorizer {
    private DoccatModel enModel;
    private DoccatModel deModel;

    /**
     * trains the model with 30 iterations and between 0 and 9
     * @param path URL from the gold standard file
     */
    public void trainModel(String path, ModelLanguage langiuage) {
        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream(path);
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            int cutoff = 10;
            int trainingIterations = 30;
            this.setLanguageModel(DocumentCategorizerME.train(langiuage.name(), sampleStream, cutoff,
                    trainingIterations), langiuage);
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

    private DoccatModel getLanguageModel(ModelLanguage language){

        switch (language){
            case EN:
                return enModel;
            case DE:
                return deModel;
        }
        return enModel;
    }

    private void setLanguageModel(DoccatModel model, ModelLanguage language){

        switch (language){
            case EN:
                enModel = model;
                break;
            case DE:
                deModel = model;
        }

    }

     /**
     * @param content the content for categorize his sentiment
     * @return returns a Value between 0 and 9 which definies the sentiment of the content
     */

    public int getSentiment(String content, ModelLanguage language) {
        DocumentCategorizerME categorizer = new DocumentCategorizerME(getLanguageModel(language));
        double[] outcomes = categorizer.categorize(content);
        String category = categorizer.getBestCategory(outcomes);
        return Integer.parseInt(category);
    }
}