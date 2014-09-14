package be.vanpeerdevelopment.eclipse.builder.jdt.write.api;

import static org.eclipse.jdt.core.JavaCore.createCompilationUnitFrom;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;

import be.vanpeerdevelopment.eclipse.builder.jdt.common.Workspace;
import be.vanpeerdevelopment.eclipse.builder.jdt.write.api.command.CreateCompilationUnitCommand;

public class JdtWriteModel {

	private Workspace workspace;

	public JdtWriteModel() {
		workspace = new Workspace();
	}

	public void createCompilationUnit(CreateCompilationUnitCommand command) {
		WriteablePackageFragment packageFragment = getPackage(command.getSiblingCompilationUnitLocation());
		packageFragment.createCompilationUnit(command);
	}

	private WriteablePackageFragment getPackage(IPath compilationUnitLocation) {
		IFile file = workspace.getFile(compilationUnitLocation);
		ICompilationUnit compilationUnit = createCompilationUnitFrom(file);
		IPackageFragment packageFragment = (IPackageFragment) compilationUnit.getParent();
		return new WriteablePackageFragment(packageFragment);
	}
}