package BootCamp.Topic2_TDD.Blog;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {

	private Blog blog;

	@Before
	public void setUp() {
		blog = new Blog();
	}

	@After
	public void tearDown() {
		blog = null;
	}

	@Test
	public void whenNewEntryIsPostedItIsAddedToTheBlog() {
		BlogEntry newEntry = new BlogEntry("John", "Title", "This is a post");
		blog.postEntry(newEntry);
		Assert.assertTrue(blog.getEntries().contains(newEntry));
	}

	@Test
	public void whenNewEntryIsPostedItIsAddedInTheTopOfTheBlog() {
		BlogEntry newEntry = new BlogEntry("John", "Title1", "This is a post");
		BlogEntry newEntry2 = new BlogEntry("Peter", "Title2", "This is another post");
		blog.postEntry(newEntry);
		blog.postEntry(newEntry2);
		Assert.assertEquals(blog.getEntries().get(0), newEntry2);
	}

	@Test
	public void whenEntryIsDeletedItIsRemovedFromTheBlog() {
		BlogEntry entry = new BlogEntry("John", "Title1", "This is a post");
		BlogEntry entry2 = new BlogEntry("Peter", "Title2", "This is another post");
		blog.postEntry(entry);
		blog.postEntry(entry2);
		blog.removeEntry(entry);
		Assert.assertTrue(!blog.getEntries().contains(entry));
	}

	@Test
	public void whenTop10EntriesWantToBeShownTheyAreShown() {
		List<BlogEntry> expected = new LinkedList<BlogEntry>();
		for (int i = 0; i < 15; i++) {
			BlogEntry entry = new BlogEntry("Author", "Title" + i, "This is a post");
			blog.postEntry(entry);
			if (i >= 5)
				expected.add(0, entry);
		}
		Assert.assertEquals(expected, blog.showMostRecent(10));
	}

}
