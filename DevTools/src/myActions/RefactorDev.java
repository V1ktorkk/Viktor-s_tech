package myActions;

import com.intellij.ide.util.EditorHelper;
import com.intellij.internal.TestWriteActionUnderProgress;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.diff.DiffBundle;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.concurrency.EdtExecutorService;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.valueOf;

public class RefactorDev extends EditorAction {

    protected RefactorDev(EditorActionHandler defaultHandler) {
        super(defaultHandler);
    }

    public RefactorDev() {
        this(new UpHandler());
    }

    static DialogWrapper wrapper = new DialogWrapper(null) {
        @Override
        protected @Nullable JComponent createCenterPanel() {
            return new JTextField("bla bla bla");
        }

        {
            setTitle("You have some warnings with my plugins?");
            init();
        }
    };



    private static class UpHandler extends EditorWriteActionHandler {

        private UpHandler() {
        }

        @Override
        public void executeWriteAction(Editor editor, DataContext dataContext) {
            System.out.println(editor.getSelectionModel().getSelectedText());
            Document document = editor.getDocument();

            if (editor == null || document == null || !document.isWritable()) {
                return;
            }
            final int startOffset = editor.getSelectionModel().getSelectionStart();
            final int endOffset =editor.getSelectionModel().getSelectionEnd();
            if (editor.getSelectionModel().getSelectedText() == null) {
                Messages.showInfoMessage("ты че выдели хоть что-то", "ff");
            }
            else{
            if (editor.getSelectionModel().getSelectedText().equals(".a/string")) {
                editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), "string a");}
                else {
                    if (editor.getSelectionModel().getSelectedText().equals(".a/int")) {
                    editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), "int a");}
                    else{
                        if (editor.getSelectionModel().getSelectedText().equals(".MyMethod")) {
                        editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), "public void MyMethod");}
                       else{
                            if (editor.getSelectionModel().getSelectedText().equals(".MyClass")) {
                                editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), "public class MyClass");}
                            else{
                           if (editor.getSelectionModel().getSelectedText().equals(".class/stat")) {
                                    editor.getDocument().replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), "private static class Myclass");}
                           else{
                                    Messages.showErrorDialog("Надо было читать документацию)", "In my plugin there is no phrases to change");
                                    //Messages.showInfoMessage("выдели хоть что-то", "ff");

                                }
                            }
        }
    }
}}}}}
