package Annotation;
class LegacyAPI {
public void oldFeature() {
    System.out.println("This is the old feature. Avoid using it.");
}
public void newFeature() {
    System.out.println("This is the new and recommended feature.");
}
}
public class LegacyApi {
public static void main(String[] args) {
    LegacyAPI api = new LegacyAPI();
    api.oldFeature();  
    api.newFeature(); 
}
}

