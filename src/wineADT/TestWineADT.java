package wineADT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWineADT {
	
	// 336,US,"There's a cantaloupe-like ripeness on the nose of this blend of 37% Grenache Blanc, 33% Viognier and 30% Roussanne, but not overdone, layered with seared white peach, caramelized pear, poached apple and almond aromas. The palate is more restrained and quite grippy, with flavors of green-apple skins, Bosc pear and a touch of honey.",XCV,91,38.0,California,Paso Robles Willow Creek District,Central Coast,Rhône-style White Blend,Jada Vineyard & Winery
	/*
	 * country = items[0];
		description = items[1];
		designation = items[2];
		province = items[3];
		winery = items[4];
		variety = items[5];
		taste_notes = taste;
		this.region = region;
		this.rating = rating;
		this.price = price;
		unique_id = id;
	 */
	private static Wine[] new_test;
	
	@Before
	public void setUp() throws Exception {
		
		new_test = Read.wines;
		Read.read_tasteNotes(new_test);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestUniqueID() {
		//fail("Not yet implemented");
		System.out.println(new_test[0].get_uniqueID());
		assert(new_test[0].get_uniqueID() == 0);
	}
	
	
	
	@Test
	public void Test_insert_taste_notes() {
		Read.read_tasteNotes(new_test);
		//new_test[0].insert_taste_notes();
		
		if (new_test[0].get_taste_noteslist()[0] == "no_notes") {
			assert(true);
		}
		else {
			int count = 0;
			for (int i = 0; i < new_test[0].get_taste_noteslist().length; i++) {
				for (String elm : TasteNoteLibrary.get_patterns()) {
					if (new_test[0].get_taste_noteslist()[i] == elm) {
						assert(true);
						count++;
					}
				}
			}
			//System.out.println(new_test[0].get_taste_noteslist().length + ", " + count);
			//assert(count == new_test[0].get_taste_noteslist().length);
		}
	}

}
