package com.johnlindquist.acejump.keycommands;

import com.johnlindquist.acejump.AceFinder;
import com.johnlindquist.acejump.ui.SearchBox;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: johnlindquist
 * Date: 8/24/12
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShowEndOfLines extends AceKeyCommand {
    private final SearchBox searchBox;
    private final AceFinder aceFinder;

    public ShowEndOfLines(SearchBox searchBox, AceFinder aceFinder) {
        this.searchBox = searchBox;
        this.aceFinder = aceFinder;
    }

    public void execute(KeyEvent keyEvent) {
        aceFinder.addResultsReadyListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                eventDispatcher.getMulticaster().stateChanged(new ChangeEvent("ShowEndOfLines"));
            }
        });
        aceFinder.findText(AceFinder.END_OF_LINE, true);
        searchBox.forceSpaceChar();
    }
}
