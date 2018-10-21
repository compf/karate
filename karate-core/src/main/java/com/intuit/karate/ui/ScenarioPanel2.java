/*
 * The MIT License
 *
 * Copyright 2018 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.ui;

import com.intuit.karate.core.ScenarioExecutionUnit;
import com.intuit.karate.core.Step;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author pthomas3
 */
public class ScenarioPanel2 extends BorderPane {

    private final AppSession2 session;
    private final ScenarioExecutionUnit unit;
    private final VBox content;
    private final VarsPanel2 varsPanel;
    
    private StepPanel2 lastStep;

    public ScenarioExecutionUnit getScenarioExecutionUnit() {
        return unit;
    }        

    public ScenarioPanel2(AppSession2 session, ScenarioExecutionUnit unit) {
        this.session = session;
        this.unit = unit;
        setPadding(App2.PADDING_TOP);
        content = new VBox(0);    
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        setCenter(scrollPane);
        varsPanel = new VarsPanel2(session, this);
        setRight(varsPanel);
        unit.getSteps().forEach(step -> addStepPanel(step)); 
        lastStep.setLast(true);
    }
    
    private void addStepPanel(Step step) {
        lastStep = new StepPanel2(session, this, step);
        content.getChildren().add(lastStep);
    }
    
    public void refreshVars() {
        varsPanel.refresh();
    }

}