package Annotation;
import java.util.ArrayList;
import java.util.List;
public class WarningSuppressor {
  
   @SuppressWarnings("unchecked")
   public static void main(String[] args) {
       List rawList = new ArrayList();
       rawList.add("Hello");
       rawList.add("World");
       List<String> stringList = rawList;
       for (String s : stringList) {
           System.out.println(s);
       }
   }
}
