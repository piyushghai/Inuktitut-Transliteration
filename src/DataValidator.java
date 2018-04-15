import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author piyushghai on {4/13/18}
 */

public class DataValidator {

    static String folder_path = "/Users/piyushghai/Documents/git-repos/InuktitutToRoman/data/";

    static String SENTINEL_VALUE = "SENTINEL_VALUE";

    public static void main(String[] args) throws IOException {

        List<String> inkFilePaths = Utils.getFileList(folder_path + "ink_verses/");
        List<String> engFilePaths = Utils.getFileList(folder_path + "eng_verses/");

        inkFilePaths.remove(".DS_Store");
        engFilePaths.remove(".DS_Store");

//        inkFilePaths.clear();
//        engFilePaths.clear();
//        String chr_24 = "Ezra_8";
//        inkFilePaths.add(chr_24);
//        engFilePaths.add(chr_24);

        int count = 0;

        int inkLines = 0;
        int engLines = 0;

        for (String file : inkFilePaths) {
            Reader inkReader = new InputStreamReader(new FileInputStream(folder_path + "ink_cleaned/" + file), Charset.defaultCharset());
            Reader inkBuffReader = new BufferedReader(inkReader);

            Reader engReader = new InputStreamReader(new FileInputStream(folder_path + "eng_verses/" + file), Charset.defaultCharset());
            Reader engBuffReader = new BufferedReader(engReader);
            String inkString = SENTINEL_VALUE;
            String engString = SENTINEL_VALUE;

            while (((BufferedReader) inkBuffReader).readLine() != null)
                inkLines++;
            while (((BufferedReader) engBuffReader).readLine() != null)
                engLines++;

//            BufferedWriter inkWriter = new BufferedWriter(new FileWriter(folder_path + "ink_cleaned/" + file, true));

//            while (true) {
//
//                if (SENTINEL_VALUE.equals(inkString) || SENTINEL_VALUE.equals(engString)) {
//                    inkString = ((BufferedReader) inkBuffReader).readLine();
//                    engString = ((BufferedReader) engBuffReader).readLine();
//                }
//
//                if (inkString == null || engString == null) {
//                    break;
//                }
//
//                int inkLineNo = getLineNumber(inkString);
//                int engLineNo = getLineNumber(engString);
//
//                while (Math.abs(inkLineNo - engLineNo) > 1) {
//
//                    if (engLineNo > inkLineNo) {
//                        System.out.println("Flagging here!" + " File  : " + file);
//                    }
//
//                    inkLineNo = inkLineNo / 10;
//                }
//
//                if (engLineNo != inkLineNo) {
//                    inkString = ((BufferedReader) inkBuffReader).readLine();
//                    System.out.println("Eng Line : " + engLineNo + " InkLine : " + inkLineNo + " File : " + file);
//                    count++;
//                } else {
//
//                    // This is where the writing first happens
//
////                    String transliterated = transcodeToRoman(inkString);
////
////                    inkWriter.append(transliterated);
////                    inkWriter.write('\n');
//
//                    inkString = ((BufferedReader) inkBuffReader).readLine();
//                    engString = ((BufferedReader) engBuffReader).readLine();
//                }
//            }
//
////            inkWriter.close();
//        }
        }
        System.out.println(count);

        System.out.println(" Eng Lines : " + engLines + " Ink Lines : " + inkLines);

    }

    private static int getLineNumber(String engString) {

        int num = 0;
        for (int i = 0; i < 4; i++) {
            if (Character.isDigit(engString.charAt(i))) {
                num = num * 10 + (engString.charAt(i) - '0');
            } else {
                break;
            }
        }

        if (num == 0) {
            throw new RuntimeException("Got no number for string : " + engString);
        }
        return num;
    }

    private static void writeToFiles(List<String> inukString, List<String> transliteratedString) throws
            IOException {
        String outputFileInuk = "inuk_original";
        String outputFileEng = "inuk_roman";
        BufferedWriter writer = new BufferedWriter(new FileWriter(folder_path + outputFileInuk, true));

        for (String str : inukString) {
            String mod = str.replaceAll("\\d", "");
            writer.append(mod);
            writer.append('\n');
        }

        writer.close();

        writer = new BufferedWriter(new FileWriter(folder_path + outputFileEng, true));

        for (String str : transliteratedString) {
            String mod = str.replaceAll("\\d", "");
            writer.append(mod);
            writer.append('\n');
        }

        writer.close();
    }

    private static void writeInukFilesAgain() throws IOException {
        List<String> inkFilePaths = Utils.getFileList(folder_path + "ink_verses/");

        System.out.println(inkFilePaths.size() + " : ");
        inkFilePaths.remove(".DS_Store");

        int count = 0;

        int prevLine = -1;

        for (String file : inkFilePaths) {
            Reader inkReader = new InputStreamReader(new FileInputStream(folder_path + "ink_verses/" + file), Charset.defaultCharset());
            Reader inkBuffReader = new BufferedReader(inkReader);

            BufferedWriter writer = new BufferedWriter(new FileWriter(folder_path + "ink_verses_cleaned/" + file, true));

            String inkString = SENTINEL_VALUE;

            while (true) {

                inkString = ((BufferedReader) inkBuffReader).readLine();
                if (inkString == null) {
                    break;
                }

                int inkLineNo = getLineNumber(inkString);

                if (prevLine == inkLineNo) {
                    count++;
                    System.out.println(file);
                }

                prevLine = inkLineNo;
            }
        }
        System.out.println(count);
    }

    static public String transcodeToRoman(String s) {
        int i = 0;
        int l = s.length();
        char c, e;
        String d;
        StringBuffer sb = new StringBuffer();
        while (i < l) {
            c = s.charAt(i);
            switch (c) {
                case '\u1403':
                    d = "i";
                    break;
                case '\u1431':
                    d = "pi";
                    break;
                case '\u144E':
                    d = "ti";
                    break;
                case '\u146D':
                    d = "ki";
                    break;
                case '\u148B':
                    d = "gi";
                    break;
                case '\u14A5':
                    d = "mi";
                    break;
                case '\u14C2':
                    d = "ni";
                    break;
                case '\u14EF':
                    d = "si";
                    break;
                case '\u14D5':
                    d = "li";
                    break;
                case '\u1528':
                    d = "ji";
                    break;
                case '\u1555':
                    d = "vi";
                    break;
                case '\u1546':
                    d = "ri";
                    break;
                case '\u157F':
                    d = "qi";
                    break;
                case '\u158F':
                    d = "ngi";
                    break;
                case '\u1671':
                    d = "nngi";
                    break;
                case '\u15A0':
                    d = "&i";
                    break;
                case '\u1405':
                    d = "u";
                    break;
                case '\u1433':
                    d = "pu";
                    break;
                case '\u1450':
                    d = "tu";
                    break;
                case '\u146F':
                    d = "ku";
                    break;
                case '\u148D':
                    d = "gu";
                    break;
                case '\u14A7':
                    d = "mu";
                    break;
                case '\u14C4':
                    d = "nu";
                    break;
                case '\u14F1':
                    d = "su";
                    break;
                case '\u14D7':
                    d = "lu";
                    break;
                case '\u152A':
                    d = "ju";
                    break;
                case '\u1557':
                    d = "vu";
                    break;
                case '\u1548':
                    d = "ru";
                    break;
                case '\u1581':
                    d = "qu";
                    break;
                case '\u1591':
                    d = "ngu";
                    break;
                case '\u1673':
                    d = "nngu";
                    break;
                case '\u15A2':
                    d = "&u";
                    break;
                case '\u140A':
                    d = "a";
                    break;
                case '\u1438':
                    d = "pa";
                    break;
                case '\u1455':
                    d = "ta";
                    break;
                case '\u1472':
                    d = "ka";
                    break;
                case '\u1490':
                    d = "ga";
                    break;
                case '\u14AA':
                    d = "ma";
                    break;
                case '\u14C7':
                    d = "na";
                    break;
                case '\u14F4':
                    d = "sa";
                    break;
                case '\u14DA':
                    d = "la";
                    break;
                case '\u152D':
                    d = "ja";
                    break;
                case '\u1559':
                    d = "va";
                    break;
                case '\u154B':
                    d = "ra";
                    break;
                case '\u1583':
                    d = "qa";
                    break;
                case '\u1593':
                    d = "nga";
                    break;
                case '\u1675':
                    d = "nnga";
                    break;
                case '\u15A4':
                    d = "&a";
                    break;
                case '\u1404':
                    d = "ii";
                    break;
                case '\u1432':
                    d = "pii";
                    break;
                case '\u144F':
                    d = "tii";
                    break;
                case '\u146E':
                    d = "kii";
                    break;
                case '\u148C':
                    d = "gii";
                    break;
                case '\u14A6':
                    d = "mii";
                    break;
                case '\u14C3':
                    d = "nii";
                    break;
                case '\u14F0':
                    d = "sii";
                    break;
                case '\u14D6':
                    d = "lii";
                    break;
                case '\u1529':
                    d = "jii";
                    break;
                case '\u1556':
                    d = "vii";
                    break;
                case '\u1547':
                    d = "rii";
                    break;
                case '\u1580':
                    d = "qii";
                    break;
                case '\u1590':
                    d = "ngii";
                    break;
                case '\u1672':
                    d = "nngii";
                    break;
                case '\u15A1':
                    d = "&ii";
                    break;
                case '\u1406':
                    d = "uu";
                    break;
                case '\u1434':
                    d = "puu";
                    break;
                case '\u1451':
                    d = "tuu";
                    break;
                case '\u1470':
                    d = "kuu";
                    break;
                case '\u148E':
                    d = "guu";
                    break;
                case '\u14A8':
                    d = "muu";
                    break;
                case '\u14C5':
                    d = "nuu";
                    break;
                case '\u14F2':
                    d = "suu";
                    break;
                case '\u14D8':
                    d = "luu";
                    break;
                case '\u152B':
                    d = "juu";
                    break;
                case '\u1558':
                    d = "vuu";
                    break;
                case '\u1549':
                    d = "ruu";
                    break;
                case '\u1582':
                    d = "quu";
                    break;
                case '\u1592':
                    d = "nguu";
                    break;
                case '\u1674':
                    d = "nnguu";
                    break;
                case '\u15A3':
                    d = "&uu";
                    break;
                case '\u140B':
                    d = "aa";
                    break;
                case '\u1439':
                    d = "paa";
                    break;
                case '\u1456':
                    d = "taa";
                    break;
                case '\u1473':
                    d = "kaa";
                    break;
                case '\u1491':
                    d = "gaa";
                    break;
                case '\u14AB':
                    d = "maa";
                    break;
                case '\u14C8':
                    d = "naa";
                    break;
                case '\u14F5':
                    d = "saa";
                    break;
                case '\u14DB':
                    d = "laa";
                    break;
                case '\u152E':
                    d = "jaa";
                    break;
                case '\u155A':
                    d = "vaa";
                    break;
                case '\u154C':
                    d = "raa";
                    break;
                case '\u1584':
                    d = "qaa";
                    break;
                case '\u1594':
                    d = "ngaa";
                    break;
                case '\u1676':
                    d = "nngaa";
                    break;
                case '\u15A5':
                    d = "&aa";
                    break;
                case '\u1449':
                    d = "p";
                    break;
                case '\u1466':
                    d = "t";
                    break;
                case '\u1483':
                    d = "k";
                    break;
                case '\u14A1':
                    d = "g";
                    break;
                case '\u14BB':
                    d = "m";
                    break;
                case '\u14D0':
                    d = "n";
                    break;
                case '\u1505':
                    d = "s";
                    break;
                case '\u14EA':
                    d = "l";
                    break;
                case '\u153E':
                    d = "j";
                    break;
                case '\u155D':
                    d = "v";
                    break;
                case '\u1550': // r
                    i++;
                    if (i < l) {
                        e = s.charAt(i);
                        switch (e) { // r+k. > q.
                            case '\u146D':
                                d = "qi";
                                break;
                            case '\u146F':
                                d = "qu";
                                break;
                            case '\u1472':
                                d = "qa";
                                break;
                            case '\u146E':
                                d = "qii";
                                break;
                            case '\u1470':
                                d = "quu";
                                break;
                            case '\u1473':
                                d = "qaa";
                                break;
                            case '\u146B':
                                d = "qai";
                                break;
                            default:
                                d = "r";
                                i--;
                                break;
                        }
                    } else {
                        i--;
                        d = "r";
                    }
                    break;
                case '\u1585': // q
                    i++;
                    if (i < l) {
                        e = s.charAt(i);
                        switch (e) { // q+k. > qq.
                            case '\u146D':
                                d = "qqi";
                                break;
                            case '\u146F':
                                d = "qqu";
                                break;
                            case '\u1472':
                                d = "qqa";
                                break;
                            case '\u146E':
                                d = "qqii";
                                break;
                            case '\u1470':
                                d = "qquu";
                                break;
                            case '\u1473':
                                d = "qqaa";
                                break;
                            case '\u146B':
                                d = "qqai";
                                break;
                            default:
                                d = "q";
                                i--;
                                break;
                        }
                    } else {
                        i--;
                        d = "q";
                    }
                    break;
                case '\u1595': // ng
                    i++;
                    if (i < l) {
                        e = s.charAt(i);
                        switch (e) { // ng+g. > ng.
                            case '\u148B':
                                d = "ngi";
                                break;
                            case '\u148D':
                                d = "ngu";
                                break;
                            case '\u1490':
                                d = "nga";
                                break;
                            case '\u148C':
                                d = "ngii";
                                break;
                            case '\u148E':
                                d = "nguu";
                                break;
                            case '\u1491':
                                d = "ngaa";
                                break;
                            case '\u1489':
                                d = "ngai";
                                break;
                            default:
                                d = "ng";
                                i--;
                                break;
                        }
                    } else {
                        i--;
                        d = "ng";
                    }
                    break;
                case '\u1596':
                    i++;
                    if (i < l) {
                        e = s.charAt(i);
                        switch (e) { // nng+g. > nng.
                            case '\u148B':
                                d = "nngi";
                                break;
                            case '\u148D':
                                d = "nngu";
                                break;
                            case '\u1490':
                                d = "nnga";
                                break;
                            case '\u148C':
                                d = "nngii";
                                break;
                            case '\u148E':
                                d = "nnguu";
                                break;
                            case '\u1491':
                                d = "nngaa";
                                break;
                            case '\u1489':
                                d = "nngai";
                                break;
                            default:
                                d = "nng";
                                i--;
                                break;
                        }
                    } else {
                        i--;
                        d = "nng";
                    }
                    break;
                case '\u15A6':
                    d = "&";
                    break;
                case '\u157C':
                    d = "H";
                    break; // Nunavut H
                case '\u1574':
                    d = "hai";
                    break; // Nunavik Hai
                case '\u1575':
                    d = "hi";
                    break; // Nunavik Hi
                case '\u1576':
                    d = "hii";
                    break; // Nunavik Hii
                case '\u1577':
                    d = "hu";
                    break; // Nunavik Hu
                case '\u1578':
                    d = "huu";
                    break; // Nunavik Huu
                case '\u1579':
                    d = "ha";
                    break; // Nunavik Ha
                case '\u157A':
                    d = "haa";
                    break; // Nunavik Haa
                case '\u15AF':
                    d = "b";
                    break; // Aivilik B
                case '\u1401':
                    d = "ai";
                    break; // ai
                case '\u142f':
                    d = "pai";
                    break; // pai
                case '\u144c':
                    d = "tai";
                    break; // tai
                case '\u146b':
                    d = "kai";
                    break; // kai
                case '\u1489':
                    d = "gai";
                    break; // gai
                case '\u14a3':
                    d = "mai";
                    break; // mai
                case '\u14c0':
                    d = "nai";
                    break; // nai
                case '\u14ed':
                    d = "sai";
                    break; // sai
                case '\u14d3':
                    d = "lai";
                    break; // lai
                case '\u1526':
                    d = "jai";
                    break; // jai
                case '\u1553':
                    d = "vai";
                    break; // vai
                case '\u1543':
                    d = "rai";
                    break; // rai
                case '\u1542':
                    d = "rai";
                    break; // rai
                case '\u166f':
                    d = "qai";
                    break; // qai
                case '\u1670':
                    d = "ngai";
                    break;  // ngai
                default:
                    d = new String(new char[]{c});
                    break;
            }
            i++;
            sb.append(d);
        }
        return sb.toString();
    }
}
