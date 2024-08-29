package de.endrullis.idea.postfixtemplates.languages.scala;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiElement;
import org.jetbrains.plugins.scala.lang.completion.postfix.templates.selector.AncestorSelector;
import scala.jdk.javaapi.CollectionConverters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
class ScalaPostfixTemplatesUtils {

	static final Condition<PsiElement> BYTE     = isDescendant("scala.Byte", "java.lang.Byte");
	static final Condition<PsiElement> CHAR     = isDescendant("scala.Char", "java.lang.Character");
	static final Condition<PsiElement> DOUBLE   = isDescendant("scala.Double", "java.lang.Double");
	static final Condition<PsiElement> FLOAT    = isDescendant("scala.Float", "java.lang.Float");
	static final Condition<PsiElement> INT      = isDescendant("scala.Int", "java.lang.Integer");
	static final Condition<PsiElement> LONG     = isDescendant("scala.Long", "java.lang.Long");
	static final Condition<PsiElement> SHORT    = isDescendant("scala.Short", "java.lang.Short");
	static final Condition<PsiElement> BOOLEAN  = isDescendant("scala.Boolean", "java.lang.Boolean");
	static final Condition<PsiElement> VOID     = isDescendant("scala.Unit", "java.lang.Void");
	static final Condition<PsiElement> NON_VOID = e -> !VOID.value(e);
	static final Condition<PsiElement> DECIMAL_NUMBER = e ->
		Stream.of(BYTE, CHAR, DOUBLE, FLOAT, INT, LONG, SHORT).anyMatch(t -> t.value(e));

	public static Condition<PsiElement> isDescendant(String... classes) {
		return isDescendant(Arrays.asList(classes));
	}

	public static Condition<PsiElement> isDescendant(List<String> classes) {
		return AncestorSelector.isSameOrInheritor(CollectionConverters.asScala(classes).toSeq());
	}

}
