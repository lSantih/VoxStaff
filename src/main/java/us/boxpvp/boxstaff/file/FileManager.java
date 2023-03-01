package us.boxpvp.boxstaff.file;

import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.file.impl.YamlFile;

import java.util.Arrays;
import java.util.List;

public class FileManager {

    private final List<IFile> loadedFiles;

    public FileManager(final BoxStaff boxStaff) {
        this.loadedFiles = Arrays.asList(
                new YamlFile("locations/freeze.yml", boxStaff)
        );
    }

    public IFile getFile(final String identifier) {
        for(final IFile iFile : loadedFiles) {
            if(iFile.getFileName().equals(identifier)) {
                return iFile;
            }
        }
        return null;
    }
}
