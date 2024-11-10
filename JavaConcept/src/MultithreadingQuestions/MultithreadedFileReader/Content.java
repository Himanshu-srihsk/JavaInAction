package MultithreadingQuestions.MultithreadedFileReader;

public class Content implements Comparable<Content>{
    private String content;
    private int order;

    public Content(String content, int order) {
        this.content = content;
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(Content o) {
        return this.content.compareTo(o.content);
    }
}
