package ch.bfh.util;


import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Can be used to import files in the Ressource Folder
 */
@Component
public class RessourceLoader {
    /**
     *Get Files from the Project defined Ressource Folder
     * @param path to file
     * @return File
     */
    public File getRessource(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputData = new File(classLoader.getResource(path).getFile());
        return inputData;
    }
}
