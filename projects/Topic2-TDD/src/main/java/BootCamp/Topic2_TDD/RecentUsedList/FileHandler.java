package BootCamp.Topic2_TDD.RecentUsedList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	private static FileHandler instance = null;

	private List<File> recentList;
	private static final int MAX_SIZE = 15;

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public static FileHandler getInstance() {
		if (instance == null) {
			instance = new FileHandler();
		}
		return instance;
	}

	private FileHandler() {
		recentList = new ArrayList<File>();
	}

	public void clear() {
		recentList.clear();
	}

	public List<File> getRecentList() {
		return recentList;
	}

	public BufferedReader openFile(File file) {
		BufferedReader br = null;
		try {
			// Open file
			br = getBufferedReader(file);
			// Update List
			updateRecentUsedList(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return br;
	}

	public BufferedReader getBufferedReader(File file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	private void updateRecentUsedList(File file) {
		if (!recentList.remove(file))
			if (recentList.size() == MAX_SIZE) {
				recentList.remove(recentList.size() - 1);
			}
		recentList.add(0, file);
	}

}
