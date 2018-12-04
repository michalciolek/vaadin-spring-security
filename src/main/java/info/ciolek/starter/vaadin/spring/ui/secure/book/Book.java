package info.ciolek.starter.vaadin.spring.ui.secure.book;

public class Book {
    private String name;
    private String author;

    Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
