package com.roomj.jetbrains.plugins;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResult;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CompletionReporter extends CompletionContributor {

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull final CompletionResultSet result) {
        try {
            final PrintWriter writer = new PrintWriter("c:/Users/tyler/PycharmProjects/dragonfly/command.txt");

            Consumer<CompletionResult> consumer = new Consumer<CompletionResult>() {
                @Override
                public void consume(CompletionResult completionResult) {
                    LookupElement lookupElement = completionResult.getLookupElement();
                    if (lookupElement.isWorthShowingInAutoPopup()) {
                        writer.println(lookupElement);
                    }
                    result.addElement(lookupElement);
                }
            };

            result.runRemainingContributors(parameters, consumer);
            writer.close();

        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

    }
}
