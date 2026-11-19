public class Book extends Collectible {

    private String title;
    private String author;
    private String edition;
    private String genre;

    public Book(String id, YearEstimate yearOfOrigin, String owner, String condition, double startingPrice, String title, String author, String edition, String genre){
        super(id, yearOfOrigin, owner, condition, startingPrice);
        this.title=title;
        this.author=author;
        this.edition=edition;
        this.genre=genre;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getEdition(){
        return edition;
    }
    public String getGenre(){
        return genre;
    }
    public String getinfo(){
        return id + ". " + title + " by " + author + " (" + edition + ")";
    }
    public String toString(){
        return "Book[" + super.toString() + ", title=" + title + ", author=" + author + ", edition=" + edition + ", genre=" + genre + "]";
    }
}