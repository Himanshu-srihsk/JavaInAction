package Reflection_and_Annotations.FactoryPatternwithAnnotations;

/*
Create a @Factory annotation that indicates which constructor to use when creating an instance.
Write a factory utility that:
Finds the constructor annotated with @Factory and uses it to instantiate objects.
Use this to instantiate different classes based on annotations.
 */
public class FactoryPatternwithAnnotations {
    public static void main(String[] args) {
        Computer pc = FactoryUtil.createInstance("Reflection_and_Annotations.FactoryPatternwithAnnotations.PC","2 GB","500 GB","2.4 GHz");
        Computer server = FactoryUtil.createInstance("Reflection_and_Annotations.FactoryPatternwithAnnotations.Server","16 GB","1 TB","2.9 GHz");
        System.out.println("Factory PC Config::"+pc);
        System.out.println("Factory Server Config::"+server);
    }
}

/*
Factory PC Config::Computer{RAM='2 GB', HDD='500 GB', CPU='2.4 GHz'}
Factory Server Config::Computer{RAM='16 GB', HDD='1 TB', CPU='2.9 GHz'}
 */