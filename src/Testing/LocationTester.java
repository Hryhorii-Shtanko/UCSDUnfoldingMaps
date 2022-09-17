package Testing;

//import demos.SimpleLocation;
//
public class LocationTester {
//public static void main(String[] args) {
//	SimpleLocation lima = new SimpleLocation (-12.0, -77.0);
//}
//}


    public static void main(String[] args)
    {
        SimpleLocation loc1 = new SimpleLocation(39.9, 116.4);
        SimpleLocation loc2 = new SimpleLocation(55.8, 37.6);
        loc1 = loc2;
        loc1.lat = -8.3;
        System.out.println(loc1.lat + ", " + loc1.lon);
    }
}
//    {
//        SimpleLocation loc1 = new SimpleLocation(39.9, 116.4);
//        SimpleLocation loc2 = new SimpleLocation(55.8, 37.6);
//        loc1 = loc2;
//        loc1.lat = -8.3;
//        System.out.println(loc2.lat + ", " + loc2.lon);
//    }
//}