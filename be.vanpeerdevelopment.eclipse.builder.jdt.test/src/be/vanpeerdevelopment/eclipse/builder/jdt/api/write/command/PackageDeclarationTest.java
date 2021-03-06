package be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command;

import static be.vanpeerdevelopment.eclipse.builder.jdt.api.write.command.PackageDeclarationTestBuilder.aPackageDeclaration;
import static be.vanpeerdevelopment.eclipse.builder.jdt.element.IPackageFragmentTestBuilder.PACKAGE_NAME;
import static org.fest.assertions.Assertions.assertThat;

import org.eclipse.core.runtime.Path;
import org.junit.Test;

import be.vanpeerdevelopment.eclipse.builder.jdt.UnitTest;
import be.vanpeerdevelopment.eclipse.builder.jdt.api.write.ValidationException;

public class PackageDeclarationTest extends UnitTest {

	@Test
	public void testBuilderCreatesDefaultValidPackageDeclaration() {
		expectNoException();

		aPackageDeclaration().build();
	}

	@Test
	public void whenNoName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aPackageDeclaration()
				.withName(null)
				.build();
	}

	@Test
	public void whenEmptyName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aPackageDeclaration()
				.withName("")
				.build();
	}

	@Test
	public void whenBlankName_throwsValidationException() {
		expectExceptionWithMessage(
				ValidationException.class,
				"Name can not be blank.");

		aPackageDeclaration()
				.withName(" ")
				.build();
	}

	@Test
	public void isFor_True() {
		PackageDeclaration packageDeclaration = aPackageDeclaration()
				.withName("be.vanpeerdevelopment.eclipse.builder")
				.build();

		boolean result = packageDeclaration.isFor(new Path("/project/src/be.vanpeerdevelopment.eclipse.builder"));

		assertThat(result).isTrue();
	}

	@Test
	public void isFor_False() {
		PackageDeclaration packageDeclaration = aPackageDeclaration()
				.withName("be.vanpeerdevelopment.eclipse.builder")
				.build();

		boolean result = packageDeclaration.isFor(new Path("/project/src/be.vanpeerdevelopment.eclipse"));

		assertThat(result).isFalse();
	}

	@Test
	public void toCode() {
		PackageDeclaration packageDeclaration = aPackageDeclaration()
				.withName(PACKAGE_NAME)
				.build();

		String code = packageDeclaration.toCode();

		assertThat(code).isEqualTo("package " + PACKAGE_NAME + ";");
	}
}