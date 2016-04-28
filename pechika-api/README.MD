# このプロジェクトに何を書くべきか
> project-api should contain your Representations
このAPIのIN/OUTの形式をここに書けばいい？
```java
public class Notification {
    private String text;

    public Notification(String text) {
        this.text = text;
    }

    @JsonProperty
    public String getText() {
        return text;
    }

    @JsonProperty
    public void setText(String text) {
        this.text = text;
    }
}
```
