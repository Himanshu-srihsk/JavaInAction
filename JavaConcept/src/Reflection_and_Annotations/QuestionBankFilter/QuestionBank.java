package Reflection_and_Annotations.QuestionBankFilter;

public class QuestionBank {
    @Question(topic = "Spring Boot", difficulty = "Medium")
    public String Question1(){
        return "What is the AOP in Spring Boot";
    }
    @Question(topic = "Spring Boot")
    public String Question2(){
        return "What is difference between controller and RestController";
    }
    @Question(topic = "Multithreading", difficulty = "Easy")
    public String Question3(){
        return "What is fork join pool";
    }
    @Question(topic = "Multithreading", difficulty = "Hard")
    public String Question4(){
        return "What is Thread pool executor";
    }
}
