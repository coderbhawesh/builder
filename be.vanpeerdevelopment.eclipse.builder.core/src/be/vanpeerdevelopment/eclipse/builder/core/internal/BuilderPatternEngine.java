package be.vanpeerdevelopment.eclipse.builder.core.internal;

import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.CreateCompilationUnitCommand.CreateCompilationUnitCommandBuilder.createCompilationUnitCommand;

import org.eclipse.core.runtime.IPath;

import be.vanpeerdevelopment.eclipse.builder.core.api.GenerateBuilderCommand;
import be.vanpeerdevelopment.eclipse.builder.jdt.api.read.JdtReadModel;
import be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.CreateCompilationUnitCommand;

public class BuilderPatternEngine {

	public static final String BUILDER_SUFFIX = "Builder";

	private JdtReadModel jdtReadModel;

	public BuilderPatternEngine() {
		this.jdtReadModel = new JdtReadModel();
	}

	public CreateCompilationUnitCommand generateBuilder(GenerateBuilderCommand command) {
		return createCompilationUnitCommand()
				.withPackageLocation(command.getDestinationPackageLocation())
				.withName(getJavaClassName(command.getSourceCompilationUnitLocation()) + BUILDER_SUFFIX)
				.build();
	}

	private String getJavaClassName(IPath compilationUnitLocation) {
		return jdtReadModel
				.getCompilationUnit(compilationUnitLocation)
				.getOnlyType()
				.getSimpleName();
	}
}