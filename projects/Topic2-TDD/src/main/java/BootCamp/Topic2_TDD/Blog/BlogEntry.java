package BootCamp.Topic2_TDD.Blog;

public class BlogEntry {

	private String author;
	private String title;
	private String body;

	public BlogEntry(String author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof BlogEntry))
			return false;
		if (obj == this)
			return true;

		BlogEntry entry = (BlogEntry) obj;
		return (this.author == entry.getAuthor() && this.title == entry.getTitle() && this.body == entry.getBody());
	}
}
