package BootCamp.Topic2_TDD.Blog;

import java.util.LinkedList;
import java.util.List;

public class Blog {

	private List<BlogEntry> list;

	public Blog() {
		list = new LinkedList<BlogEntry>();
	}

	public void postEntry(BlogEntry entry) {
		list.add(0, entry);
	}

	public void removeEntry(BlogEntry entry) {
		list.remove(entry);
	}

	public List<BlogEntry> showMostRecent(int cant) {
		LinkedList<BlogEntry> result = new LinkedList<BlogEntry>();
		for (int i = 0; i < cant; i++)
			result.add(list.get(i));
		return result;
	}

	public List<BlogEntry> getEntries() {
		return list;
	}

}
