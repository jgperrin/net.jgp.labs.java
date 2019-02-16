package net.jgp.labs.java.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApp {

  public static void main(String[] args) {
    RegexApp app = new RegexApp();
    app.start();
    app.t2();
  }

  private void t2() {
    List<String> matchList = new ArrayList<String>();
    Pattern regex = Pattern.compile("\\((.*?)\\)");
    Matcher regexMatcher = regex.matcher("Hello This is (Java) Not (.NET)");

    while (regexMatcher.find()) {//Finds Matching Pattern in String
       matchList.add(regexMatcher.group(1));//Fetching Group from String
    }

    for(String str:matchList) {
       System.out.println(str);
    }
  }

  private boolean start() {
    String in = "public static Column callUDF(String udfName, scala.Seq<Column> cols) import org.apache.spark.sql._ val df = Seq((\"id1\", 1), (\"id2\", 4), (\"id3\", 5)).toDF(\"id\", \"value\") val spark = df.sparkSession spark.udf.register(\"simpleUDF\", (v: Int) => v * v) df.select($\"id\", callUDF(\"simpleUDF\", $\"value\"))";
    System.out.println(in);
    Pattern pattern = Pattern.compile("\\(.*\\)");
    Matcher matcher = pattern.matcher(in);
    while (matcher.find()) {
      System.out.println(matcher.group());
    }
    return true;
  }

}
