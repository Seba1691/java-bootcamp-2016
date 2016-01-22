package BootCamp.Topic2_TDD.RecentUsedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit test for simple App.
 */
public class FileHandlerTest {

	private FileHandler fileHandler;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fileHandler = Mockito.spy(FileHandler.getInstance());
		try {
			Mockito.doReturn(null).when(fileHandler).getBufferedReader(Mockito.any(File.class));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		fileHandler.clear();
	}

	@Test
	public final void whenIsRunForFirstTimeListIsEmpty() {
		Assert.assertTrue(fileHandler.getRecentList().isEmpty());
	}

	@Test
	public final void whenFileIsOpenedItIsAddedToAEmptyList() {
		File file = new File("File1");
		fileHandler.openFile(file);
		Assert.assertTrue(fileHandler.getRecentList().contains(file));
	}

	@Test
	public final void whenFileIsOpenedItIsAddedToANotEmptyList() {
		fileHandler.openFile(new File("File2"));
		fileHandler.openFile(new File("File3"));
		File file = new File("File1");
		fileHandler.openFile(file);
		Assert.assertTrue(fileHandler.getRecentList().contains(file));
	}

	@Test
	public final void whenExistingFileIsOpenedItIsDumpedToTheTopOfTheList() {
		fileHandler.openFile(new File("File1"));
		File file = new File("File2");
		fileHandler.openFile(file);
		fileHandler.openFile(new File("File3"));
		// Reopen File 2
		fileHandler.openFile(file);

		// Expected List
		List<File> expectedList = new ArrayList<File>();
		expectedList.add(new File("File2"));
		expectedList.add(new File("File3"));
		expectedList.add(new File("File1"));

		Assert.assertEquals(expectedList, fileHandler.getRecentList());
	}

	@Test
	public final void whenFileIsOpenedAndListIsFullOldestFileIsRemovedAndNewOneIsAdded() {
		// Make the list full
		for (int i = 1; i < 16; i++)
			fileHandler.openFile(new File("File" + i));
		// Add one more element
		fileHandler.openFile(new File("File16"));

		// Expected List
		List<File> expectedList = new ArrayList<File>();
		for (int i = 16; i > 1; i--)
			expectedList.add(new File("File" + i));

		Assert.assertEquals(expectedList, fileHandler.getRecentList());
	}

}
