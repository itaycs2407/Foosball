package Utils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileUtils {

    public static String relativePathToFullPath(String relPath) throws IOException {
        String fullPath = new File("").getCanonicalPath() + relPath;
        return fullPath;
    }

    public static List<String> ReadAllText(String path)  {
        try{
            String filePath = relativePathToFullPath(path);
            List<String> readenText = Files.readAllLines(Paths.get(filePath));
            return readenText;
        }
        catch(Exception e){return null;}
    }

    //Gets string, splitter - and returns a tuple made from the splitted string (while ignoring capital chars if wanted)
    public static Tuple<String,String> SplitLine(String str, String splitter, boolean removeSpaces, boolean camelCase) {
        if(str.length() == 0)return null;
        if(!str.contains(splitter))return null;
        if(removeSpaces)str = str.replaceAll(" ", "");
        if(!camelCase){
            splitter = splitter.toLowerCase();
            str = str.toLowerCase();
        }

        String[] sArr = str.split(splitter, 2);
        return new Tuple<>(sArr[0], sArr[1]);
    }

    //gets map (dictionary) with keys(means the property name) and values(the values of the property), the wanted instance type
    //and the type to work on.
    //go through each row in the dict' and parse the value into the dataStruct wanted field
    //returns the struct after the changes
    public static  <T> T ParseSettings(Map<String,String> configDict, Class classToParse, T dataStruct ) {
        try {
            for (String key: configDict.keySet()) {
                Field currField = classToParse.getField(key);
                Object obj = convertType(currField.getType().getTypeName(),configDict.get(key));
                currField.set(dataStruct,obj);
            }
            return dataStruct;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    //gets type and string to parse, returns the converted value
    public static  Object convertType(String type, String s) {
        switch (type){
            case "double":
                return Double.parseDouble(s);
            case "int":
                return Integer.parseInt(s);
            case "boolean":
                return Boolean.parseBoolean(s);
            default:
                return s.replaceAll("\"","");
        }
    }
}
