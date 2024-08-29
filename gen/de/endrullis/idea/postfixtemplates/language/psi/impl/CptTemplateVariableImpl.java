// This is a generated file. Not intended for manual editing.
package de.endrullis.idea.postfixtemplates.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static de.endrullis.idea.postfixtemplates.language.psi.CptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import de.endrullis.idea.postfixtemplates.language.psi.*;

public class CptTemplateVariableImpl extends ASTWrapperPsiElement implements CptTemplateVariable {

  public CptTemplateVariableImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CptVisitor visitor) {
    visitor.visitTemplateVariable(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CptVisitor) accept((CptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CptTemplateVariableExpressionG getTemplateVariableExpressionG() {
    return findChildByClass(CptTemplateVariableExpressionG.class);
  }

  @Override
  @NotNull
  public CptTemplateVariableNameG getTemplateVariableNameG() {
    return findNotNullChildByClass(CptTemplateVariableNameG.class);
  }

  @Override
  @Nullable
  public CptTemplateVariableValueG getTemplateVariableValueG() {
    return findChildByClass(CptTemplateVariableValueG.class);
  }

}
