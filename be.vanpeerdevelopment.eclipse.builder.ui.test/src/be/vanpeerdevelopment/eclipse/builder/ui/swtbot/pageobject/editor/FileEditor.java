package be.vanpeerdevelopment.eclipse.builder.ui.swtbot.pageobject.editor;

import org.eclipse.swt.SWTException;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import be.vanpeerdevelopment.eclipse.builder.ui.swtbot.pageobject.EditorObject;
import be.vanpeerdevelopment.eclipse.builder.ui.swtbot.pageobject.eclipse.Workbench;

public class FileEditor extends EditorObject {

	public FileEditor(Workbench workbench, String fileName) {
		super(workbench, fileName);
	}

	public boolean hasGenerateBuilderInSourceContextMenu() {
		try {
			editor
					.contextMenu("Source")
					.menu("Generate Builder");
			return true;
		} catch (SWTException e) {
			if (e.throwable instanceof WidgetNotFoundException)
				return false;
			throw e;
		}
	}
}