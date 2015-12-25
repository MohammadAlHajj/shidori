package test;
import static org.junit.Assert.*;

import java.io.File;

import mainFolder.ShidoriHelper;

import org.junit.Test;


public class TestShidoriHelper {

	@Test
	public void test() {
		File f = new File("C:\\Desktop\\Mohammad\\Videos\\Anime - Cartoon\\Anime\\[CBM] A Certain Scientific Railgun 1-24 Complete (Dual Audio) [DVDRip-480p-8bit]\\[CBM]_A_Certain_Scientific_Railgun_-_01_-_Electromaster_[304362A4].mkv");
		assertTrue(ShidoriHelper.isVideo(f));
	}

}
