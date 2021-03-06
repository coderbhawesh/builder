package be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command;

import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.ClassDefinitionTestBuilder.aClassDefinition;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.CompilationUnitTestBuilder.aCompilationUnit;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.FieldTestBuilder.aField;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.PackageDeclarationTestBuilder.aPackageDeclaration;
import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.TypeTestBuilder.aType;
import static be.vanpeerdevelopment.eclipse.builder.jdt.element.IPackageFragmentTestBuilder.PACKAGE_NAME;
import static be.vanpeerdevelopment.eclipse.builder.jdt.element.ITypeTestBuilder.CLASS_DEFINITION_NAME;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.runtime.IPath;
import org.junit.Test;

import be.vanpeerdevelopment.eclipse.builder.jdt.UnitTest;
import be.vanpeerdevelopment.eclipse.builder.jdt.api.write.ValidationException;

public class CompilationUnitTest extends UnitTest {

	@Test
	public void testBuilderCreatesDefaultValidCompilationUnit() {
		expectNoException();

		aCompilationUnit().build();
	}

	@Test
	public void whenNoName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aCompilationUnit()
				.withName(null)
				.build();
	}

	@Test
	public void whenEmptyName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aCompilationUnit()
				.withName("")
				.build();
	}

	@Test
	public void whenBlankName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aCompilationUnit()
				.withName(" ")
				.build();
	}

	@Test
	public void whenClassDefinitionNotHasSameName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Class definition name has to be same as compilation unit name.");

		aCompilationUnit()
				.withName("CompilationUnit")
				.withClassDefinition(aClassDefinition()
						.withName("ClassDefinition")
						.build())
				.build();
	}

	@Test
	public void whenNoPackageDeclaration_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Package declaration is required.");

		aCompilationUnit()
				.withPackageDeclaration(null)
				.build();
	}

	@Test
	public void whenNoClassDefinition_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Class definition is required.");

		aCompilationUnit()
				.withClassDefinition(null)
				.build();
	}

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
	public void isInPackage_delegatesToPackageDeclaration() {
		IPath packageLocation = mock(IPath.class);
		PackageDeclaration packageDeclaration = mock(PackageDeclaration.class);
		when(packageDeclaration.isFor(packageLocation)).thenReturn(true);
		CompilationUnit compilationUnit = aCompilationUnit()
				.withPackageDeclaration(packageDeclaration)
				.build();

		boolean result = compilationUnit.isInPackage(packageLocation);

		assertThat(result).isTrue();
	}

	@Test
	public void toCode() {
		CompilationUnit compilationUnit = aCompilationUnit()
				.withPackageDeclaration(aPackageDeclaration()
						.withName(PACKAGE_NAME)
						.build())
				.withClassDefinition(aClassDefinition()
						.withName(CLASS_DEFINITION_NAME)
						.withField(aField()
								.withType(aType()
										.withName("Person")
										.build())
								.withName("person")
								.build())
						.build())
				.build();

		String code = compilationUnit.toCode();

		assertThat(code)
				.isEqualTo(
						"package " + PACKAGE_NAME + ";\n"
								+ "public class " + CLASS_DEFINITION_NAME + "{\n"
								+ "private Person person;\n"
								+ "}");
	}
}