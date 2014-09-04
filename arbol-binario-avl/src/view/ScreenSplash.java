package view;

import java.awt.SplashScreen;

import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

final public class ScreenSplash
{

	public void init() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		SplashScreen.getSplashScreen();
		SubstanceLookAndFeel
				.setSkin("org.jvnet.substance.skin.CremeCoffeeSkin");
		ViewArbolAVL frame = new ViewArbolAVL();
		frame.setVisible(true);
	}

	public static void main(String a[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException
	{
		new ScreenSplash().init();
	}

}
