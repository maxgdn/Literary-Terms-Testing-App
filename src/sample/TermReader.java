package sample;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * Created by Max on 9/10/2015.
 */
public class TermReader {
    public static String termlist;
        public static LinkedHashMap<String, String> readTerms() throws IOException {
            termlist = getTermlist();
            InputStream stream = TermReader.class.getResourceAsStream("/sample/quizzes/" + termlist + ".txt");
            Reader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
        LinkedHashMap<String, String> termsMap = new LinkedHashMap<>();

        String currentLine = bufferedReader.readLine();
        while (currentLine != null) {
            String[] split = currentLine.split("=");
            termsMap.put(split[0].trim(), split[1].trim());
            currentLine = bufferedReader.readLine();
        }
        return termsMap;
    }
    public static String getTermlist() {
        return termlist;
    }

    public static void setTermlist(String currentTermList) {
        termlist = currentTermList;
    }

}

