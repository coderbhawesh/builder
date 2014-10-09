package be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command;

import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.ClassDefinitionTestBuilder.aClassDefinition;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.CompilationUnitTestBuilder.aCompilationUnit;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.PackageDeclarationTestBuilder.aPackageDeclaration;
import static be.vanpeerdevelopment.eclipse.builder.jdt.element.IPackageFragmentTestBuilder.PACKAGE_NAME;
import static be.vanpeerdevelopment.eclipse.builder.jdt.element.ITypeTestBuilder.CLASS_DEFINITION_NAME;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import be.vanpeerdevelopment.eclipse.builder.jdt.UnitTest;

public class CompilationUnitTest extends UnitTest {

	@Test
	public void getNameWithExtension() {
		String name = "Person";
		CompilationUnit compilationUnit = aCompilationUnit()
				.withName(name)
				.build();

		String result = compilationUnit.getNameWithExtension();

		assertThat(result).isEqualTo(name + ".java");
	}

	@Test
	public void toCode() {
		CompilationUnit compilationUnit = aCompilationUnit()
				.withPackageDeclaration(aPackageDeclaration()
						.withName(PACKAGE_NAME)
						.build())
				.withClassDefinition(aClassDefinition()
						.withName(CLASS_DEFINITION_NAME)
						.build())
				.build();

		String code = compilationUnit.toCode();

		assertThat(code).isEqualTo("package " + PACKAGE_NAME + ";\npublic class " + CLASS_DEFINITION_NAME + "{}");
	}
}