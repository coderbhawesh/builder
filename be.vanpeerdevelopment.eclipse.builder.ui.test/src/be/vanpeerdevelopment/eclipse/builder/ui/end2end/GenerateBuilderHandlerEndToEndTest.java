package be.vanpeerdevelopment.eclipse.builder.ui.end2end;

import static be.vanpeerdevelopment.eclipse.builder.swtbot.shortcut.Key.ALT;
import static be.vanpeerdevelopment.eclipse.builder.swtbot.shortcut.Key.CTRL;
import static be.vanpeerdevelopment.eclipse.builder.swtbot.shortcut.Key.key;
import static org.junit.Assert.assertTrue;

import org.eclipse.jface.bindings.keys.ParseException;
import org.junit.Test;

import be.vanpeerdevelopment.eclipse.builder.ui.EclipseTest;

public class GenerateBuilderHandlerEndToEndTest extends EclipseTest {

	@Test
	public void generateBuilder() throws ParseException {
		eclipse
				.openClass(JAVA_PROJECT_NAME,
						JAVA_SOURCE_FOLDER_NAME,
						JAVA_PACKAGE_NAME,
						JAVA_CLASS_NAME)
				.pressShortcut(CTRL, ALT, key("B"));

		assertTrue(eclipse.willClassBeCreated(
				JAVA_PROJECT_NAME,
				JAVA_SOURCE_FOLDER_NAME,
				JAVA_PACKAGE_NAME,
				JAVA_CLASS_NAME + "Builder"));

		eclipse
				.deleteClass(
						JAVA_PROJECT_NAME,
						JAVA_SOURCE_FOLDER_NAME,
						JAVA_PACKAGE_NAME,
						JAVA_CLASS_NAME + "Builder")
				.ok();
	}
}